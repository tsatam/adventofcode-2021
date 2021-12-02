package adventofcode.day1.sonarsweep;

import adventofcode.Solver;

import java.util.List;
import java.util.stream.IntStream;

public class PartOne implements Solver {
    public String solve(List<String> input) {
        var measurements = Parser.parseMeasurements(input);

        var counter = countMeasurementsLargerThanPrevious(measurements);

        return Long.toString(counter);
    }

    private long countMeasurementsLargerThanPrevious(int[] measurements) {
        return IntStream.range(1, measurements.length).filter(i -> measurements[i] > measurements[i - 1]).count();
    }
}
