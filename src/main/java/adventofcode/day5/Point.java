package adventofcode.day5;

public record Point(int x, int y) {
    public static Point fromInput(String rawPoint) {
        var rawCoordinates = rawPoint.split(",");
        var x = Integer.parseInt(rawCoordinates[0]);
        var y = Integer.parseInt(rawCoordinates[1]);
        return new Point(x, y);
    }

    Point translate(int byX, int byY) {
        return new Point(x + byX, y + byY);
    }
}
