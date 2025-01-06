package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.zoeller.aoc2024.FileReader.readLines;
import static dev.zoeller.aoc2024.FileReader.readToString;
import static java.lang.Long.parseLong;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Day03P1 {
    public static void main(String[] args) throws IOException {
        String string = readToString("dev/zoeller/aoc2024/input03.txt");
        Pattern pattern = Pattern.compile("mul[(](\\d{1,3}),(\\d{1,3})[)]");
        Matcher matcher = pattern.matcher(string);
        long result = 0;
        while (matcher.find()) {
            result += parseLong(matcher.group(1)) * parseLong(matcher.group(2));
        }
        System.out.println(result);
    }
}
