package adventofcode.common;

import java.util.concurrent.atomic.AtomicInteger;

public record Board(int[][] points) {

    public static Board fromBoundingBox(BoundingBox boundingBox) {
        var points = new int[boundingBox.max().y() + 1][boundingBox.max().x() + 1];
        return new Board(points);
    }

    public static Board ofSize(int x, int y) {
        return new Board(new int[y][x]);
    }

    public int at(Point point) {
        return points[point.y()][point.x()];
    }

    public void set(Point point, int value) {
        points[point.y()][point.x()] = value;
    }

    public BoundingBox size() {
        if (points.length == 0) return BoundingBox.ZERO;
        return new BoundingBox(Point.ORIGIN, new Point(points[0].length - 1, points.length - 1));
    }

    public int sum() {
        AtomicInteger sum = new AtomicInteger();
        Point.forPointRange(size(), point -> {
            sum.addAndGet(at(point));
        });
        return sum.get();
    }
}
