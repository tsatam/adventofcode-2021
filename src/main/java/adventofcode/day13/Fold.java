package adventofcode.day13;

import java.util.regex.Pattern;

record Fold(Direction direction, int position) {
    enum Direction {X, Y;}

    public static Fold fromInput(String input) {
        var pattern = Pattern.compile("fold along (?<direction>[xy])=(?<position>\\d+)");
        var matcher = pattern.matcher(input);
        if(!matcher.find()) {
            throw new RuntimeException();
        }
        var direction = switch (matcher.group("direction")) {
            case "x" -> Direction.X;
            case "y" -> Direction.Y;
            default -> throw new RuntimeException();
        };
        var position = Integer.parseInt(matcher.group("position"));
        return new Fold(direction, position);
    }
}
