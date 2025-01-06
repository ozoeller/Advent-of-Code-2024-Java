package dev.zoeller.aoc2024;

import java.io.IOException;

import static dev.zoeller.aoc2024.FileReader.readToString;

/**
 * This class calculates the solution for the second part of the fourth day.
 * The solution was created with the help of GitHub Copilot.
 */
public class Day04P2 {
    public static void main(String[] args) throws IOException {
        String input = readToString("dev/zoeller/aoc2024/input04.txt");

        System.out.println("Anzahl der gekreuzten Treffer: " + countCrossedOccurrences(input)); // 1974
    }

    private static long countCrossedOccurrences(String input) {
        String[] lines = input.split("\n");
        int gridSize = lines.length;
        long count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (lines[row].charAt(col) == 'A') {
                    count += checkDiagonalCrossedPattern(lines, row, col);
                }
            }
        }

        return count;
    }

    private static long checkDiagonalCrossedPattern(String[] lines, int row, int col) {
        int gridSize = lines.length;
        long count = 0;

        // Check diagonals
        if (row >= 1 && row < gridSize - 1 && col >= 1 && col < gridSize - 1) {
            if ((lines[row - 1].charAt(col - 1) == 'M' && lines[row + 1].charAt(col + 1) == 'S') ||
                    (lines[row - 1].charAt(col - 1) == 'S' && lines[row + 1].charAt(col + 1) == 'M')) {
                if ((lines[row - 1].charAt(col + 1) == 'M' && lines[row + 1].charAt(col - 1) == 'S') ||
                        (lines[row - 1].charAt(col + 1) == 'S' && lines[row + 1].charAt(col - 1) == 'M')) {
                    count++;
                }
            }
        }

        return count;
    }
}