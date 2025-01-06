package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Day02P2optimized {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> rows = readLines("dev/zoeller/aoc2024/input02.txt").stream()
                .map(line -> line.split(" "))
                .map(Day02P2optimized::convertToIntegerList)
                .toList();
        long safeCount = rows.stream().filter(numbers -> isSafe(numbers, true)).count();
        System.out.println("Count: " + safeCount);
    }

    private static List<Integer> convertToIntegerList(String[] strArr) {
        return Arrays.stream(strArr).map(Integer::parseInt).toList();
    }

    private static boolean isSafe(List<Integer> numbers, boolean canRemoveOne) {
        Float oldSignum = null;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int diff = numbers.get(i+1) - numbers.get(i);
            if (oldSignum == null) {
                oldSignum = signum(diff);
            }
            else if (signum(diff) != oldSignum) {
                return canRemoveOne && isSafeIfOneRemoved(numbers, i);
            }
            if (diff == 0 || abs(diff) > 3){
                return canRemoveOne && isSafeIfOneRemoved(numbers, i);
            }
        }
        return true;
    }

    private static boolean isSafeIfOneRemoved(List<Integer> numbers, int index) {
        // Removing index - 1 was mistakenly not considered in the original version.
        // However, this case is relevant when the direction changes at the 2nd position.
        // This is the case for the following two lines from 'input02.txt':
        // 57 55 56 58 59 62 65 68 (line 273).
        // 78 76 77 79 80 81 83 86 (line 643)
        // The list is safe if the 1st value is removed (57 or 78). However, the overall list is not safe,
        // which only becomes apparent when comparing the 2nd with the 3rd value (55 with 56 or 76 with 77) and noticing the
        // change in direction.
        for (int i = index - 1; i <= index + 1; i++) {
            if (isSafeIfRemoved(numbers, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafeIfRemoved(List<Integer> numbers, int index) {
        if (index < 0) {
            return false;
        }
        ArrayList<Integer> newList = new ArrayList<>(numbers);
        newList.remove(index);
        return isSafe(newList, false);
    }
}
