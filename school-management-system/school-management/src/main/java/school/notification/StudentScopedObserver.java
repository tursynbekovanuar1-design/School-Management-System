package school.notification;

import java.io.PrintStream;
import java.util.Objects;

/**
 * Template for observers that care about ONE student only: filters events, then delegates to deliver().
 *
 * Pattern: Observer (abstract Concrete Observer)
 * Owner:   Developer 2
 */
public abstract class StudentScopedObserver implements GradeObserver {

    private final String studentId;
    protected final PrintStream out;

    protected StudentScopedObserver(String studentId, PrintStream out) {
        this.studentId = Objects.requireNonNull(studentId, "studentId");
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public final void update(GradeEvent event) {
        if (studentId.equals(event.studentId())) {
            deliver(event);
        }
    }

    protected abstract void deliver(GradeEvent event);
}
