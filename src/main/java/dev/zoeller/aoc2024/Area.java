package dev.zoeller.aoc2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.compare;

public class Area {
    private static final List<int[]> DIRECTIONS = List.of(new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0});
    private final List<String> lines;
    private Set<Coordinate> coordinates;
    private char areaChar;

    public Area(char areaChar, List<String> lines) {
        this.lines = lines;
        this.coordinates = new HashSet<>();
        this.areaChar = areaChar;
    }

    private boolean isValidPosition(int newRow, int newCol) {
        return newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(newRow).length();
    }

    /**
     * Use a recursive method to explore all neighboring cells that belong to the area.
     * It is a depth-first search that stores the coordinates of the cells that have already been visited.
     */
    public void exploreNeighbours(int row, int col) {
        Coordinate coordinate = new Coordinate(row, col);
        if (coordinates.contains(coordinate)) {
            return;
        }
        coordinates.add(coordinate);
        for (int[] direction : DIRECTIONS) {
            int exploredRow = row + direction[0];
            int exploredCol = col + direction[1];
            if (isValidPosition(exploredRow, exploredCol)) {
                char exploredChar = getCharAt(exploredRow, exploredCol);
                if (exploredChar == areaChar) {
                    exploreNeighbours(exploredRow, exploredCol);
                }
            }
        }
    }

    private char getCharAt(int row, int col) {
        return lines.get(row).charAt(col);
    }

    public boolean contains(int row, int col) {
        return coordinates.contains(new Coordinate(row, col));
    }

    public int getSize() {
        return coordinates.size();
    }

    public int calculatePerimeter() {
        int perimeter = 0;
        for (Coordinate coordinate : coordinates) {
            perimeter += calculatePerimeter(coordinate);
        }
        return perimeter;
    }

    private int calculatePerimeter(Coordinate coordinate) {
        int perimeter = 0;
        for (int[] direction : DIRECTIONS) {
            int newRow = coordinate.row() + direction[0];
            int newCol = coordinate.col() + direction[1];
            if (isValidPosition(newRow, newCol)) {
                char newC = getCharAt(newRow, newCol);
                if (newC != areaChar) {
                    perimeter++;
                }
            }
            else {
                perimeter++;
            }
        }
        return perimeter;
    }

    public int calculateSides() {
        List<Coordinate> eastSide = new ArrayList<>();
        List<Coordinate> southSide = new ArrayList<>();
        List<Coordinate> westSide = new ArrayList<>();
        List<Coordinate> northSide = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            if (isOuterSide(coordinate, 0, 1)) {
                eastSide.add(coordinate);
            }
            if (isOuterSide(coordinate, 1, 0)) {
                southSide.add(coordinate);
            }
            if (isOuterSide(coordinate, 0, -1)) {
                westSide.add(coordinate);
            }
            if (isOuterSide(coordinate, -1, 0)) {
                northSide.add(coordinate);
            }
        }

        sortCoordinates(eastSide, true);
        sortCoordinates(southSide, false);
        sortCoordinates(westSide, true);
        sortCoordinates(northSide, false);

        int eastSegments = countContinuousSegments(eastSide);
        int southSegments = countContinuousSegments(southSide);
        int westSegments = countContinuousSegments(westSide);
        int northSegments = countContinuousSegments(northSide);

        return eastSegments + southSegments + westSegments + northSegments;
    }

    private boolean isOuterSide(Coordinate coordinate, int rowOffset, int colOffset) {
        int newRow = coordinate.row() + rowOffset;
        int newCol = coordinate.col() + colOffset;
        return !isValidPosition(newRow, newCol) || !coordinates.contains(new Coordinate(newRow, newCol));
    }

    private void sortCoordinates(List<Coordinate> side, boolean sortByColumnFirst) {
        side.sort((c1, c2) -> {
            if (sortByColumnFirst) {
                if (c1.col() == c2.col()) {
                    return compare(c1.row(), c2.row());
                }
                return compare(c1.col(), c2.col());
            } else {
                if (c1.row() == c2.row()) {
                    return compare(c1.col(), c2.col());
                }
                return compare(c1.row(), c2.row());
            }
        });
    }
    private int countContinuousSegments(List<Coordinate> side) {
        if (side.isEmpty()) {
            return 0;
        }

        int segments = 1;
        for (int i = 1; i < side.size(); i++) {
            Coordinate prev = side.get(i - 1);
            Coordinate curr = side.get(i);
            if (!areNeighbors(prev, curr)) {
                segments++;
            }
        }
        return segments;
    }

    private boolean areNeighbors(Coordinate c1, Coordinate c2) {
        return (Math.abs(c1.row() - c2.row()) == 1 && c1.col() == c2.col()) || (Math.abs(c1.col() - c2.col()) == 1 && c1.row() == c2.row());
    }
}