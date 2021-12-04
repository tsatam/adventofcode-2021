package adventofcode.day2;

import adventofcode.Solver;

import java.util.List;

public abstract sealed class Dive<P extends Position<P>> implements Solver {
    @Override
    public String solve(List<String> input) {
        var commands = input.stream()
                .map(Command::fromInput)
                .toList();

        var position = commands.stream()
                .reduce(
                        startingPosition(),
                        P::applyCommand,
                        P::sum
                );
        return Integer.toString(position.product());
    }

    protected abstract P startingPosition();

    public static final class PartOne extends Dive<Position.PartOne> {
        @Override
        protected Position.PartOne startingPosition() {
            return new Position.PartOne(0, 0);
        }
    }

    public static final class PartTwo extends Dive<Position.PartTwo> {
        @Override
        protected Position.PartTwo startingPosition() {
            return new Position.PartTwo(0, 0, 0);
        }
    }
}
