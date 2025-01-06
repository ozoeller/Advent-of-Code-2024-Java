package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

public class Day12P2 {
    private static final List<int[]> DIRECTIONS = List.of(new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0});

    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input12.txt");
        HashMap<Character, List<Area>> map = Areas.findAreas(lines);

        int totalProductSum = 0;
        for (Character c : map.keySet()) {
            for (Area area : map.get(c)) {
                int size = area.getSize();
                int sides = area.calculateSides();
                int product = size * sides;
                totalProductSum += product;
                System.out.printf("Character: %c, Size: %d, Sides: %d, Product: %d%n", c, size, sides, product);
            }
        }
        System.out.printf("Total Product Sum: %d%n", totalProductSum); // 966476
    }
}