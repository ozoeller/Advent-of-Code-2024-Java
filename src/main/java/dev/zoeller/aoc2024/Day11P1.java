package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * --- Day 11: Blinking Stones ---
 * Die Lösung wurde komplett mithilfe von GitHub Copilot erstellt. Der Beschreibungstext wurde von der AoC-Website 1:1
 * per Copy-Paste in das GitHub Copilot Chatfenster übertragen. Copilot hat daraus auf Anhieb den korrekten Code
 * generiert.
 */
public class Day11P1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input11.txt");
        String input = lines.getFirst();
        List<String> stones = List.of(input.split(" "));
        int blinks = 25;
        List<String> result = blinkStones(stones, blinks);
        System.out.println("Number of stones after " + blinks + " blinks: " + result.size()); // 189547
    }

    private static List<String> blinkStones(List<String> stones, int blinks) {
        List<String> currentStones = new ArrayList<>(stones);
        for (int i = 0; i < blinks; i++) {
            currentStones = processStones(currentStones);
        }
        return currentStones;
    }

    private static List<String> processStones(List<String> stones) {
        List<String> newStones = new ArrayList<>();
        for (String stone : stones) {
            if (stone.equals("0")) {
                newStones.add("1");
            } else if (stone.length() % 2 == 0) {
                int mid = stone.length() / 2;
                String left = stone.substring(0, mid);
                String right = stone.substring(mid);
                newStones.add(removeLeadingZeros(left));
                newStones.add(removeLeadingZeros(right));
            } else {
                long newStoneValue = Long.parseLong(stone) * 2024;
                newStones.add(String.valueOf(newStoneValue));
            }
        }
        return newStones;
    }

    private static String removeLeadingZeros(String str) {
        return str.replaceFirst("^0+(?!$)", "");
    }
}