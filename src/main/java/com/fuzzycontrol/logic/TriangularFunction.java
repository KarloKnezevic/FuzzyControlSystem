package com.fuzzycontrol.logic;

/**
 * Represents a triangular membership function (Lambda-function).
 * <p>
 * The function increases linearly from 0 at alpha to 1 at beta,
 * and then decreases linearly from 1 at beta to 0 at gamma.
 * This is equivalent to the original 'Lambda' function.
 */
public class TriangularFunction implements MembershipFunction {

    private final double alpha;
    private final double beta;
    private final double gamma;

    /**
     * Constructs a TriangularFunction.
     *
     * @param alpha The value where the triangle begins (membership is 0).
     * @param beta  The value where the triangle peaks (membership is 1).
     * @param gamma The value where the triangle ends (membership is 0).
     * @throws IllegalArgumentException if alpha >= beta or beta >= gamma.
     */
    public TriangularFunction(double alpha, double beta, double gamma) {
        if (alpha >= beta || beta >= gamma) {
            throw new IllegalArgumentException("The condition alpha < beta < gamma must be met.");
        }
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    @Override
    public Double apply(Double value) {
        if (value < alpha || value >= gamma) {
            return 0.0;
        }
        if (value >= alpha && value < beta) {
            return (value - alpha) / (beta - alpha);
        }
        // value >= beta && value < gamma
        return (gamma - value) / (gamma - beta);
    }
}
