package adventofcode.day3.binarydiagnostic;

import adventofcode.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne implements Solver {
    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }

        var size = input.get(0).length();
        int[] bitCount = new int[size];
        for (var measurement : input) {
            for (int position = 0; position < size; position++) {
                if(measurement.charAt(position) == '1') {
                    bitCount[position]++;
                }
            }
        }
        var bitAverage = Arrays.stream(bitCount)
                .map(value -> Math.round(value / (float) input.size()))
                .toArray();

        var gammaRate = Integer.parseUnsignedInt(Arrays.stream(bitAverage)
                .mapToObj(Integer::toBinaryString)
                .collect(Collectors.joining()), 2);
        var epsilonRate = Integer.parseUnsignedInt(Arrays.stream(bitAverage)
                .map(i -> i ^ 1)
                .mapToObj(Integer::toBinaryString)
                .collect(Collectors.joining()), 2);
        return Integer.toString(gammaRate * epsilonRate);
    }
}
