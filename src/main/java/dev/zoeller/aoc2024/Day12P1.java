package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static dev.zoeller.aoc2024.Areas.findAreas;
import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * --- Day 12: Garden Groups ---
 * This puzzle was a real fun to solve.
 * I solved it mostly on my own, with a little help from GitHub Copilot.
 */
public class Day12P1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input12.txt");
        HashMap<Character, List<Area>> map = findAreas(lines);
        map.values().stream().flatMap(List::stream).map(area -> area.getSize() * area.calculatePerimeter()).reduce(Integer::sum).ifPresent(System.out::println);
        // 1573474
    }
}
