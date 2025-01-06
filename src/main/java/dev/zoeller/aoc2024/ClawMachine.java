package dev.zoeller.aoc2024;

class ClawMachine {
    private final long ax, ay, bx, by, px, py;

    public ClawMachine(long ax, long ay, long bx, long by, long px, long py) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.px = px;
        this.py = py;
    }

    public long pressButtons() {
        long det = ax * by - ay * bx;
        long a = (px * by - py * bx) / det;
        long b = (ax * py - ay * px) / det;
        if (ax * a + bx * b == px && ay * a + by * b == py) {
            return a * 3L + b;
        } else {
            return 0;
        }
    }
}