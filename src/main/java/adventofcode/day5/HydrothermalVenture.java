package adventofcode.day5;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.Arrays;
import java.util.List;

@Day(5)
public abstract sealed class HydrothermalVenture implements Solver {
    @Override
    public String solve(List<String> input) {
        var allLines = input.stream()
                .map(Line::fromInput)
                .toList();

        var linesToCheck = linesToCheck(allLines);

        var boundingBox = BoundingBox.fromLines(linesToCheck);

        var diagram = new int
                [boundingBox.max().y() + 1]
                [boundingBox.max().x() + 1];

        linesToCheck.stream()
                .flatMap(Line::pointsOnLine)
                .forEach(point -> plotPointOnDiagram(diagram, point));

        var count = Arrays.stream(diagram)
                .flatMapToInt(Arrays::stream)
                .filter(numberOfLines -> numberOfLines > 1)
                .count();

        return Integer.toString((int) count);
    }

    protected abstract List<Line> linesToCheck(List<Line> allLines);

    private static void plotPointOnDiagram(int[][] diagram, Point point) {
        diagram[point.y()][point.x()]++;
    }

    private record BoundingBox(Point min, Point max) {

        public static BoundingBox fromLines(List<Line> lines) {
            if (lines.isEmpty()) {
                return new BoundingBox(new Point(0, 0), new Point(0, 0));
            }

            var xStats = lines.stream()
                    .flatMapToInt(Line::getXs)
                    .summaryStatistics();
            var yStats = lines.stream()
                    .flatMapToInt(Line::getYs)
                    .summaryStatistics();

            var min = new Point(xStats.getMin(), yStats.getMin());
            var max = new Point(xStats.getMax(), yStats.getMax());

            return new BoundingBox(min, max);
        }
    }

    @Part(1)
    public static final class PartOne extends HydrothermalVenture {
        protected List<Line> linesToCheck(List<Line> allLines) {
            return allLines.stream()
                    .filter(line ->
                            line.direction().equals(Line.Direction.HORIZONTAL)
                                    || line.direction().equals(Line.Direction.VERTICAL)
                    )
                    .toList();
        }
    }

    @Part(2)
    public static final class PartTwo extends HydrothermalVenture {
        @Override
        protected List<Line> linesToCheck(List<Line> allLines) {
            return allLines;
        }
    }
}
