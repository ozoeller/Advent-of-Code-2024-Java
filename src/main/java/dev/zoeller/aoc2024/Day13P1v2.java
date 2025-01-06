package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.List;

/**
 * Diese Lösung basiert auf dem Lösungsvorschlag aus folgendem Blog-Eintrag:
 * <a href="https://todd.ginsberg.com/post/advent-of-code/2024/day13/">Advent of Code 2024 - Day 13, in Kotlin - Claw Contraption</a>
 * Die Lösung wurde in Java übersetzt und angepasst.
 * Vorteil: Die Lösung ist für Part 2 geeignet.
 * Mathematische Erklärung:
 * <a href="https://www.reddit.com/r/adventofcode/comments/1hd7irq/2024_day_13_an_explanation_of_the_mathematics/">[2024 Day 13] An explanation of the mathematics</a>
 */
public class Day13P1v2 {
    public static void main(String[] args) throws IOException {
        String input = FileReader.readToString("dev/zoeller/aoc2024/testInput13.txt");
        List<ClawMachine> machines = Day13Parser.parseInput(input, 0);
        long totalTokens = calculateMinimumTokens(machines);
        System.out.println("Minimum tokens required: " + totalTokens);
    }

    private static long calculateMinimumTokens(List<ClawMachine> machines) {
        return machines.stream().map(ClawMachine::pressButtons).reduce(Long::sum).orElse(0L);
    }
}