package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.zoeller.aoc2024.FileReader.readLines;

public class Day10P2 {
    private static final List<int[]> DIRECTIONS = List.of(new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1});
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input10.txt");

        List<int[]> trailHeads = findTrailHeads(lines);
        Integer sum = trailHeads.stream().map(trailHead -> findNumberOfReachable9(trailHead, lines))
                .reduce(0, Integer::sum);
        System.out.println(sum); // 644
    }

    private static int findNumberOfReachable9(int[] position, List<String> lines) {
        int numberAtPosition = Integer.parseInt(lines.get(position[1]).substring(position[0], position[0] + 1));
        if (numberAtPosition == 9) {
                return 1;
        }
        int numberOfPaths = 0;
        for (int[] direction : DIRECTIONS) {
            int newX = position[0] + direction[0];
            int newY = position[1] + direction[1];
            if (newX >= 0 && newX < lines.get(0).length() && newY >= 0 && newY < lines.size()) {
                int nextNumber = Character.getNumericValue(lines.get(newY).charAt(newX));
                if (nextNumber - numberAtPosition == 1) {
                    numberOfPaths += findNumberOfReachable9(new int[]{newX, newY}, lines);
                }
            }
        }
        return numberOfPaths;
    }
    private static List<int[]> findTrailHeads(List<String> lines) {
        List<int[]> trailHeads = new ArrayList<>();
        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (lines.get(row).charAt(col) == '0') {
                    trailHeads.add(new int[]{col, row});
                }
            }
        }
        return trailHeads;
    }
}