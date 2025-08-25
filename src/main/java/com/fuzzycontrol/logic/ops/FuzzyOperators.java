package com.fuzzycontrol.logic.ops;

import com.fuzzycontrol.logic.MembershipFunction;

/**
 * A collection of fuzzy logic operators (T-norms, S-norms, and modifiers).
 * This class provides static methods for common fuzzy operations.
 */
public final class FuzzyOperators {

    private FuzzyOperators() {
        // Private constructor to prevent instantiation
    }

    /**
     * Represents a fuzzy AND operation using the Drastic T-norm (Drastic Product).
     *
     * @param a The first membership degree.
     * @param b The second membership degree.
     * @return The result of the drastic AND operation.
     */
    public static double drasticAnd(double a, double b) {
        if (Math.abs(a - 1.0) < 1e-9) return b;
        if (Math.abs(b - 1.0) < 1e-9) return a;
        return 0.0;
    }

    /**
     * Represents a fuzzy OR operation using the Drastic S-norm (Drastic Sum).
     *
     * @param a The first membership degree.
     * @param b The second membership degree.
     * @return The result of the drastic OR operation.
     */
    public static double drasticOr(double a, double b) {
        if (Math.abs(a - 0.0) < 1e-9) return b;
        if (Math.abs(b - 0.0) < 1e-9) return a;
        return 1.0;
    }

    /**
     * Represents the standard fuzzy NOT operation (Zadeh Not).
     *
     * @param a The membership degree.
     * @return 1.0 - a.
     */
    public static double zadehNot(double a) {
        return 1.0 - a;
    }

    /**
     * Represents the 'VERY' hedge, which concentrates the fuzzy set.
     * It is implemented by squaring the membership degree.
     *
     * @param a The membership degree.
     * @return The squared membership degree.
     */
    public static double very(double a) {
        return a * a;
    }

    /**
     * Represents the 'MORE OR LESS' hedge, which dilates the fuzzy set.
     * It is implemented by taking the square root of the membership degree.
     *
     * @param a The membership degree.
     * @return The square root of the membership degree.
     */
    public static double moreOrLess(double a) {
        return Math.sqrt(a);
    }
}
