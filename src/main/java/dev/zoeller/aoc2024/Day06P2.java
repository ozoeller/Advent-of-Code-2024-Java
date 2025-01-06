package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * The solution for part 2 was created with the help of GitHub Copilot, based on part 1, which was mostly created by me
 * without the help of AI.
 */
public class Day06P2 {
    private static final List<int[]> MOVEMENT_VECTORS = List.of(
            new int[]{0, -1}, // Up
            new int[]{1, 0},  // Right
            new int[]{0, 1},  // Down
            new int[]{-1, 0}  // Left
    );

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>(readLines("dev/zoeller/aoc2024/input06.txt"));
        int loopCreatingObstacles = countLoopCreatingObstacles(lines);
        System.out.println(String.format("Number of positions to place an obstacle to create a loop: %d", loopCreatingObstacles));
    }

    private static int countLoopCreatingObstacles(List<String> lines) {
        int count = 0;
        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (lines.get(row).charAt(col) == '.') {
                    List<String> modifiedLines = new ArrayList<>(lines);
                    StringBuilder line = new StringBuilder(modifiedLines.get(row));
                    line.setCharAt(col, '#');
                    modifiedLines.set(row, line.toString());
                    if (createsLoop(modifiedLines)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean createsLoop(List<String> lines) {
        int[] position = findCaretPosition(lines);
        int movementIndex = 0; // Start with the "Up" direction
        int[] newPosition;
        Set<String> visitedPositions = new HashSet<>();
        while (true) {
            String posKey = position[0] + "," + position[1] + "," + movementIndex;
            if (visitedPositions.contains(posKey)) {
                return true;
            }
            visitedPositions.add(posKey);
            markAsVisited(lines, position);
            newPosition = getNewPosition(position, MOVEMENT_VECTORS.get(movementIndex));
            if (isMovementOutsideMap(lines, newPosition)) {
                return false;
            }
            while (isMovingTowardObstacle(lines, newPosition)) {
                movementIndex = turnRight(movementIndex);
                newPosition = getNewPosition(position, MOVEMENT_VECTORS.get(movementIndex));
            }
            position = newPosition;
        }
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