package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

public class Day06P1 {
    private static final List<int[]> MOVEMENT_VECTORS = List.of(
            new int[]{0, -1}, // Up
            new int[]{1, 0},  // Right
            new int[]{0, 1},  // Down
            new int[]{-1, 0}  // Left
    );

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>(readLines("dev/zoeller/aoc2024/input06.txt"));

        int[] position = findCaretPosition(lines);
        int movementIndex = 0; // Start with the "Up" direction
        int[] newPosition;
        do {
            markAsVisited(lines, position);
            newPosition = getNewPosition(position, MOVEMENT_VECTORS.get(movementIndex));
            if (isMovementOutsideMap(lines, newPosition)) {
                break;
            }
            while (isMovingTowardObstacle(lines, newPosition)) {
                movementIndex = turnRight(movementIndex);
                newPosition = getNewPosition(position, MOVEMENT_VECTORS.get(movementIndex));
            }
            position = newPosition;
        } while (true);

        System.out.println(String.format("Besuchte Orte: %s", countVisitedPlaces(lines)));
        lines.forEach(System.out::println);
    }

    private static int countVisitedPlaces(List<String> lines) {
        int count = 0;
        for (String line : lines) {
            for (char c : line.toCharArray()) {
                if (c == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    private static int turnRight(int currentIndex) {
        return (currentIndex + 1) % MOVEMENT_VECTORS.size();
    }

    private static void markAsVisited(List<String> lines, int[] position) {
        StringBuilder line = new StringBuilder(lines.get(position[1]));
        line.setCharAt(position[0], 'X');
        lines.set(position[1], line.toString());
    }

    private static boolean isMovingTowardObstacle(List<String> lines, int[] newPosition) {
        return lines.get(newPosition[1]).charAt(newPosition[0]) == '#';
    }

    private static boolean isMovementOutsideMap(List<String> lines, int[] newPosition) {
        return newPosition[0] < 0 || newPosition[0] >= lines.get(0).length() || newPosition[1] < 0 || newPosition[1] >= lines.size();
    }

    private static int[] getNewPosition(int[] position, int[] movementVector) {
        return new int[]{position[0] + movementVector[0], position[1] + movementVector[1]};
    }

    private static int[] findCaretPosition(List<String> lines) {
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            int colIndex = lines.get(lineIndex).indexOf('^');
            if (colIndex != -1) {
                return new int[]{colIndex, lineIndex};
            }
        }
        return new int[]{-1, -1}; // Return -1, -1 if the caret is not found
    }
}