package com.fuzzycontrol.logic;

/**
 * An enumeration of all possible input and output variables for the fuzzy system.
 * This provides a type-safe way to reference variables in rules.
 */
public enum FuzzyVariable {
    // Input variables (Antecedents)
    DISTANCE_LEFT,
    DISTANCE_RIGHT,
    DISTANCE_AHEAD_LEFT,
    DISTANCE_AHEAD_RIGHT,
    SPEED,
    HEADING_CORRECTNESS,

    // Output variables (Consequents)
    ACCELERATION,
    RUDDER_ANGLE
}
