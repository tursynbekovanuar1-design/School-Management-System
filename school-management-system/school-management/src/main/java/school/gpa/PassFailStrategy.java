package school.gpa;

import school.grade.Grade;

import java.util.List;

/** Concrete Strategy: 1.0 if every subject reaches the pass threshold, otherwise 0.0. Pattern: Strategy. Owner: Developer 2. */
public class PassFailStrategy implements GPACalculationStrategy {

    private static final double PASS_THRESHOLD = 50.0;
    private static final double PASS = 1.0;
    private static final double FAIL = 0.0;

    @Override
    public double calculate(List<Grade> grades) {
        boolean allPassed = !grades.isEmpty()
                && grades.stream().allMatch(grade -> grade.score() >= PASS_THRESHOLD);
        return allPassed ? PASS : FAIL;
    }

    @Override
    public String name() {
        return "Pass/Fail (1.0 = pass)";
    }
}
