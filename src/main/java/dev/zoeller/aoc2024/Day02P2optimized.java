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
        // Entfernen von index - 1 wurde in der ursprünglichen Version fälschlicherweise nicht berücksichtigt.
        // Der Fall ist jedoch relevant, wenn an der 2. Stelle die Richtung gewechselt wird.
        // Dies ist bei folgenden beiden Zeilen von 'input02.txt' der Fall:
        // 57 55 56 58 59 62 65 68 (Zeile 273).
        // 78 76 77 79 80 81 83 86 (Zeile 643)
        // Die Liste ist safe, wenn der 1. Wert entfernt wird (57, bzw. 78). Dass die Gesamtliste nicht safe ist,
        // fällt jedoch erst auf, wenn der 2. mit dem 3. Wert verglichen wird (55 mit 56 bzw. 76 mit 77) und dabei der
        // Richtungswechsel auffällt.
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
