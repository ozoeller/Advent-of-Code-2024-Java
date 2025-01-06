package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.regex.Pattern;

import static dev.zoeller.aoc2024.FileReader.readToString;
import static java.lang.Long.parseLong;

public class Day03P2v2 {
    public static void main(String[] args) throws IOException {
        String string = readToString("dev/zoeller/aoc2024/input03.txt");

        String stringToSearch = Pattern.compile("don't[(][)].*?do[(][)]", Pattern.DOTALL)
                .matcher(string).replaceAll("");

        Long result = Pattern.compile("mul[(](\\d{1,3}),(\\d{1,3})[)]").matcher(stringToSearch).results()
                .map(matchResult -> parseLong(matchResult.group(1)) * parseLong(matchResult.group(2)))
                .reduce(0L, Long::sum);

        System.out.println(result);
        // 82857512
    }
}
