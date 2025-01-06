package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Day02P1 {
    public static void main(String[] args) throws IOException {
        List<String[]> rows = readLines("dev/zoeller/aoc2024/input02.txt").stream()
                .map(line -> line.split(" "))
                .toList();
        long safeCount = rows.stream().filter(Day02P1::isSafe).count();
        System.out.println(safeCount);
    }

    private static boolean isSafe(String[] numbers) {
        Float oldSignum = null;
        for (int i = 0; i < numbers.length - 1; i++) {
            int diff = parseInt(numbers[i + 1]) - parseInt(numbers[i]);
            if (oldSignum == null) {
                oldSignum = signum(diff);
            }
            else if (signum(diff) != oldSignum) {
                return false;
            }
            if (diff == 0 || abs(diff) > 3){
                return false;
            }
        }
        return true;
    }
}
