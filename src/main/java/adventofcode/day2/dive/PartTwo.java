package adventofcode.day2.dive;

import adventofcode.Solver;

import java.util.List;

public class PartTwo implements Solver {
    public String solve(List<String> input) {
        var commands = Parser.parseInput(input);

        var position = commands.stream().reduce(Position.DEFAULT, Position::applyCommand, Position::sum);

        return Integer.toString(position.product());
    }

    private record Position(int horizontal, int depth, int aim) {
        public static Position DEFAULT = new Position(0, 0, 0);

        public Position applyCommand(Command nextCommand) {
            return switch (nextCommand.direction()) {
                case forward -> forward(nextCommand.distance());
                case down -> down(nextCommand.distance());
                case up -> up(nextCommand.distance());
            };
        }

        public Position sum(Position other) {
            return new Position(horizontal + other.horizontal, depth + other.depth, aim + other.aim);
        }

        public int product() {
            return horizontal * depth;
        }

        private Position forward(int distance) {
            return new Position(horizontal + distance, depth + (aim * distance), aim);
        }

        private Position up(int distance) {
            return new Position(horizontal, depth, aim - distance);
        }

        private Position down(int distance) {
            return new Position(horizontal, depth, aim + distance);
        }
    }
}
