# Fuzzy Boat Control System

This project is an implementation of a fuzzy control system designed to navigate a ship through a canal, avoiding collisions. The original codebase has been refactored to use modern Java 17 practices, with a focus on readability, modularity, and clear documentation.

## 🚢 Project Goal

The primary goal is to create a fuzzy controller that receives sensor data from a ship simulator and outputs control commands (acceleration and rudder angle) to guide the ship safely.

- **Input (from simulator):** 6 integer values representing distances to shores, diagonal distances, speed, and heading.
- **Output (to simulator):** 2 integer values for acceleration (`-35` to `35`) and rudder angle (`-90` to `90`).
- **Communication:** The system reads from standard input and writes to standard output.

## ✨ Architecture

The refactored application follows a clean, modular design:

- **`Main.java`**: The main entry point. Handles the communication loop with the simulator (reading `stdin`, writing `stdout`). It delegates all decision-making to the `FuzzyLogicService`.
- **`FuzzyLogicService.java`**: The core of the fuzzy logic. It contains all the rules, membership functions, and the defuzzification logic based on the Tsukamoto fuzzy model.
- **`ShipInput.java` / `ControlOutput.java`**: Modern Java records used as immutable data carriers for simulator communication.
- **`com.fuzzycontrol.logic.*`**: A dedicated package containing all the building blocks for the fuzzy system:
    - **`MembershipFunction`**: An interface for fuzzy set definitions (e.g., `LeftShoulderFunction`).
    - **`FuzzyRule`**: A class representing a single IF-THEN rule.
    - **`FuzzyOperators`**: A utility class for fuzzy operations (AND, OR, NOT, etc.).
- **`pom.xml`**: A Maven build file for easy project compilation and packaging.

## 🛠️ How to Build and Run

This project uses Apache Maven for building.

### Prerequisites

- Java 17 or higher
- Apache Maven

### Building the Application

1.  **Clone the repository.**
2.  **Navigate to the project root directory.**
3.  **Run the Maven package command:**

    ```bash
    mvn package
    ```

4.  This will compile the source code and create a runnable JAR file at `target/fuzzy-boat-1.0.0-jar-with-dependencies.jar`.

### Running with the Simulator

The simulator (`res/Simulator.jar`) is configured via the `res/config.txt` file.

1.  **Open `res/config.txt` in a text editor.**
2.  **Modify the first line** to point to the newly built JAR file. You must use an **absolute path**.

    For example, if your project is located at `C:\Users\Me\Projects\fuzzy-boat`, the command would be:

    ```
    java -jar C:\Users\Me\Projects\fuzzy-boat\target\fuzzy-boat-1.0.0-jar-with-dependencies.jar
    ```

    *Note: On Linux or macOS, the path would look like `/home/me/projects/fuzzy-boat/target/...`*

3.  **Configure other parameters** (track number, wind speed) as desired.
4.  **Run the simulator** by double-clicking `res/Simulator.jar` or by executing `java -jar res/Simulator.jar` from your terminal. The simulator will then start your fuzzy control program automatically.
