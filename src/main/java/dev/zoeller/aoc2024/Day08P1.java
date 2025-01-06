package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * Komplett mithilfe von GitHub Copilot erstellt. Es wurde der komplette Input des Rätsels per Copy-Paste in den
 * GitHub Copilot Chat eingefügt und die Lösung wurde dann übernommen.
 */
public class Day08P1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/testInput08.txt");
        Set<String> antinodes = findAntinodes(lines);
        System.out.println(antinodes.size()); // 278
    }

    private static Set<String> findAntinodes(List<String> lines) {
        Set<String> antinodes = new HashSet<>();
        int rows = lines.size();
        int cols = lines.get(0).length();

        for (int row1 = 0; row1 < rows; row1++) {
            for (int col1 = 0; col1 < cols; col1++) {
                char freq1 = lines.get(row1).charAt(col1);
                if (freq1 != '.') {
                    for (int row2 = 0; row2 < rows; row2++) {
                        for (int col2 = 0; col2 < cols; col2++) {
                            if (row1 == row2 && col1 == col2) continue;
                            char freq2 = lines.get(row2).charAt(col2);
                            if (freq1 == freq2) {
                                int dx = row2 - row1;
                                int dy = col2 - col1;
                                int antinodeRow1 = row1 - dx;
                                int antinodeCol1 = col1 - dy;
                                int antinodeRow2 = row2 + dx;
                                int antinodeCol2 = col2 + dy;
                                if (isValid(antinodeRow1, antinodeCol1, rows, cols)) {
                                    antinodes.add(antinodeRow1 + "," + antinodeCol1);
                                }
                                if (isValid(antinodeRow2, antinodeCol2, rows, cols)) {
                                    antinodes.add(antinodeRow2 + "," + antinodeCol2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return antinodes;
    }

    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}