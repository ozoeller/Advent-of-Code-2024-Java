package dev.zoeller.aoc2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 14: Restroom Redoubt, Part 1 ---
 * Part 1 of this puzzle was solved completely by GitHub Copilot. I copied the puzzle input to the GitHub Copilot prompt,
 * and it generated the solution.
 */
public class Day14P1 {
    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;
    private static final int SECONDS = 100;

    public static void main(String[] args) throws IOException {
        String input = FileReader.readToString("dev/zoeller/aoc2024/input14.txt");
        List<Robot> robots = parseInput(input);
        simulateMovement(robots, SECONDS);
        int safetyFactor = calculateSafetyFactor(robots);
        System.out.println("Safety factor: " + safetyFactor);
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

    private static void simulateMovement(List<Robot> robots, int seconds) {
        for (Robot robot : robots) {
            for (int i = 0; i < seconds; i++) {
                robot.move(WIDTH, HEIGHT);
            }
        }
    }

    private static int calculateSafetyFactor(List<Robot> robots) {
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (Robot robot : robots) {
            if (robot.x > 50 && robot.y < 51) q1++;
            else if (robot.x < 50 && robot.y < 51) q2++;
            else if (robot.x < 50 && robot.y > 51) q3++;
            else if (robot.x > 50 && robot.y > 51) q4++;
        }
        return q1 * q2 * q3 * q4;
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