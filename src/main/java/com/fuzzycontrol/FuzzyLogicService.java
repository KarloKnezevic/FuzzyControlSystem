package com.fuzzycontrol;

import com.fuzzycontrol.logic.FuzzyRule;
import com.fuzzycontrol.logic.FuzzyVariable;
import com.fuzzycontrol.logic.LeftShoulderFunction;
import com.fuzzycontrol.logic.MembershipFunction;
import com.fuzzycontrol.logic.RightShoulderFunction;
import com.fuzzycontrol.logic.TriangularFunction;
import com.fuzzycontrol.logic.ops.FuzzyOperators;

import java.util.List;
import java.util.function.Function;

/**
 * Service class responsible for all fuzzy logic calculations.
 * This class encapsulates the fuzzy rules, membership functions,
 * and the Tsukamoto defuzzification process.
 */
public class FuzzyLogicService {

    private final List<FuzzyRule> rules;

    /**
     * Initializes the fuzzy logic system, creating all membership functions and rules.
     */
    public FuzzyLogicService() {
        // --- Define Membership Functions for Inputs ---
        // These functions map a crisp input value (like distance) to a fuzzy value (like "close").
        MembershipFunction l_small = new LeftShoulderFunction(10, 30);
        MembershipFunction d_small = new LeftShoulderFunction(10, 30);
        MembershipFunction lk_small = new LeftShoulderFunction(50, 100);
        MembershipFunction dk_small = new LeftShoulderFunction(50, 100);
        MembershipFunction v_slow = new LeftShoulderFunction(20, 35);
        MembershipFunction v_med = new TriangularFunction(10, 20, 30);
        MembershipFunction v_fast = new RightShoulderFunction(40, 60);
        MembershipFunction v_very_fast = new RightShoulderFunction(45, 70);
        MembershipFunction v_large = new RightShoulderFunction(25, 45);
        MembershipFunction s_wrong = new LeftShoulderFunction(0, 1); // 1 = correct, 0 = wrong

        // --- Define Membership Functions for Outputs (Consequents) ---
        // For Tsukamoto, the consequents must be monotonic.
        // We also need their inverse functions for defuzzification.
        MembershipFunction k_turn_right_small = new LeftShoulderFunction(-10, 0);
        MembershipFunction k_turn_left_small = new RightShoulderFunction(0, 10);
        MembershipFunction k_turn_right_med = new LeftShoulderFunction(-45, 0);
        MembershipFunction k_turn_left_med = new RightShoulderFunction(0, 45);
        MembershipFunction k_turn_right_hard = new LeftShoulderFunction(-90, -70);
        MembershipFunction k_turn_left_hard = new RightShoulderFunction(70, 90);
        MembershipFunction k_turn_right_very_hard = new LeftShoulderFunction(-90, -88);
        MembershipFunction k_turn_left_very_hard = new RightShoulderFunction(88, 90);

        MembershipFunction a_accel_small = new RightShoulderFunction(0, 20);
        MembershipFunction a_accel_med = new RightShoulderFunction(25, 35);
        MembershipFunction a_accel_hard = new RightShoulderFunction(34, 35);
        MembershipFunction a_brake_hard = new LeftShoulderFunction(-35, -10);
        MembershipFunction a_brake_very_hard = new LeftShoulderFunction(-35, 0);

        // --- Rule Definitions ---
        // Each rule is defined with an antecedent and a consequent.
        // The antecedent is a lambda that takes ShipInput and computes the rule's firing strength.
        // The consequent is a lambda that takes the firing strength and computes the crisp output.
        rules = List.of(
            // Rule 1: If distance to left is small and speed is medium, turn right slightly.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(l_small.valueAt(input.distanceLeft()), v_med.valueAt(input.speed())),
                alpha -> -10 + 10 * alpha, // Inverse of k_turn_right_small
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 2: If distance to right is small and speed is medium, turn left slightly.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(d_small.valueAt(input.distanceRight()), v_med.valueAt(input.speed())),
                alpha -> 10 * alpha, // Inverse of k_turn_left_small
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 3: If ahead-left distance is small and speed is slow, turn right.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(lk_small.valueAt(input.distanceAheadLeft()), v_slow.valueAt(input.speed())),
                alpha -> -45 + 45 * alpha, // Inverse of k_turn_right_med
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 4: If ahead-right distance is small and speed is slow, turn left.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(dk_small.valueAt(input.distanceAheadRight()), v_slow.valueAt(input.speed())),
                alpha -> 45 * alpha, // Inverse of k_turn_left_med
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 5: If (LK is small and V is slow) OR (DK is small and V is slow), accelerate moderately.
            new FuzzyRule(
                input -> FuzzyOperators.drasticOr(
                    FuzzyOperators.drasticAnd(lk_small.valueAt(input.distanceAheadLeft()), v_slow.valueAt(input.speed())),
                    FuzzyOperators.drasticAnd(dk_small.valueAt(input.distanceAheadRight()), v_slow.valueAt(input.speed()))
                ),
                alpha -> 25 + 10 * alpha, // Inverse of a_accel_med
                FuzzyVariable.ACCELERATION
            ),
            // Rule 6: If L is small and LK is VERY small, turn hard right.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(
                    new LeftShoulderFunction(15, 25).valueAt(input.distanceLeft()),
                    FuzzyOperators.very(lk_small.valueAt(input.distanceAheadLeft()))
                ),
                alpha -> -90 + 20 * Math.sqrt(alpha), // Inverse of VERY(k_turn_right_hard)
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 7: If D is small and DK is VERY small, turn hard left.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(
                    new LeftShoulderFunction(15, 25).valueAt(input.distanceRight()),
                    FuzzyOperators.very(dk_small.valueAt(input.distanceAheadRight()))
                ),
                alpha -> 70 + 20 * Math.sqrt(alpha), // Inverse of VERY(k_turn_left_hard)
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 8: If V is NOT fast AND (LK OR DK are NOT small), accelerate slightly.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(
                    FuzzyOperators.zadehNot(v_fast.valueAt(input.speed())),
                    FuzzyOperators.zadehNot(FuzzyOperators.drasticOr(
                        new LeftShoulderFunction(30, 70).valueAt(input.distanceAheadLeft()),
                        new LeftShoulderFunction(30, 70).valueAt(input.distanceAheadRight())
                    ))
                ),
                alpha -> 20 * (1 - (1 - alpha) * (1 - alpha)), // Inverse of MOREORLESS(a_accel_small)
                FuzzyVariable.ACCELERATION
            ),
            // Rule 9: If V is MORE OR LESS very fast AND (LK OR DK is VERY small), brake hard.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(
                    FuzzyOperators.moreOrLess(v_very_fast.valueAt(input.speed())),
                    FuzzyOperators.drasticOr(
                        FuzzyOperators.very(lk_small.valueAt(input.distanceAheadLeft())),
                        FuzzyOperators.very(dk_small.valueAt(input.distanceAheadRight()))
                    )
                ),
                alpha -> -35 + 25 * Math.sqrt(alpha), // Inverse of VERY(a_brake_hard)
                FuzzyVariable.ACCELERATION
            ),
             // Rule 10: If V is large and LK is small, turn hard right.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(v_large.valueAt(input.speed()), lk_small.valueAt(input.distanceAheadLeft())),
                alpha -> -90 + 20 * Math.sqrt(alpha), // Inverse of VERY(k_turn_right_hard)
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 11: If V is large and DK is small, turn hard left.
            new FuzzyRule(
                input -> FuzzyOperators.drasticAnd(v_large.valueAt(input.speed()), dk_small.valueAt(input.distanceAheadRight())),
                alpha -> 70 + 20 * Math.sqrt(alpha), // Inverse of VERY(k_turn_left_hard)
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 12: If D is VERY small OR L is VERY small, accelerate hard.
            new FuzzyRule(
                input -> FuzzyOperators.drasticOr(
                    FuzzyOperators.very(new LeftShoulderFunction(10, 35).valueAt(input.distanceRight())),
                    FuzzyOperators.very(new LeftShoulderFunction(10, 35).valueAt(input.distanceLeft()))
                ),
                alpha -> 34 + alpha, // Inverse of a_accel_hard
                FuzzyVariable.ACCELERATION
            ),
            // Rule 13: If D is VERY small, turn very hard left.
            new FuzzyRule(
                input -> FuzzyOperators.very(new LeftShoulderFunction(10, 35).valueAt(input.distanceRight())),
                alpha -> 88 + 2 * alpha, // Inverse of k_turn_left_very_hard
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 14: If L is VERY small, turn very hard right.
            new FuzzyRule(
                input -> FuzzyOperators.very(new LeftShoulderFunction(10, 35).valueAt(input.distanceLeft())),
                alpha -> -90 + 2 * alpha, // Inverse of k_turn_right_very_hard
                FuzzyVariable.RUDDER_ANGLE
            ),
            // Rule 15: If direction is wrong, brake very hard.
            new FuzzyRule(
                input -> s_wrong.valueAt(input.headingCorrectness()),
                alpha -> -35 + 35 * Math.sqrt(alpha), // Inverse of VERY(a_brake_very_hard)
                FuzzyVariable.ACCELERATION
            )
        );
    }

    /**
     * Calculates the control output using the Tsukamoto fuzzy inference model.
     *
     * @param input The current state of the ship from the simulator.
     * @return A ControlOutput object with calculated acceleration and rudder angle.
     */
    public ControlOutput calculate(ShipInput input) {
        double weightedSumA = 0;
        double firingSumA = 0;
        double weightedSumK = 0;
        double firingSumK = 0;

        for (FuzzyRule rule : rules) {
            double firingStrength = rule.getFiringStrength(input);

            if (firingStrength > 1e-9) { // Only consider rules that have fired
                double crispOutput = rule.getCrispOutput(firingStrength);
                if (rule.getOutputVariable() == FuzzyVariable.ACCELERATION) {
                    weightedSumA += crispOutput * firingStrength;
                    firingSumA += firingStrength;
                } else { // RUDDER_ANGLE
                    weightedSumK += crispOutput * firingStrength;
                    firingSumK += firingStrength;
                }
            }
        }

        int finalA = 0;
        if (firingSumA > 1e-9) {
            finalA = (int) Math.round(weightedSumA / firingSumA);
        }

        int finalK = 0;
        if (firingSumK > 1e-9) {
            finalK = (int) Math.round(weightedSumK / firingSumK);
        }

        // Clamp values to their allowed ranges as a safety measure
        finalA = Math.max(-35, Math.min(35, finalA));
        finalK = Math.max(-90, Math.min(90, finalK));

        return new ControlOutput(finalA, finalK);
    }
}
