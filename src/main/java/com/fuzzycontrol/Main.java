package com.fuzzycontrol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Main entry point for the Fuzzy Boat Control System.
 * Handles communication with the simulator.
 */
public class Main {

    public static void main(String[] args) {
        // System.err is used for logging, as System.out is used for simulator communication.
        System.err.println("Fuzzy Boat Control System starting.");

        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            FuzzyLogicService fuzzyService = new FuzzyLogicService();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if ("END".equalsIgnoreCase(line)) {
                    System.err.println("END signal received. Shutting down.");
                    break;
                }

                try {
                    ShipInput input = ShipInput.fromString(line);
                    System.err.println("Received input: " + input);

                    // Delegate calculation to the fuzzy logic service
                    ControlOutput output = fuzzyService.calculate(input);

                    System.err.println("Sending output: " + output);
                    writer.write(output.toString());
                    writer.newLine();
                    writer.flush(); // Crucial for communication with the simulator.

                } catch (IllegalArgumentException e) {
                    System.err.println("Error parsing input line: '" + line + "'. " + e.getMessage());
                    // Decide if we should continue or exit on bad input.
                    // For now, we'll just log and continue, waiting for the next valid line.
                }
            }
        } catch (IOException e) {
            // Use a more descriptive error message
            System.err.println("A critical IO error occurred: " + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            System.err.println("Fuzzy Boat Control System stopped.");
        }
    }
}
