package dev.zoeller.aoc2024;

import java.util.ArrayList;
import java.util.List;

public class Day13Parser {
    public static List<ClawMachine> parseInput(String input, long prizeScale) {
        List<ClawMachine> machines = new ArrayList<>();
        String[] sections = input.split("\n\n");
        for (String section : sections) {
            String[] lines = section.split("\n");
            long[] a = parseButton(lines[0]);
            long[] b = parseButton(lines[1]);
            long[] prize = parsePrize(lines[2]);
            machines.add(new ClawMachine(a[0], a[1], b[0], b[1], prize[0] + prizeScale, prize[1] + prizeScale));
        }
        return machines;
    }

    private static long[] parseButton(String line) {
        String[] parts = line.split(", ");
        long x = Long.parseLong(parts[0].split("\\+")[1]);
        long y = Long.parseLong(parts[1].split("\\+")[1]);
        return new long[]{x, y};
    }

    private static long[] parsePrize(String line) {
        String[] parts = line.split(", ");
        long x = Long.parseLong(parts[0].split("=")[1]);
        long y = Long.parseLong(parts[1].split("=")[1]);
        return new long[]{x, y};
    }
}