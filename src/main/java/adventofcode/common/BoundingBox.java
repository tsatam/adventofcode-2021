package adventofcode.common;

import java.util.Comparator;
import java.util.List;

public record BoundingBox(Point min, Point max) {
    public static BoundingBox ZERO = new BoundingBox(Point.ORIGIN, Point.ORIGIN);
    public static BoundingBox fromPoints(List<Point> points) {
        var minX = points.stream()
                .min(Comparator.comparingInt(Point::x))
                .orElse(Point.ORIGIN);
        var maxX = points.stream()
                .max(Comparator.comparingInt(Point::x))
                .orElse(Point.ORIGIN);
        var minY = points.stream()
                .min(Comparator.comparingInt(Point::y))
                .orElse(Point.ORIGIN);
        var maxY = points.stream()
                .max(Comparator.comparingInt(Point::y))
                .orElse(Point.ORIGIN);

        return new BoundingBox(new Point(minX.x(), minY.y()), new Point(maxX.x(), maxY.y()));
    }

    public boolean inBounds(Point point) {
        return (min.x() <= point.x() && point.x() <= max.x())
            && (min.y() <= point.y() && point.y() <= max.y());
    }
}
