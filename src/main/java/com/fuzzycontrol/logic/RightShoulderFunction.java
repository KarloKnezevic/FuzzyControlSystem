package com.fuzzycontrol.logic;

/**
 * Represents a right-shoulder membership function (Gamma-function).
 * <p>
 * The function is 0.0 for values up to a certain point (alpha),
 * then increases linearly to 1.0 at another point (beta).
 * It is 1.0 for all values greater than or equal to beta.
 * This is equivalent to the original 'Gamma' function.
 */
public class RightShoulderFunction implements MembershipFunction {

    private final double alpha;
    private final double beta;

    /**
     * Constructs a RightShoulderFunction.
     *
     * @param alpha The value at which the function starts to increase from 0.0.
     * @param beta  The value at which the function reaches 1.0.
     * @throws IllegalArgumentException if alpha is not less than beta.
     */
    public RightShoulderFunction(double alpha, double beta) {
        if (alpha >= beta) {
            throw new IllegalArgumentException("Alpha must be less than beta.");
        }
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public Double apply(Double value) {
        if (value < alpha) {
            return 0.0;
        }
        if (value >= beta) {
            return 1.0;
        }
        // Linear interpolation between alpha and beta
        return (value - alpha) / (beta - alpha);
    }
}
