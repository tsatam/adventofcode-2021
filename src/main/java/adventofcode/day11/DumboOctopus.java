package adventofcode.day11;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Day(11)
public abstract sealed class DumboOctopus implements Solver {
    @Override
    public String solve(List<String> input) {
        if(input.isEmpty()) {
            return "0";
        }
        var stepSimulator = new StepSimulator(input);
        var totalFlashes = calculateResult(stepSimulator);
        return Long.toString(totalFlashes);
    }

    abstract long calculateResult(StepSimulator simulator);

    @Part(1)
    public static final class PartOne extends DumboOctopus {

        @Override
        long calculateResult(StepSimulator simulator) {
            for (int i = 0; i < 100; i++) {
                simulator.simulateStep();
            }
            return simulator.getTotalFlashes();
        }
    }

    @Part(2)
    public static final class PartTwo extends DumboOctopus {
        @Override
        long calculateResult(StepSimulator simulator) {
            for (int step = 1; step < Integer.MAX_VALUE; step++) {
                var flashesThisStep = simulator.simulateStep();
                if(flashesThisStep == 100) {
                    return step;
                }
            }
            return 0;
        }
    }

    static class StepSimulator {
        private final int[][] grid;
        private final Stack<Point> flashesToSimulate = new Stack<>();
        private long totalFlashes = 0;

        public StepSimulator(List<String> input) {
            this.grid = input.stream()
                    .map(row -> row.chars().map(c -> c - '0').toArray())
                    .toArray(int[][]::new);
        }

        public long getTotalFlashes() {
            return totalFlashes;
        }

        private int simulateStep() {
            var flashedOctopi = new ArrayList<Point>();
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    var point = new Point(x, y);
                    increaseEnergy(point);
                }
            }
            while (!flashesToSimulate.isEmpty()) {
                var flash = flashesToSimulate.pop();
                flashedOctopi.add(flash);
                simulateFlash(flash);
            }
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] > 9) {
                        grid[y][x] = 0;
                    }
                }
            }
            return flashedOctopi.size();
        }

        private void increaseEnergy(Point point) {
            if (inBounds(point) && ++grid[point.y()][point.x()] == 10) {
                flashesToSimulate.push(point);
            }
        }

        private void simulateFlash(Point point) {
            totalFlashes++;

            increaseEnergy(point.north());
            increaseEnergy(point.northEast());
            increaseEnergy(point.east());
            increaseEnergy(point.southEast());
            increaseEnergy(point.south());
            increaseEnergy(point.southWest());
            increaseEnergy(point.west());
            increaseEnergy(point.northWest());
        }

        private boolean inBounds(Point point) {
            return point.x() >= 0 &&
                    point.y() >= 0 &&
                    point.y() < grid.length &&
                    point.x() < grid[point.y()].length;
        }
    }


    record Point(int x, int y) {
        public Point north() {
            return new Point(x, y - 1);
        }

        public Point northEast() {
            return new Point(x + 1, y - 1);
        }

        public Point east() {
            return new Point(x + 1, y);
        }

        public Point southEast() {
            return new Point(x + 1, y + 1);
        }

        public Point south() {
            return new Point(x, y + 1);
        }

        public Point southWest() {
            return new Point(x - 1, y + 1);
        }

        public Point west() {
            return new Point(x - 1, y);
        }

        public Point northWest() {
            return new Point(x - 1, y - 1);
        }
    }
}
