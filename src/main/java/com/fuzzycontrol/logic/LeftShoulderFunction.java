package com.fuzzycontrol.logic;

/**
 * Represents a left-shoulder membership function (L-function).
 * <p>
 * The function is 1.0 for values up to a certain point (alpha),
 * then decreases linearly to 0.0 at another point (beta).
 * It is 0.0 for all values greater than or equal to beta.
 * This is equivalent to the original 'Lfunc'.
 */
public class LeftShoulderFunction implements MembershipFunction {

    private final double alpha;
    private final double beta;

    /**
     * Constructs a LeftShoulderFunction.
     *
     * @param alpha The value at which the function starts to decrease from 1.0.
     * @param beta  The value at which the function reaches 0.0.
     * @throws IllegalArgumentException if alpha is not less than beta.
     */
    public LeftShoulderFunction(double alpha, double beta) {
        if (alpha >= beta) {
            throw new IllegalArgumentException("Alpha must be less than beta.");
        }
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public Double apply(Double value) {
        if (value < alpha) {
            return 1.0;
        }
        if (value >= beta) {
            return 0.0;
        }
        // Linear interpolation between alpha and beta
        return (beta - value) / (beta - alpha);
    }
}
