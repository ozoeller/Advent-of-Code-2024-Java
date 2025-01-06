package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * --- Day 12: Garden Groups, Part 2 ---
 * As in part one, I developed the solution approach myself and implemented it with the help of GitHub Copilot.
 */
public class Day12P2 {

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