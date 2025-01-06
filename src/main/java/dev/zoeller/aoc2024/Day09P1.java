package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

/**
 * The following code was again created with the help of GitHub Copilot.
 * Several iterations were necessary to find the correct solution. The code was adjusted and improved multiple times.
 * Initially, the IDs were not processed correctly because IDs greater than 10 could not be handled with Strings.
 * Instead, it was switched to Integer. Furthermore, compactDisk was adjusted to return an int array
 * instead of a String.
 * The calculateChecksum method also had to be adjusted because an overflow occurred when summing with Integer.
 * Therefore, it was switched to long.
 */
public class Day09P1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input09.txt");
        String diskMap = lines.getFirst();
        int[] compactedDisk = compactDisk(diskMap);
        long checksum = calculateChecksum(compactedDisk);
        System.out.println("Checksum: " + checksum); // 6367087064415
    }

    private static int[] compactDisk(String diskMap) {
        List<Integer> disk = parseDiskMap(diskMap);
        compactDiskBlocks(disk);
        return convertListToArray(disk);
    }

    private static List<Integer> parseDiskMap(String diskMap) {
        List<Integer> disk = new ArrayList<>();
        int fileId = 0;
        int index = 0;
        while (index < diskMap.length()) {
            int fileLength = Character.getNumericValue(diskMap.charAt(index++));
            int freeSpaceLength = (index < diskMap.length()) ? Character.getNumericValue(diskMap.charAt(index++)) : 0;
            for (int fileIndex = 0; fileIndex < fileLength; fileIndex++) {
                disk.add(fileId);
            }
            for (int freeSpaceIndex = 0; freeSpaceIndex < freeSpaceLength; freeSpaceIndex++) {
                disk.add(-1); // Use -1 to represent free space
            }
            fileId++;
        }
        return disk;
    }

    private static void compactDiskBlocks(List<Integer> disk) {
        int diskSize = disk.size();
        for (int currentIndex = diskSize - 1; currentIndex >= 0; currentIndex--) {
            if (disk.get(currentIndex) != -1) {
                int freeSpaceIndex = 0;
                while (freeSpaceIndex < currentIndex && disk.get(freeSpaceIndex) != -1) {
                    freeSpaceIndex++;
                }
                if (freeSpaceIndex < currentIndex) {
                    disk.set(freeSpaceIndex, disk.get(currentIndex));
                    disk.set(currentIndex, -1);
                }
            }
        }
    }

    private static int[] convertListToArray(List<Integer> disk) {
        int[] compactedDisk = new int[disk.size()];
        for (int compactedIndex = 0; compactedIndex < disk.size(); compactedIndex++) {
            compactedDisk[compactedIndex] = disk.get(compactedIndex);
        }
        return compactedDisk;
    }

    private static long calculateChecksum(int[] disk) {
        long checksum = 0;
        for (int i = 0; i < disk.length; i++) {
            if (disk[i] != -1) {
                checksum += (long) i * disk[i];
            }
        }
        return checksum;
    }
}