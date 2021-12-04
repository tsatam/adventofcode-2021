package adventofcode.day3;

import adventofcode.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public abstract sealed class BinaryDiagnostic implements Solver {
    private int bitSize = 0;

    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }
        bitSize = input.get(0).length();
        var rating = calculateRating(input);
        return Integer.toString(rating);
    }

    protected int getBitSize() {
        return this.bitSize;
    }

    protected abstract int calculateRating(List<String> input);

    protected static int invertBit(int bit) {
        return bit ^ 1;
    }

    public static final class PartOne extends BinaryDiagnostic {
        @Override
        protected int calculateRating(List<String> input) {
            int[] bitCount = new int[getBitSize()];
            for (var measurement : input) {
                for (int position = 0; position < getBitSize(); position++) {
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
                    .map(BinaryDiagnostic::invertBit)
                    .mapToObj(Integer::toBinaryString)
                    .collect(Collectors.joining()), 2);

            return gammaRate * epsilonRate;
        }
    }

    public static final class PartTwo extends BinaryDiagnostic {

        @Override
        protected int calculateRating(List<String> input) {

            var oxygenGeneratorRating = findOxygenGeneratorRating(input);
            var cO2ScrubberRating = findCO2ScrubberRating(input);
            return oxygenGeneratorRating*cO2ScrubberRating;
        }

        private int findRating(List<String> input, IntUnaryOperator calculateBitToFilterFromMostCommon) {
            var filteredList = List.copyOf(input);

            for (int i = 0; filteredList.size() > 1; i++) {
                int position = i % getBitSize();
                int mostCommon = Math.toIntExact(Math.round(getAverageOfBit(filteredList, position)));
                var bitToFilter = calculateBitToFilterFromMostCommon.applyAsInt(mostCommon);
                filteredList = filterListWithBitAtPosition(filteredList, position, bitToFilter);
            }

            return Integer.parseUnsignedInt(filteredList.get(0), 2);
        }

        private int findOxygenGeneratorRating(List<String> input) {
            return findRating(input, (mostCommon) -> mostCommon);
        }

        private int findCO2ScrubberRating(List<String> input) {
            return findRating(input, BinaryDiagnostic::invertBit);
        }

        private double getAverageOfBit(List<String> filteredList, int bitToCheck) {
            return filteredList.stream()
                    .map(s -> s.charAt(bitToCheck))
                    .mapToInt(c -> Integer.parseUnsignedInt(c.toString(), 2))
                    .average()
                    .orElse(0);
        }

        private List<String> filterListWithBitAtPosition(List<String> filteredList, int position, int bitToFilter) {
            return filteredList.stream()
                    .filter(line -> getBitAtPosition(position, line) == bitToFilter)
                    .toList();
        }

        private int getBitAtPosition(int position, String s) {
            return Integer.parseUnsignedInt(String.valueOf(s.charAt(position)));
        }
    }
}
