package adventofcode.day9;

import adventofcode.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract sealed class SmokeBasin implements Solver {

    @Override
    public String solve(List<String> input) {
        var heightmap = parseInput(input);

        int result = calculateResult(heightmap);

        return Integer.toString(result);
    }


    protected abstract int calculateResult(int[][] heightmap);

    private static boolean currentIsLowPoint(int[][] heightmap, int x, int y) {
        var current = heightmap[y][x];
        var top = y > 0 ? heightmap[y - 1][x] : Integer.MAX_VALUE;
        var bottom = y < heightmap.length - 1 ? heightmap[y + 1][x] : Integer.MAX_VALUE;
        var left = x > 0 ? heightmap[y][x - 1] : Integer.MAX_VALUE;
        var right = x < heightmap[y].length - 1 ? heightmap[y][x + 1] : Integer.MAX_VALUE;

        return current < top && current < bottom && current < left && current < right;
    }

    private static int heightToRiskLevel(int height) {
        return height + 1;
    }

    private static int[][] parseInput(List<String> input) {
        return input.stream()
            .map(line -> line.chars().map(c -> c - '0').toArray())
            .toArray(int[][]::new);
    }

    public static final class PartOne extends SmokeBasin {
        @Override
        protected int calculateResult(int[][] heightmap) {
            List<Integer> lowPoints = new ArrayList<>();
            for (int y = 0; y < heightmap.length; y++) {
                for (int x = 0; x < heightmap[y].length; x++) {
                    if (currentIsLowPoint(heightmap, x, y)) {
                        lowPoints.add(heightmap[y][x]);
                    }
                }
            }

            return lowPoints.stream()
                .mapToInt(Integer::valueOf)
                .map(SmokeBasin::heightToRiskLevel)
                .sum();
        }
    }

    public static final class PartTwo extends SmokeBasin {
        @Override
        protected int calculateResult(int[][] heightmap) {
            List<Integer> basinSizes = new ArrayList<>();
            for (int y = 0; y < heightmap.length; y++) {
                for (int x = 0; x < heightmap[y].length; x++) {
                    if (currentIsLowPoint(heightmap, x, y)) {
                        var sizeOfBasin = new FloodFillBasinSizeCalcluator(heightmap).sizeOfBasinWithLowPoint(new Point(x,
                            y));
                        basinSizes.add(sizeOfBasin);
                    }
                }
            }

            return basinSizes.stream()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::valueOf)
                .limit(3)
                .reduce((a, b) -> a * b)
                .orElse(0);
        }
    }

}
