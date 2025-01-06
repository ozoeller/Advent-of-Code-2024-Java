package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.zoeller.aoc2024.FileReader.readLines;

public class Day10P1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input10.txt");

        List<int[]> trailHeads = findTrailHeads(lines);
        Integer sum = trailHeads.stream().map(trailHead -> findNumberOfReachable9(trailHead, lines, new HashSet<>()))
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static int findNumberOfReachable9(int[] position, List<String> lines, Set<String> visited) {
        int numberAtPosition = Integer.parseInt(lines.get(position[1]).substring(position[0], position[0] + 1));
        if (numberAtPosition == 9) {
            String posKey = position[0] + "," + position[1];
            if (!visited.contains(posKey)) {
                visited.add(posKey);
                return 1;
            }
            return 0;
        }
        int numberOfPaths = 0;
        if (position[0] + 1 < lines.get(position[1]).length()) {
            int rightNumber = Integer.parseInt(lines.get(position[1]).substring(position[0] + 1, position[0] + 2));
            if (rightNumber - numberAtPosition == 1) {
                numberOfPaths += findNumberOfReachable9(new int[]{position[0] + 1, position[1]}, lines, visited);
            }
        }
        if (position[1] + 1 < lines.size()) {
            int downNumber = Integer.parseInt(lines.get(position[1] + 1).substring(position[0], position[0] + 1));
            if (downNumber - numberAtPosition == 1) {
                numberOfPaths += findNumberOfReachable9(new int[]{position[0], position[1] + 1}, lines, visited);
            }
        }
        if (position[0] - 1 >= 0) {
            int leftNumber = Integer.parseInt(lines.get(position[1]).substring(position[0] - 1, position[0]));
            if (leftNumber - numberAtPosition == 1) {
                numberOfPaths += findNumberOfReachable9(new int[]{position[0] - 1, position[1]}, lines, visited);
            }
        }
        if (position[1] - 1 >= 0) {
            int upNumber = Integer.parseInt(lines.get(position[1] - 1).substring(position[0], position[0] + 1));
            if (upNumber - numberAtPosition == 1) {
                numberOfPaths += findNumberOfReachable9(new int[]{position[0], position[1] - 1}, lines, visited);
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