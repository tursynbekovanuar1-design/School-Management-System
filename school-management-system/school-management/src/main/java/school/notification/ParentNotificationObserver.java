package school.notification;

import java.io.PrintStream;

/** Concrete Observer: notifies the parent. Pattern: Observer. Owner: Developer 2. */
public class ParentNotificationObserver extends StudentScopedObserver {

    private final String parentName;

    public ParentNotificationObserver(String studentId, String parentName, PrintStream out) {
        super(studentId, out);
        this.parentName = parentName;
    }

    @Override
    protected void deliver(GradeEvent event) {
        out.printf("   [PARENT  %s] %s: %s%n", parentName, event.type(), event.message());
    }
}
