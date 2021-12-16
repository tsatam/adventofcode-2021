package adventofcode.day5;

import adventofcode.common.Point;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Line(Point from, Point to) {
    public static Line fromInput(String line) {
        var rawPoints = line.split(" -> ");
        var from = Point.fromInput(rawPoints[0]);
        var to = Point.fromInput(rawPoints[1]);
        return new Line(from, to);
    }

    public IntStream getXs() {
        return IntStream.of(from.x(), to.x());
    }

    public IntStream getYs() {
        return IntStream.of(from.y(), to.y());
    }

    public Stream<Point> pointsOnLine() {
        var xInc = Integer.compare(to.x(), from.x());
        var yInc = Integer.compare(to.y(), from.y());

        var bound = to.translate(xInc, yInc);

        return Stream.iterate(from, p -> !p.equals(bound), p -> p.translate(xInc, yInc));
    }

    public Direction direction() {
        if (from.x() == to.x()) {
            return Direction.VERTICAL;
        } else if (from.y() == to.y()) {
            return Direction.HORIZONTAL;
        } else {
            return Direction.DIAGONAL;
        }
    }

    public enum Direction {
        HORIZONTAL, VERTICAL, DIAGONAL
    }
}
