package adventofcode.day15;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;
import adventofcode.common.Board;
import adventofcode.common.Point;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

@Day(15)
public abstract sealed class Chiton implements Solver {
    @Override
    public String solve(List<String> input) {
        var board = fromInput(input);
        var lowestRisk = findLowestRisk(board);
        return Integer.toString(lowestRisk);
    }

    protected abstract Board fromInput(List<String> input);

    @Part(1)
    public static final class PartOne extends Chiton {
        @Override
        protected Board fromInput(List<String> input) {
            var points = input.stream()
                .map(s -> s.chars().map(c -> c - '0').toArray())
                .toArray(int[][]::new);

            return new Board(points);
        }
    }

    @Part(2)
    public static final class PartTwo extends Chiton {
        @Override
        protected Board fromInput(List<String> input) {
            var oldPoints = input.stream()
                .map(s -> s.chars().map(c -> c - '0').toArray())
                .toArray(int[][]::new);

            var height = oldPoints.length;
            var width = oldPoints[0].length;
            var newPoints = new int[height * 5][width * 5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int additionFactor = i + j;

                    for (int oldY = 0; oldY < height; oldY++) {
                        for (int oldX = 0; oldX < width; oldX++) {
                            var newY = i * height + oldY;
                            var newX = j * width + oldX;

                            newPoints[newY][newX] = add(oldPoints[oldY][oldX], additionFactor);
                        }
                    }
                }
            }

            return new Board(newPoints);
        }

        private static int add(int original, int additionFactor) {
            return (original - 1 + additionFactor) % 9 + 1;
        }
    }

    private static int findLowestRisk(Board cavern) {
        var discovered = new PriorityQueue<>(Comparator.comparingInt(cavern::at));
        discovered.add(Point.ORIGIN);

        var lowestRiskForPoint = new HashMap<Point, Integer>();
        lowestRiskForPoint.put(Point.ORIGIN, 0);

        while (!discovered.isEmpty()) {
            var current = discovered.stream()
                .min(Comparator.comparingInt(point -> lowestRiskForPoint.getOrDefault(point, Integer.MAX_VALUE)))
                .orElseThrow();

            if (current.equals(cavern.size().max())) {
                return lowestRiskForPoint.get(current);
            }

            discovered.remove(current);

            neighbors(current)
                .filter(cavern::inBounds)
                .forEach(neighbor -> {
                    var tentativeRisk = lowestRiskForPoint.getOrDefault(current, Integer.MAX_VALUE) + cavern.at(neighbor);
                    if (tentativeRisk < lowestRiskForPoint.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        lowestRiskForPoint.put(neighbor, tentativeRisk);
                        if (!discovered.contains(neighbor)) {
                            discovered.add(neighbor);
                        }
                    }
                });
        }
        return Integer.MAX_VALUE;
    }

    private static Stream<Point> neighbors(Point point) {
        return Stream.of(point.north(), point.west(), point.east(), point.south());
    }

}
