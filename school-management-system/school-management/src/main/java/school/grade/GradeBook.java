package school.grade;

import school.notification.GradeEvent;
import school.notification.GradeEventPublisher;
import school.notification.GradeEventType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores grades and publishes a GRADE_CHANGED event on every change.
 * Only talks to the publisher abstraction; it does not know who the observers are.
 *
 * Pattern: Observer (the source of events)
 * Owner:   Developer 2
 */
public class GradeBook {

    private final Map<String, List<Grade>> gradesByStudent = new HashMap<>();
    private final GradeEventPublisher publisher;

    public GradeBook(GradeEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void recordGrade(String studentId, Grade grade) {
        List<Grade> grades = gradesByStudent.computeIfAbsent(studentId, id -> new ArrayList<>());
        grades.removeIf(existing -> existing.subject().equals(grade.subject()));
        grades.add(grade);
        publisher.publish(new GradeEvent(GradeEventType.GRADE_CHANGED, studentId,
                String.format("%s -> %.1f", grade.subject(), grade.score())));
    }

    public List<Grade> getGrades(String studentId) {
        return Collections.unmodifiableList(gradesByStudent.getOrDefault(studentId, List.of()));
    }
}
