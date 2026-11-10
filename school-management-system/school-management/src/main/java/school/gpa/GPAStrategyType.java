package school.gpa;

import java.util.function.Supplier;

/** Names of available strategies and how to create them. Pattern: Strategy (selection helper). Owner: Developer 2. */
public enum GPAStrategyType {
    STANDARD(StandardGPAStrategy::new),
    WEIGHTED(WeightedAverageStrategy::new),
    PASS_FAIL(PassFailStrategy::new);

    private final Supplier<GPACalculationStrategy> factory;

    GPAStrategyType(Supplier<GPACalculationStrategy> factory) {
        this.factory = factory;
    }

    public GPACalculationStrategy create() {
        return factory.get();
    }
}
