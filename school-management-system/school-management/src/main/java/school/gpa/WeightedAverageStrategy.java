package school.gpa;

import school.grade.Grade;
import school.grade.GradeScale;

import java.util.List;

/** Concrete Strategy: grade points weighted by credits. Pattern: Strategy. Owner: Developer 2. */
public class WeightedAverageStrategy implements GPACalculationStrategy {

    private static final double EMPTY_RESULT = 0.0;

    @Override
    public double calculate(List<Grade> grades) {
        double weightedSum = 0.0;
        int totalCredits = 0;
        for (Grade grade : grades) {
            weightedSum += GradeScale.toGradePoints(grade.score()) * grade.credits();
            totalCredits += grade.credits();
        }
        return totalCredits == 0 ? EMPTY_RESULT : weightedSum / totalCredits;
    }

    @Override
    public String name() {
        return "Weighted Average (by credits)";
    }
}
