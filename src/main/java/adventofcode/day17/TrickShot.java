package adventofcode.day17;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;
import adventofcode.common.BoundingBox;
import adventofcode.common.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Day(17)
public abstract sealed class TrickShot implements Solver {
    @Override
    public String solve(List<String> input) {
        assert input.size() == 1;

        var trench = parseInput(input.get(0));

        List<Result.Success> successes = new ArrayList<>();

        for (int velocityX = 1; velocityX <= trench.max().x(); velocityX++) {
            for (int velocityY = trench.min().y(); velocityY < -trench.min().y(); velocityY++) {

                var velocity = new Point(velocityX, velocityY);

                var probe = new Probe(Point.ORIGIN, velocity);

                var result = probe.simulate(trench, new ArrayList<>());

                if (result instanceof Result.Success success) {
                    successes.add(success);
                }
            }
        }

        return Integer.toString(getResult(successes));
    }

    protected abstract int getResult(List<Result.Success> successes);

    @Part(1)
    public static final class PartOne extends TrickShot {
        @Override
        protected int getResult(List<Result.Success> successes) {
            return successes.stream()
                .mapToInt(Result.Success::highestY)
                .max()
                .orElse(Integer.MIN_VALUE);
        }
    }

    @Part(2)
    public static final class PartTwo extends TrickShot {
        @Override
        protected int getResult(List<Result.Success> successes) {
            return successes.size();
        }
    }

    private BoundingBox parseInput(String input) {
        var pattern = Pattern.compile(
            "target area: x=(?<minX>-?[0-9]+)\\.\\.(?<maxX>-?[0-9]+), y=(?<minY>-?[0-9]+)\\.\\.(?<maxY>-?[0-9]+)"
        );
        var matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new RuntimeException();
        }
        var minX = Integer.parseInt(matcher.group("minX"));
        var maxX = Integer.parseInt(matcher.group("maxX"));
        var minY = Integer.parseInt(matcher.group("minY"));
        var maxY = Integer.parseInt(matcher.group("maxY"));

        return new BoundingBox(
            new Point(minX, minY),
            new Point(maxX, maxY)
        );
    }

    record Probe(Point position, Point velocity) {
        public Probe next() {
            var newPoint = position.translate(velocity);
            var newVelocity = new Point(
                velocity.x() + Integer.compare(0, velocity.x()),
                velocity.y() - 1
            );
            return new Probe(newPoint, newVelocity);
        }

        public Result simulate(BoundingBox trench, List<Point> points) {
            points.add(position);

            if (trench.inBounds(position)) {
                return new Result.Success(List.copyOf(points));
            }

            var canNeverSucceed =
                position.x() > trench.max().x()
                || velocity.y() <= 0 && position.y() < trench.min().y();

            if (canNeverSucceed) {
                return new Result.Failure(List.copyOf(points));
            }

            return next().simulate(trench, points);
        }
    }

    sealed interface Result permits Result.Success, Result.Failure {
        List<Point> points();

        record Failure(List<Point> points) implements Result {}

        record Success(List<Point> points) implements Result {
            public int highestY() {
                return points.stream()
                    .mapToInt(Point::y)
                    .max()
                    .orElseThrow();
            }
        }
    }
}
