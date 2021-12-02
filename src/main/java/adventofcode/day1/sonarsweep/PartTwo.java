package adventofcode.day1.sonarsweep;

import adventofcode.Solver;

import java.util.List;
import java.util.stream.IntStream;

public class PartTwo implements Solver {
    public String solve(List<String> input) {
        var measurements = Parser.parseMeasurements(input);

        if (measurements.length < 4) {
            return "0";
        }

        var count = countMeasurementWindowLargerThanPrevious(measurements);

        return Long.toString(count);
    }

    private long countMeasurementWindowLargerThanPrevious(int[] measurements) {
        return IntStream.range(3, measurements.length).filter(i -> measurements[i] > measurements[i - 3]).count();
    }
}
