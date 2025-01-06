package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.zoeller.aoc2024.FileReader.readLines;

public class Day09P2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readLines("dev/zoeller/aoc2024/input09.txt");
        String diskMap = lines.get(0);
        int[] compactedDisk = compactDisk(diskMap);
        long checksum = calculateChecksum(compactedDisk);
        System.out.println("Checksum: " + checksum); // 6390781891880
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
        for (int fileId = diskSize - 1; fileId >= 0; fileId--) {
            int fileLength = 0;
            int fileStart = -1;
            for (int i = 0; i < diskSize; i++) {
                if (disk.get(i) == fileId) {
                    if (fileStart == -1) {
                        fileStart = i;
                    }
                    fileLength++;
                } else if (fileStart != -1) {
                    break;
                }
            }
            if (fileStart == -1) {
                continue;
            }
            int freeSpaceStart = -1;
            int freeSpaceLength = 0;
            for (int i = 0; i < fileStart; i++) {
                if (disk.get(i) == -1) {
                    if (freeSpaceStart == -1) {
                        freeSpaceStart = i;
                    }
                    freeSpaceLength++;
                    if (freeSpaceLength == fileLength) {
                        break;
                    }
                } else {
                    freeSpaceStart = -1;
                    freeSpaceLength = 0;
                }
            }
            if (freeSpaceLength >= fileLength) {
                for (int i = 0; i < fileLength; i++) {
                    disk.set(freeSpaceStart + i, fileId);
                    disk.set(fileStart + i, -1);
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