package school.grade;

/**
 * A single course result. Immutable value object.
 * Score is on a 0..100 scale; credits are positive.
 */
public record Grade(String subject, double score, int credits) {

    public static final double MIN_SCORE = 0.0;
    public static final double MAX_SCORE = 100.0;

    public Grade {
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("subject must not be blank");
        }
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("score must be in [" + MIN_SCORE + ", " + MAX_SCORE + "]");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("credits must be positive");
        }
    }
}
