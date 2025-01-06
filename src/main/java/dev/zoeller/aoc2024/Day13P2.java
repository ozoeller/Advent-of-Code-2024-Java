package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.List;

public class Day13P2 {
    public static void main(String[] args) throws IOException {
        String input = FileReader.readToString("dev/zoeller/aoc2024/input13.txt");
        long prizeScale = 10000000000000L;
        List<ClawMachine> machines = Day13Parser.parseInput(input, prizeScale);
        long totalTokens = calculateMinimumTokens(machines);
        System.out.println("Minimum tokens required: " + totalTokens); // 73267584326867
    }

    private static long calculateMinimumTokens(List<ClawMachine> machines) {
        return machines.stream().map(ClawMachine::pressButtons).reduce(Long::sum).orElse(0L);
    }
}