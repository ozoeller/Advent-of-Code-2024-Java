package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Day02P2v2 {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> rows = readLines("dev/zoeller/aoc2024/input02.txt").stream()
                .map(line -> line.split(" "))
                .map(Day02P2v2::convertToIntegerList)
                .toList();
        long safeCount = rows.stream().filter(numbers -> isSafeListFound(numbers)).count();
        System.out.println("Count: " + safeCount);
    }

    private static List<Integer> convertToIntegerList(String[] strArr) {
        return Arrays.stream(strArr).map(Integer::parseInt).toList();
    }

    private static boolean isSafeListFound(List<Integer> numbers) {
        if (isSafe(numbers)) {
            return true;
        }
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> reducedList = new ArrayList<>(numbers);
            reducedList.remove(i);
            if (isSafe(reducedList)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafe(List<Integer> numbers) {
        Float oldSignum = null;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int diff = numbers.get(i+1) - numbers.get(i);
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
