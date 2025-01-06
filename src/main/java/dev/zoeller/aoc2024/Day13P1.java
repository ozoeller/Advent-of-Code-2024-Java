package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 13: Claw Machine ---
 * Die Lösung hat GitHub Copilot vorgeschlagen, auf Basis des Inputs zu AoC 2024, Day 13, Part 1.
 * Diese "naive" Lösung funktioniert, ist jedoch für Teil 2 nicht geeignet.
 * Daher wurde Day13P1v2 entwickelt, um die Lösung zu verbessern.
 */
public class Day13P1 {
    public static void main(String[] args) throws IOException {
        String input = FileReader.readToString("dev/zoeller/aoc2024/input13.txt");
        List<ClawMachineV1> machines = parseInput(input);
        int totalTokens = calculateMinimumTokens(machines);
        System.out.println("Minimum tokens required: " + totalTokens);
    }

    private static List<ClawMachineV1> parseInput(String input) {
        List<ClawMachineV1> machines = new ArrayList<>();
        String[] sections = input.split("\n\n");
        for (String section : sections) {
            String[] lines = section.split("\n");
            int[] a = parseButton(lines[0]);
            int[] b = parseButton(lines[1]);
            int[] prize = parsePrize(lines[2]);
            machines.add(new ClawMachineV1(a[0], a[1], b[0], b[1], prize[0], prize[1]));
        }
        return machines;
    }

    private static int[] parseButton(String line) {
        String[] parts = line.split(", ");
        int x = Integer.parseInt(parts[0].split("\\+")[1]);
        int y = Integer.parseInt(parts[1].split("\\+")[1]);
        return new int[]{x, y};
    }

    private static int[] parsePrize(String line) {
        String[] parts = line.split(", ");
        int x = Integer.parseInt(parts[0].split("=")[1]);
        int y = Integer.parseInt(parts[1].split("=")[1]);
        return new int[]{x, y};
    }

    private static int calculateMinimumTokens(List<ClawMachineV1> machines) {
        int totalTokens = 0;
        for (ClawMachineV1 machine : machines) {
            int tokens = machine.calculateMinimumTokens();
            if (tokens != Integer.MAX_VALUE) {
                totalTokens += tokens;
            }
        }
        return totalTokens;
    }
}

class ClawMachineV1 {
    private final int ax, ay, bx, by, px, py;

    public ClawMachineV1(int ax, int ay, int bx, int by, int px, int py) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.px = px;
        this.py = py;
    }

    public int calculateMinimumTokens() {
        int minTokens = Integer.MAX_VALUE;
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100; b++) {
                if (a * ax + b * bx == px && a * ay + b * by == py) {
                    int tokens = a * 3 + b;
                    minTokens = Math.min(minTokens, tokens);
                }
            }
        }
        return minTokens;
    }
}