package school.gpa;

import school.grade.Grade;
import school.grade.GradeScale;

import java.util.List;

/** Concrete Strategy: plain average of grade points (credits ignored). Pattern: Strategy. Owner: Developer 2. */
public class StandardGPAStrategy implements GPACalculationStrategy {

    private static final double EMPTY_RESULT = 0.0;

    @Override
    public double calculate(List<Grade> grades) {
        return grades.stream()
                .mapToDouble(grade -> GradeScale.toGradePoints(grade.score()))
                .average()
                .orElse(EMPTY_RESULT);
    }

    @Override
    public String name() {
        return "Standard GPA";
    }
}
