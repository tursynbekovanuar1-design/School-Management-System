package school;

import school.card.Benefit;
import school.card.ContactInfo;
import school.card.Document;
import school.card.PersonCard;
import school.common.UserType;
import school.facade.AccessDeniedException;
import school.facade.SchoolManagementFacade;
import school.gpa.GPAStrategyType;
import school.role.RoleType;

import java.util.List;
import java.util.Set;

/**
 * Demo scenario: create users -> assign roles -> subscribe to notifications -> record grades
 * -> calculate GPA -> publish report -> build card. Everything goes through the Facade.
 */
public class Main {

    private static final String STUDENT_ID = "S-001";
    private static final String TEACHER_ID = "T-001";
    private static final String ADMIN_ID = "A-001";

    public static void main(String[] args) {
        SchoolManagementFacade school = new SchoolManagementFacade(System.out);

        step("1. Factory Method: create profiles");
        school.registerUser(UserType.STUDENT, STUDENT_ID, "Aigerim Seitova");
        school.registerUser(UserType.TEACHER, TEACHER_ID, "Daniyar Omarov");
        school.registerUser(UserType.ADMIN, ADMIN_ID, "Madina Kenzhebek");
        System.out.println("   " + school.describeUser(STUDENT_ID));
        System.out.println("   " + school.describeUser(TEACHER_ID));

        step("2. Decorator: assign roles dynamically");
        school.assignRole(STUDENT_ID, RoleType.CLUB_LEADER);
        school.assignRole(STUDENT_ID, RoleType.LAB_ASSISTANT);
        school.assignRole(TEACHER_ID, RoleType.HEAD_TEACHER);
        System.out.println("   " + school.describeUser(STUDENT_ID));
        System.out.println("   " + school.describeUser(TEACHER_ID));
        try {
            school.recordGrade(STUDENT_ID, STUDENT_ID, "Math", 100, 3);
        } catch (AccessDeniedException e) {
            System.out.println("   Access check works: " + e.getMessage());
        }

        step("3. Observer: subscribe student and parent, record grades");
        school.subscribeToNotifications(STUDENT_ID, "Aigul Seitova");
        school.recordGrade(TEACHER_ID, STUDENT_ID, "Math", 95, 3);
        school.recordGrade(TEACHER_ID, STUDENT_ID, "Physics", 82, 4);
        school.recordGrade(TEACHER_ID, STUDENT_ID, "History", 74, 2);
        school.recordGrade(TEACHER_ID, STUDENT_ID, "Physics", 88, 4);

        step("4. Strategy: calculate GPA with different algorithms");
        for (GPAStrategyType type : GPAStrategyType.values()) {
            school.calculateGpa(STUDENT_ID, type);
        }

        step("5. Observer: publish report");
        school.publishReport(TEACHER_ID, STUDENT_ID, "Term 1 report is available");

        step("6. Builder: assemble student card");
        PersonCard card = school.buildCard(
                STUDENT_ID,
                new ContactInfo("aigerim@school.kz", "+7 700 000 00 00", "Astana"),
                List.of(new Document("Birth certificate", "ID"), new Document("Olympiad diploma", "Award")),
                Set.of(Benefit.SCHOLARSHIP, Benefit.MERIT_AWARD));
        System.out.println(card.summary());
    }

    private static void step(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
