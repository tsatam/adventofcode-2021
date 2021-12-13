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
}
