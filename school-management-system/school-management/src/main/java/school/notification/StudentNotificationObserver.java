package school.notification;

import java.io.PrintStream;

/** Concrete Observer: notifies the student. Pattern: Observer. Owner: Developer 2. */
public class StudentNotificationObserver extends StudentScopedObserver {

    private final String studentName;

    public StudentNotificationObserver(String studentId, String studentName, PrintStream out) {
        super(studentId, out);
        this.studentName = studentName;
    }

    @Override
    protected void deliver(GradeEvent event) {
        out.printf("   [STUDENT %s] %s: %s%n", studentName, event.type(), event.message());
    }
}
