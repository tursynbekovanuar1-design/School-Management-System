package school.notification;

/** Immutable event passed from the Subject to its Observers. Pattern: Observer. Owner: Developer 2. */
public record GradeEvent(GradeEventType type, String studentId, String message) { }
