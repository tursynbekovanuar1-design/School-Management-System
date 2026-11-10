package school.facade;

import school.card.Benefit;
import school.card.ContactInfo;
import school.card.Document;
import school.card.PersonCard;
import school.card.PersonCardBuilder;
import school.common.Permission;
import school.common.UserType;
import school.gpa.GPACalculationContext;
import school.gpa.GPAStrategyType;
import school.grade.Grade;
import school.grade.GradeBook;
import school.notification.GradeEvent;
import school.notification.GradeEventPublisher;
import school.notification.GradeEventType;
import school.notification.ParentNotificationObserver;
import school.notification.StudentNotificationObserver;
import school.profile.UserProfile;
import school.profile.factory.UserProfileFactoryProvider;
import school.role.RoleDecoratorRegistry;
import school.role.RoleType;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Single entry point of the system. Hides the subsystems:
 *   - profile factories   (Factory Method, Developer 1)
 *   - role decorators     (Decorator,      Developer 1)
 *   - card builder        (Builder,        Developer 1)
 *   - grade notifications (Observer,       Developer 2)
 *   - GPA strategies      (Strategy,       Developer 2)
 *
 * Pattern: Facade
 * Owner:   Developer 2
 */
public class SchoolManagementFacade {

    private final UserProfileFactoryProvider profileFactories = new UserProfileFactoryProvider();
    private final RoleDecoratorRegistry roleRegistry = new RoleDecoratorRegistry();
    private final GradeEventPublisher publisher = new GradeEventPublisher();
    private final GradeBook gradeBook = new GradeBook(publisher);
    private final Map<String, UserProfile> users = new HashMap<>();
    private final PrintStream out;

    public SchoolManagementFacade(PrintStream out) {
        this.out = out;
    }

    // ---- Factory Method ----
    public UserProfile registerUser(UserType type, String id, String fullName) {
        UserProfile profile = profileFactories.create(type, id, fullName);
        users.put(id, profile);
        return profile;
    }

    // ---- Decorator ----
    public UserProfile assignRole(String userId, RoleType role) {
        UserProfile decorated = roleRegistry.decorate(findUser(userId), role);
        users.put(userId, decorated);
        return decorated;
    }

    public boolean hasPermission(String userId, Permission permission) {
        return findUser(userId).hasPermission(permission);
    }

    public String describeUser(String userId) {
        return findUser(userId).describe();
    }

    // ---- Observer ----
    public void subscribeToNotifications(String studentId, String parentName) {
        UserProfile student = requireStudent(studentId);
        publisher.subscribe(new StudentNotificationObserver(studentId, student.getFullName(), out));
        publisher.subscribe(new ParentNotificationObserver(studentId, parentName, out));
    }

    public void recordGrade(String teacherId, String studentId, String subject, double score, int credits) {
        requirePermission(teacherId, Permission.EDIT_GRADES);
        requireStudent(studentId);
        gradeBook.recordGrade(studentId, new Grade(subject, score, credits));
    }

    public void publishReport(String publisherId, String studentId, String reportTitle) {
        requirePermission(publisherId, Permission.PUBLISH_REPORTS);
        requireStudent(studentId);
        publisher.publish(new GradeEvent(GradeEventType.REPORT_PUBLISHED, studentId, reportTitle));
    }

    // ---- Strategy ----
    public double calculateGpa(String studentId, GPAStrategyType strategyType) {
        requireStudent(studentId);
        GPACalculationContext context = new GPACalculationContext(strategyType.create());
        double result = context.calculate(gradeBook.getGrades(studentId));
        out.printf("   %s -> %.2f%n", context.strategyName(), result);
        return result;
    }

    // ---- Builder ----
    public PersonCard buildCard(String userId, ContactInfo contact,
                                List<Document> documents, Set<Benefit> benefits) {
        UserProfile profile = findUser(userId);
        PersonCardBuilder builder = new PersonCardBuilder(profile)
                .withContact(contact)
                .withGrades(gradeBook.getGrades(userId));
        documents.forEach(builder::addDocument);
        benefits.forEach(builder::addBenefit);
        return builder.build();
    }

    // ---- internal helpers ----
    private UserProfile findUser(String userId) {
        UserProfile profile = users.get(userId);
        if (profile == null) {
            throw new IllegalArgumentException("Unknown user: " + userId);
        }
        return profile;
    }

    private UserProfile requireStudent(String userId) {
        UserProfile profile = findUser(userId);
        if (profile.getType() != UserType.STUDENT) {
            throw new IllegalArgumentException(userId + " is not a student");
        }
        return profile;
    }

    private void requirePermission(String userId, Permission permission) {
        if (!findUser(userId).hasPermission(permission)) {
            throw new AccessDeniedException(userId + " lacks permission " + permission);
        }
    }
}
