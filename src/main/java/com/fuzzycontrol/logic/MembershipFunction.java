package com.fuzzycontrol.logic;

import java.util.function.Function;

/**
 * Represents a fuzzy membership function.
 * It is a functional interface that maps a crisp input value to a fuzzy membership degree (between 0.0 and 1.0).
 */
@FunctionalInterface
public interface MembershipFunction extends Function<Double, Double> {

    /**
     * Calculates the degree of membership for a given crisp value.
     *
     * @param value The crisp input value.
     * @return The degree of membership, a value between 0.0 and 1.0.
     */
    default double valueAt(double value) {
        return apply(value);
    }
}
