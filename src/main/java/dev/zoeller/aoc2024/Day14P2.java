package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day14P2 {
    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    public static void main(String[] args) throws IOException {
        String input = FileReader.readToString("dev/zoeller/aoc2024/input14.txt");
        List<Robot> robots = parseInput(input);
        int seconds = findAlignment(robots);
        System.out.println("Fewest number of seconds: " + seconds);
        printGrid(robots);
    }

    private static List<Robot> parseInput(String input) {
        List<Robot> robots = new ArrayList<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] parts = line.split(" ");
            String[] pos = parts[0].substring(2).split(",");
            String[] vel = parts[1].substring(2).split(",");
            int px = Integer.parseInt(pos[0]);
            int py = Integer.parseInt(pos[1]);
            int vx = Integer.parseInt(vel[0]);
            int vy = Integer.parseInt(vel[1]);
            robots.add(new Robot(px, py, vx, vy));
        }
        return robots;
    }

    private static int findAlignment(List<Robot> robots) {
        int seconds = 0;
        while (true) {
            simulateMovement(robots, 1);
            seconds++;
            if (isAligned(robots)) {
                break;
            }
        }
        return seconds;
    }

    private static void simulateMovement(List<Robot> robots, int seconds) {
        for (Robot robot : robots) {
            for (int i = 0; i < seconds; i++) {
                robot.move(WIDTH, HEIGHT);
            }
        }
    }

    private static boolean isAligned(List<Robot> robots) {
        int robotsWithNeighbors = 0;
        for (Robot robot : robots) {
            int neighbors = 0;
            for (Robot other : robots) {
                if (robot != other && isNeighbor(robot, other)) {
                    neighbors++;
                }
            }
            if (neighbors >= 2) {
                robotsWithNeighbors++;
            }
        }
        return robotsWithNeighbors >= robots.size() / 2;
    }

    private static boolean isNeighbor(Robot r1, Robot r2) {
        int dx = Math.abs(r1.x - r2.x);
        int dy = Math.abs(r1.y - r2.y);
        return (dx <= 1 && dy <= 1);
    }

    private static void printGrid(List<Robot> robots) {
        char[][] grid = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = '.';
            }
        }
        for (Robot robot : robots) {
            grid[robot.y][robot.x] = '#';
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    static class Robot {
        int x, y, vx, vy;

        Robot(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        void move(int width, int height) {
            x = (x + vx + width) % width;
            y = (y + vy + height) % height;
        }
    }
}