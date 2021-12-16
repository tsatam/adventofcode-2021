package adventofcode.common;

import java.util.function.Consumer;

public record Point(int x, int y) {
    public static Point ORIGIN = new Point(0, 0);

    public static Point fromInput(String input) {
        var split = input.split(",");
        var x = Integer.parseInt(split[0]);
        var y = Integer.parseInt(split[1]);
        return new Point(x, y);
    }

    public static void forPointRange(BoundingBox boundingBox, Consumer<Point> action) {
        for(int x = boundingBox.min().x(); x <= boundingBox.max().x(); x++) {
            for(int y = boundingBox.min().y(); y <= boundingBox.max().y(); y++) {
                var point = new Point(x,y);
                action.accept(point);
            }
        }
    }

    public Point translate(int byX, int byY) {
        return new Point(x + byX, y + byY);
    }

    public Point north() {
        return translate(0, -1);
    }
    public Point northEast() {
        return translate(1, -1);
    }
    public Point east() {
        return translate(1, 0);
    }
    public Point southEast() {
        return translate(1, 1);
    }
    public Point south() {
        return translate(0, 1);
    }
    public Point southWest() {
        return translate(-1, 1);
    }
    public Point west() {
        return translate(-1, 0);
    }
    public Point northWest() {
        return translate(-1, -1);
    }
}
