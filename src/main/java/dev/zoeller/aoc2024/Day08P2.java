package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
* Like part 1, part 2 was also created entirely with the help of GitHub Copilot.
* Additionally, some refactorings were made with the help of GitHub Copilot to make the code more readable.
* The solution is not optimal, as it has a complexity of O(n^4), but it works.
 */
public class Day08P2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input08.txt");
        Set<String> antinodes = findAntinodes(lines);
        System.out.println(antinodes.size()); // 1067
    }

    private static Set<String> findAntinodes(List<String> lines) {
        Set<String> antinodes = new HashSet<>();
        int rows = lines.size();
        int cols = lines.get(0).length();

        for (int row1 = 0; row1 < rows; row1++) {
            for (int col1 = 0; col1 < cols; col1++) {
                char freq1 = lines.get(row1).charAt(col1);
                if (freq1 != '.') {
                    Set<String> antennas = findAntennasOfFrequency(lines, rows, cols, row1, col1, freq1);
                    antinodes.addAll(antennas);
                    calculateAntinodePositions(antinodes, rows, cols, row1, col1, antennas);
                }
            }
        }
        return antinodes;
    }

    private static Set<String> findAntennasOfFrequency(List<String> lines, int rows, int cols, int row1, int col1, char freq1) {
        Set<String> antennas = new HashSet<>();
        for (int row2 = 0; row2 < rows; row2++) {
            for (int col2 = 0; col2 < cols; col2++) {
                if (row1 == row2 && col1 == col2) continue;
                char freq2 = lines.get(row2).charAt(col2);
                if (freq1 == freq2) {
                    antennas.add(row2 + "," + col2);
                }
            }
        }
        return antennas;
    }

    private static void calculateAntinodePositions(Set<String> antinodes, int rows, int cols, int row1, int col1, Set<String> antennas) {
        for (String antenna : antennas) {
            String[] parts = antenna.split(",");
            int row2 = Integer.parseInt(parts[0]);
            int col2 = Integer.parseInt(parts[1]);
            int dx = row2 - row1;
            int dy = col2 - col1;
            for (int multiplier = 1; multiplier <= Math.max(rows, cols); multiplier++) {
                int antinodeRow1 = row1 - multiplier * dx;
                int antinodeCol1 = col1 - multiplier * dy;
                int antinodeRow2 = row2 + multiplier * dx;
                int antinodeCol2 = col2 + multiplier * dy;
                if (isValid(antinodeRow1, antinodeCol1, rows, cols)) {
                    antinodes.add(antinodeRow1 + "," + antinodeCol1);
                }
                if (isValid(antinodeRow2, antinodeCol2, rows, cols)) {
                    antinodes.add(antinodeRow2 + "," + antinodeCol2);
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}