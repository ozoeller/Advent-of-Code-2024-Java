package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.regex.Pattern;

import static dev.zoeller.aoc2024.FileReader.readToString;

/**
 * Mit dieser Klasse wird die Lösung für den ersten Teil des vierten Tages berechnet.
 * Die Lösung wurde mithilfe von GitHub Copilot erstellt.
 */
public class Day04P1 {
    public static void main(String[] args) throws IOException {
        String string = readToString("dev/zoeller/aoc2024/input04.txt");
        String stringToSearch = "XMAS";

        long count = countOccurrences(string, stringToSearch);
        count += countVerticalOccurrences(string, stringToSearch);
        count += countVerticalReverseOccurrences(string, stringToSearch);
        count += countHorizontalReverseOccurrences(string, stringToSearch);
        count += countDiagonalOccurrences(string, stringToSearch);

        System.out.println("Anzahl der Treffer: " + count); // 2642
    }

    private static long countOccurrences(String string, String stringToSearch) {
        Pattern pattern = Pattern.compile(Pattern.quote(stringToSearch));
        return pattern.matcher(string).results().count();
    }

    private static long countVerticalOccurrences(String string, String stringToSearch) {
        String[] lines = string.split("\n");
        int size = lines.length;
        long count = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - stringToSearch.length(); j++) {
                StringBuilder vertical = new StringBuilder();
                for (int k = 0; k < stringToSearch.length(); k++) {
                    vertical.append(lines[j + k].charAt(i));
                }
                count += countOccurrences(vertical.toString(), stringToSearch);
            }
        }

        return count;
    }

    private static long countVerticalReverseOccurrences(String string, String stringToSearch) {
        String[] lines = string.split("\n");
        int size = lines.length;
        long count = 0;

        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= stringToSearch.length() - 1; j--) {
                StringBuilder vertical = new StringBuilder();
                for (int k = 0; k < stringToSearch.length(); k++) {
                    vertical.append(lines[j - k].charAt(i));
                }
                count += countOccurrences(vertical.toString(), stringToSearch);
            }
        }

        return count;
    }

    private static long countHorizontalReverseOccurrences(String string, String stringToSearch) {
        String[] lines = string.split("\n");
        long count = 0;

        for (String line : lines) {
            StringBuilder reversedLine = new StringBuilder(line).reverse();
            count += countOccurrences(reversedLine.toString(), stringToSearch);
        }

        return count;
    }

    private static long countDiagonalOccurrences(String string, String stringToSearch) {
        String[] lines = string.split("\n");
        int size = lines.length;
        long count = 0;

        // Check diagonals from top-left to bottom-right
        for (int i = 0; i <= size - stringToSearch.length(); i++) {
            for (int j = 0; j <= size - stringToSearch.length(); j++) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; k < stringToSearch.length(); k++) {
                    diagonal.append(lines[i + k].charAt(j + k));
                }
                count += countOccurrences(diagonal.toString(), stringToSearch);
            }
        }

        // Check diagonals from top-right to bottom-left
        for (int i = 0; i <= size - stringToSearch.length(); i++) {
            for (int j = size - 1; j >= stringToSearch.length() - 1; j--) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; k < stringToSearch.length(); k++) {
                    diagonal.append(lines[i + k].charAt(j - k));
                }
                count += countOccurrences(diagonal.toString(), stringToSearch);
            }
        }

        // Check diagonals from bottom-left to top-right
        for (int i = size - 1; i >= stringToSearch.length() - 1; i--) {
            for (int j = 0; j <= size - stringToSearch.length(); j++) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; k < stringToSearch.length(); k++) {
                    diagonal.append(lines[i - k].charAt(j + k));
                }
                count += countOccurrences(diagonal.toString(), stringToSearch);
            }
        }

        // Check diagonals from bottom-right to top-left
        for (int i = size - 1; i >= stringToSearch.length() - 1; i--) {
            for (int j = size - 1; j >= stringToSearch.length() - 1; j--) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; k < stringToSearch.length(); k++) {
                    diagonal.append(lines[i - k].charAt(j - k));
                }
                count += countOccurrences(diagonal.toString(), stringToSearch);
            }
        }

        return count;
    }
}