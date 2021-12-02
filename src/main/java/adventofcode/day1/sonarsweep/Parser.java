package adventofcode.day1.sonarsweep;

import java.util.List;

public class Parser {
    public static int[] parseMeasurements(List<String> input) {
        return input.stream().mapToInt(Integer::parseInt).toArray();
    }
}
