package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;
import static java.lang.Math.abs;

public class Day01Part1 {
    public static void main(String[] args) throws IOException {
        List<String[]> rows = readLines("dev/zoeller/aoc2024/input01.txt").stream()
                .map(line -> line.split(" {3}"))
                .toList();
        List<Integer> col1 = rows.stream().map(row -> row[0]).map(Integer::parseInt).sorted().toList();
        List<Integer> col2 = rows.stream().map(row -> row[1]).map(Integer::parseInt).sorted().toList();
        long distance = 0;
        for (int i = 0; i < rows.size(); i++) {
            distance += abs(col1.get(i) - col2.get(i));
        }
        System.out.println(distance);
    }
}