package com.fuzzycontrol;

/**
 * Represents the 2 integer outputs sent to the simulator.
 * Using a record for an immutable data carrier.
 *
 * @param acceleration The calculated acceleration.
 * @param rudderAngle The calculated rudder angle.
 */
public record ControlOutput(
    int acceleration,
    int rudderAngle
) {
    /**
     * Formats the output for the simulator.
     *
     * @return A string with acceleration and rudder angle separated by a space.
     */
    @Override
    public String toString() {
        return acceleration + " " + rudderAngle;
    }
}
