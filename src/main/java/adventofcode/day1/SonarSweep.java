package adventofcode.day1;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.List;
import java.util.stream.IntStream;

@Day(1)
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

    @Part(1)
    public static final class PartOne extends SonarSweep {
        @Override
        protected long countMeasurements(int[] measurements) {
            return IntStream.range(1, measurements.length).filter(i -> measurements[i] > measurements[i - 1]).count();
        }
    }

    @Part(2)
    public static final class PartTwo extends SonarSweep {
        @Override
        protected long countMeasurements(int[] measurements) {
            return IntStream.range(3, measurements.length).filter(i -> measurements[i] > measurements[i - 3]).count();
        }
    }
}
