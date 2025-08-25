package com.fuzzycontrol.logic;

import com.fuzzycontrol.ShipInput;
import java.util.function.Function;

/**
 * Represents a single fuzzy rule in the format: IF (antecedent) THEN (consequent).
 * This class is designed for a Tsukamoto-style fuzzy inference system.
 */
public class FuzzyRule {

    private final Function<ShipInput, Double> antecedent;
    private final Function<Double, Double> consequent;
    private final FuzzyVariable outputVariable;

    /**
     * Constructs a new fuzzy rule.
     *
     * @param antecedent     A function that takes a crisp ShipInput and returns the rule's firing strength (a value from 0.0 to 1.0).
     * @param consequent     A function that takes the firing strength (alpha-cut) and returns a single crisp output value.
     *                       For Tsukamoto, this is the inverse of the consequent membership function.
     * @param outputVariable The output variable this rule affects (e.g., ACCELERATION or RUDDER_ANGLE).
     */
    public FuzzyRule(Function<ShipInput, Double> antecedent, Function<Double, Double> consequent, FuzzyVariable outputVariable) {
        this.antecedent = antecedent;
        this.consequent = consequent;
        this.outputVariable = outputVariable;
    }

    /**
     * Calculates the firing strength of the rule's antecedent for a given ship input.
     *
     * @param input The current ship sensor data.
     * @return The firing strength (degree of truth) of the antecedent, between 0.0 and 1.0.
     */
    public double getFiringStrength(ShipInput input) {
        return antecedent.apply(input);
    }

    /**
     * Calculates the crisp output value from the consequent based on a given firing strength.
     *
     * @param firingStrength The alpha-cut value from the antecedent.
     * @return The crisp output value for this rule.
     */
    public double getCrispOutput(double firingStrength) {
        return consequent.apply(firingStrength);
    }

    /**
     * Gets the output variable that this rule is intended to control.
     *
     * @return The output fuzzy variable.
     */
    public FuzzyVariable getOutputVariable() {
        return outputVariable;
    }
}
