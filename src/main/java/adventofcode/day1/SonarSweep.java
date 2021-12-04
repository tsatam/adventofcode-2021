package adventofcode.day1;

import adventofcode.Solver;

import java.util.List;
import java.util.stream.IntStream;

public abstract sealed class SonarSweep implements Solver {

    @Override
    public String solve(List<String> input) {
        var measurements = input.stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        var count = countMeasurements(measurements);
        return Long.toString(count);
    }

    protected abstract long countMeasurements(int[] measurements);

    public static final class PartOne extends SonarSweep {
        @Override
        protected long countMeasurements(int[] measurements) {
            return IntStream.range(1, measurements.length).filter(i -> measurements[i] > measurements[i - 1]).count();
        }
    }

    public static final class PartTwo extends SonarSweep {
        @Override
        protected long countMeasurements(int[] measurements) {
            return IntStream.range(3, measurements.length).filter(i -> measurements[i] > measurements[i - 3]).count();
        }
    }
}
