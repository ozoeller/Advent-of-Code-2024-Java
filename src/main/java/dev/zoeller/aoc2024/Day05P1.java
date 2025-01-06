package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readToString;

public class Day05P1 {
    public static void main(String[] args) throws IOException {
        String string = readToString("dev/zoeller/aoc2024/input05.txt");
        String[] splitInput = string.split("\n\n");
        List<List<Integer>> orderRuleList = Arrays.stream(splitInput[0].split("\n"))
                .map(orderStringLine -> Arrays.stream(orderStringLine.split("\\|"))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
        List<List<Integer>> combinationList = Arrays.stream(splitInput[1].split("\n"))
                .map(s -> Arrays.stream(s.split(","))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        Integer sum = combinationList.stream()
                .filter(combination -> checkCombinationList(combination, orderRuleList))
                .map(Day05P1::getMiddleNumber)
                .reduce(0, Integer::sum);
        System.out.println("Summe: " + sum); // 6242
    }

    static boolean checkCombinationList(List<Integer> combinationList, List<List<Integer>> orderRuleList) {
        for (int numberIndex = 0; numberIndex < combinationList.size(); numberIndex++) {
            for (int followingNumberIndex = numberIndex + 1 ; followingNumberIndex < combinationList.size(); followingNumberIndex++) {
                for (int orderRuleIndex = 0; orderRuleIndex < orderRuleList.size(); orderRuleIndex++) {
                    if ((combinationList.get(numberIndex) == orderRuleList.get(orderRuleIndex).get(1) &&
                            combinationList.get(followingNumberIndex) == orderRuleList.get(orderRuleIndex).get(0))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static int getMiddleNumber(List<Integer> combinationList) {
        return combinationList.get(combinationList.size() / 2);
    }
}
