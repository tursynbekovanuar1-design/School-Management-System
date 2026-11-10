package school.gpa;

import school.grade.Grade;

import java.util.List;
import java.util.Objects;

/**
 * Context: delegates the calculation to the currently selected strategy,
 * which can be swapped at runtime.
 *
 * Pattern: Strategy (Context)
 * Owner:   Developer 2
 */
public class GPACalculationContext {

    private GPACalculationStrategy strategy;

    public GPACalculationContext(GPACalculationStrategy strategy) {
        setStrategy(strategy);
    }

    public final void setStrategy(GPACalculationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public double calculate(List<Grade> grades) {
        return strategy.calculate(grades);
    }

    public String strategyName() {
        return strategy.name();
    }
}
