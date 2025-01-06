package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static dev.zoeller.aoc2024.FileReader.readLines;

public class Day01Part2 {
    public static void main(String[] args) throws IOException {
        List<String[]> rows = readLines("dev/zoeller/aoc2024/input01.txt").stream()
                .map(line -> line.split(" {3}"))
                .toList();
        List<Integer> col1 = rows.stream().map(row -> row[0]).map(Integer::parseInt).sorted().toList();
        List<Integer> col2 = rows.stream().map(row -> row[1]).map(Integer::parseInt).sorted().toList();

        Map<Integer, Long> map = col2.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long result = col1.stream().map(i -> i * (map.get(i) == null ? 0 : map.get(i))).reduce(0L, Long::sum);
        System.out.println(result);
    }
}