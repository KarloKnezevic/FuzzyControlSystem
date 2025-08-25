package com.fuzzycontrol;

/**
 * Represents the 6 integer inputs received from the simulator.
 * Using a record for an immutable data carrier.
 *
 * @param distanceLeft Shore distance to the left.
 * @param distanceRight Shore distance to the right.
 * @param distanceAheadLeft Diagonal distance ahead-left.
 * @param distanceAheadRight Diagonal distance ahead-right.
 * @param speed Current ship speed (Vm).
 * @param headingCorrectness Ship heading correctness (1 for correct, 0 otherwise).
 */
public record ShipInput(
    int distanceLeft,
    int distanceRight,
    int distanceAheadLeft,
    int distanceAheadRight,
    int speed,
    int headingCorrectness
) {
    /**
     * Parses a single line of space-separated text from the simulator into a ShipInput object.
     *
     * @param line The input line from the simulator.
     * @return A new ShipInput object.
     * @throws IllegalArgumentException if the line does not contain exactly 6 parsable integers.
     */
    public static ShipInput fromString(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Input line must contain 6 space-separated integers.");
        }
        try {
            int l = Integer.parseInt(parts[0]);
            int d = Integer.parseInt(parts[1]);
            int lk = Integer.parseInt(parts[2]);
            int dk = Integer.parseInt(parts[3]);
            int v = Integer.parseInt(parts[4]);
            int s = Integer.parseInt(parts[5]);
            return new ShipInput(l, d, lk, dk, v, s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("All parts of the input line must be integers.", e);
        }
    }
}
