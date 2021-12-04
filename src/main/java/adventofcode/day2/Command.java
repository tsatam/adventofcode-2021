package adventofcode.day2;

record Command(Direction direction, int distance) {
    public static Command fromInput(String input) {
        var split = input.split(" ");
        return new Command(Direction.valueOf(split[0]), Integer.parseInt(split[1]));
    }
}
