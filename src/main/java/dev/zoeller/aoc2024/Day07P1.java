package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * The solution was created with the help of GitHub Copilot. Only the basic structure in the main method and the
 * method-declaration of solveEquation were written by me.
 */
public class Day07P1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/testInput07.txt");
        Long sum = lines.stream()
                .map(line -> line.split(": "))
                .map(arr -> solveEquation(Long.parseLong(arr[0]), Arrays.stream(arr[1].split(" "))
                        .map(Integer::parseInt).toList()))
                .reduce(0L, Long::sum);

        System.out.println(sum); // 2664460013123
    }

    public static long solveEquation(Long result, List<Integer> integerList) {
        return evaluate(integerList, 0, integerList.get(0), result) ? result : 0;
    }

    private static boolean evaluate(List<Integer> integerList, int index, long currentResult, long targetResult) {
        if (index == integerList.size() - 1) {
            return currentResult == targetResult;
        }

        int nextValue = integerList.get(index + 1);

        // Try addition
        if (evaluate(integerList, index + 1, currentResult + nextValue, targetResult)) {
            return true;
        }

        // Try multiplication
        if (evaluate(integerList, index + 1, currentResult * nextValue, targetResult)) {
            return true;
        }

        return false;
    }
}