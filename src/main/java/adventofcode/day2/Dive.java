package adventofcode.day2;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.List;

@Day(2)
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

    @Part(1)
    public static final class PartOne extends Dive<Position.PartOne> {
        @Override
        protected Position.PartOne startingPosition() {
            return new Position.PartOne(0, 0);
        }
    }

    @Part(2)
    public static final class PartTwo extends Dive<Position.PartTwo> {
        @Override
        protected Position.PartTwo startingPosition() {
            return new Position.PartTwo(0, 0, 0);
        }
    }
}
