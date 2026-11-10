package school.gpa;

import school.grade.Grade;

import java.util.List;

/**
 * Strategy interface: one algorithm for computing a final GPA / rating.
 * OCP: add a new algorithm by implementing this interface.
 *
 * Pattern: Strategy (Strategy)
 * Owner:   Developer 2
 */
public interface GPACalculationStrategy {

    double calculate(List<Grade> grades);

    String name();
}
