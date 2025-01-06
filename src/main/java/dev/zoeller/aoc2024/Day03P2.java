package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.zoeller.aoc2024.FileReader.readToString;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Long.sum;

public class Day03P2 {
    public static void main(String[] args) throws IOException {
        String string = readToString("dev/zoeller/aoc2024/input03.txt");
        Pattern startPattern = Pattern.compile("^(.*?)don't[(][)]");
        // Achtung: input03.txt ist mehrzeilig!
        // Das folgende Pattern funktioniert nur über Zeilengrenzen hinweg, wenn das Pattern.DOTALL-Flag genutzt wird.
        // Andere Lösungsmöglichkeit: Zeilenbegrenzungen der Input-Datei entweder manuell entfernen,
        // oder programmatisch, vor dem Regex-Matching.
        Pattern enabledPattern = Pattern.compile("do[(][)](.*?)don't[(][)]", Pattern.DOTALL);
        Pattern endPattern = Pattern.compile(".*don't[(][)].*?do[(][)](.*?)$");

        ArrayList<String> strings = new ArrayList<>();

        Matcher startMatcher = startPattern.matcher(string);
        if (startMatcher.find()) {
            strings.add(startMatcher.group(1));
        }

        Matcher enabledMatcher = enabledPattern.matcher(string);
        while (enabledMatcher.find()) {
            strings.add(enabledMatcher.group(1));
        }

        Matcher endMatcher = endPattern.matcher(string);
        while (endMatcher.find()) {
            strings.add(endMatcher.group(1));
        }

        String stringToSearch = String.join("\n", strings);

        Pattern mulPattern = Pattern.compile("mul[(](\\d{1,3}),(\\d{1,3})[)]");
        Matcher mulMatcher = mulPattern.matcher(stringToSearch);
        long result = 0;
        while (mulMatcher.find()) {
            result += parseLong(mulMatcher.group(1)) * parseLong(mulMatcher.group(2));
        }
        System.out.println(result);
        // 82857512
    }
}
