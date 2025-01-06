package dev.zoeller.aoc2024;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * --- Day 11: Blinking Stones, Part 2 ---
 */
public class Day11P2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input11.txt");
        String input = lines.get(0);
        Map<BigInteger, BigInteger> stones = new HashMap<>();
        for (String s : input.split(" ")) {
            BigInteger stone = new BigInteger(s);
            stones.put(stone, stones.getOrDefault(stone, BigInteger.ZERO).add(BigInteger.ONE));
        }
        int blinks = 75;
        blinkStones(stones, blinks);
        System.out.println("Number of stones after " + blinks + " blinks: " + stones.values().stream().reduce(BigInteger.ZERO, BigInteger::add));
        // 224577979481346, also rund 225 Billionen
    }

    private static void blinkStones(Map<BigInteger, BigInteger> stones, int blinks) {
        for (int i = 0; i < blinks; i++) {
            processStones(stones);
            System.out.println("Blink " + (i + 1) + ": " + stones.values().stream().reduce(BigInteger.ZERO, BigInteger::add));
        }
    }

    private static void processStones(Map<BigInteger, BigInteger> stones) {
        Map<BigInteger, BigInteger> newStones = new HashMap<>();
        for (Map.Entry<BigInteger, BigInteger> entry : stones.entrySet()) {
            BigInteger stone = entry.getKey();
            BigInteger count = entry.getValue();
            if (stone.equals(BigInteger.ZERO)) {
                newStones.put(BigInteger.ONE, newStones.getOrDefault(BigInteger.ONE, BigInteger.ZERO).add(count));
            } else if (stone.toString().length() % 2 == 0) {
                String stoneStr = stone.toString();
                int mid = stoneStr.length() / 2;
                BigInteger left = new BigInteger(stoneStr.substring(0, mid));
                BigInteger right = new BigInteger(stoneStr.substring(mid));
                newStones.put(left, newStones.getOrDefault(left, BigInteger.ZERO).add(count));
                newStones.put(right, newStones.getOrDefault(right, BigInteger.ZERO).add(count));
            } else {
                BigInteger newStoneValue = stone.multiply(BigInteger.valueOf(2024));
                newStones.put(newStoneValue, newStones.getOrDefault(newStoneValue, BigInteger.ZERO).add(count));
            }
        }
        stones.clear();
        stones.putAll(newStones);
    }
}