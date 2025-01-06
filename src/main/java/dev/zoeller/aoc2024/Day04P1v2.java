package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.regex.Pattern;

import static dev.zoeller.aoc2024.FileReader.readToString;

/**
 * Mit dieser Klasse wird die Lösung für den ersten Teil des vierten Tages berechnet, in einer optimierten Art und Weise.
 * Die Optimierung wurde mithilfe von GitHub Copilot erstellt, nachdem die KI darauf hingewiesen wurde,
 * Richtungsvektoren zu verwenden, um die Anzahl der Treffer in allen Richtungen zu zählen.
 */
public class Day04P1v2 {
    public static void main(String[] args) throws IOException {
        String input = readToString("dev/zoeller/aoc2024/input04.txt");
        String searchString = "XMAS";

        long totalCount = countDirectionalOccurrences(input, searchString, new int[]{1, 0}); // Horizontal
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{-1, 0}); // Horizontal Reverse
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{0, 1}); // Vertical
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{0, -1}); // Vertical Reverse
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{1, 1}); // Diagonal
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{1, -1}); // Diagonal Reverse
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{-1, 1}); // Diagonal Bottom-Left to Top-Right
        totalCount += countDirectionalOccurrences(input, searchString, new int[]{-1, -1}); // Diagonal Bottom-Right to Top-Left

        System.out.println("Anzahl der Treffer: " + totalCount); // 2642
    }

    private static long countOccurrences(String input, String searchString) {
        Pattern pattern = Pattern.compile(Pattern.quote(searchString));
        return pattern.matcher(input).results().count();
    }

    private static long countDirectionalOccurrences(String input, String searchString, int[] direction) {
        String[] lines = input.split("\n");
        int gridSize = lines.length;
        long count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                StringBuilder sequence = new StringBuilder();
                for (int offset = 0; offset < searchString.length(); offset++) {
                    int newRow = row + offset * direction[0];
                    int newCol = col + offset * direction[1];
                    if (newRow >= 0 && newRow < gridSize && newCol >= 0 && newCol < gridSize) {
                        sequence.append(lines[newRow].charAt(newCol));
                    } else {
                        break;
                    }
                }
                if (sequence.length() == searchString.length()) {
                    count += countOccurrences(sequence.toString(), searchString);
                }
            }
        }

        return count;
    }
}