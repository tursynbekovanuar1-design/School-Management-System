package school.grade;

import java.util.List;

/** Converts a 0..100 score into 4.0-scale grade points. All thresholds are named constants. */
public final class GradeScale {

    private static final double A_MIN_SCORE = 90.0;
    private static final double B_MIN_SCORE = 80.0;
    private static final double C_MIN_SCORE = 70.0;
    private static final double D_MIN_SCORE = 60.0;

    private static final double A_POINTS = 4.0;
    private static final double B_POINTS = 3.0;
    private static final double C_POINTS = 2.0;
    private static final double D_POINTS = 1.0;
    private static final double F_POINTS = 0.0;

    private record Band(double minScore, double points) { }

    private static final List<Band> BANDS = List.of(
            new Band(A_MIN_SCORE, A_POINTS),
            new Band(B_MIN_SCORE, B_POINTS),
            new Band(C_MIN_SCORE, C_POINTS),
            new Band(D_MIN_SCORE, D_POINTS));

    private GradeScale() { }

    public static double toGradePoints(double score) {
        for (Band band : BANDS) {
            if (score >= band.minScore()) {
                return band.points();
            }
        }
        return F_POINTS;
    }
}
