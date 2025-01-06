package dev.zoeller.aoc2024;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReader {
    public static List<String> readLines(String resourceFilePath) throws IOException {
        return readToString(resourceFilePath).lines().filter(StringUtils::isNotEmpty).toList();
    }

    public static String readToString(String resourceFilePath) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(resourceFilePath).getFile());
        return FileUtils.readFileToString(file, "UTF-8");
    }
}
