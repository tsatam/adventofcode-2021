package adventofcode.day8;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Day(8)
public abstract sealed class SevenSegmentSearch implements Solver {
    private static final int[] SEGMENT_LENGTHS = new int[]{
        6, 2, 5, 5, 4, 5, 6, 3, 7, 6
    };

    @Part(1)
    public static final class PartOne extends SevenSegmentSearch {

        private static final List<Integer> LENGTHS_TO_CHECK = List.of(
            SEGMENT_LENGTHS[1],
            SEGMENT_LENGTHS[4],
            SEGMENT_LENGTHS[7],
            SEGMENT_LENGTHS[8]
        );

        @Override
        public String solve(List<String> input) {
            var count = input.stream()
                .map(Display::fromInputLine)
                .mapToInt(PartOne::numberOfLengthsToCheckInOutput)
                .sum();

            return Integer.toString(count);
        }

        private static int numberOfLengthsToCheckInOutput(Display display) {
            return (int) Arrays.stream(display.outputs())
                .filter(output -> LENGTHS_TO_CHECK.contains(output.length()))
                .count();
        }
    }

    @Part(2)
    public static final class PartTwo extends SevenSegmentSearch {
        @Override
        public String solve(List<String> input) {
            var sum = input.stream()
                .map(Display::fromInputLine)
                .mapToInt(PartTwo::calculateValue)
                .sum();
            return Integer.toString(sum);
        }

        private static int calculateValue(Display display) {
            var sequences = new SegmentCalculator(display).calculateSequences();

            var rawResult = Arrays.stream(display.outputs())
                .mapToInt(digit -> calculateNumberForDigit(sequences, digit))
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());

            return Integer.parseInt(rawResult);
        }

        private static int calculateNumberForDigit(String[] sequences, String digit) {
            for(int i = 0; i < 10; i++) {
                if(sequencesEqual(sequences[i], digit)) {
                    return i;
                }
            }
            return Integer.MIN_VALUE;
        }

        private static boolean sequencesEqual(String a, String b) {
            var aChars = a.chars().boxed().toList();
            var bChars = b.chars().boxed().toList();
            return aChars.containsAll(bChars) && bChars.containsAll(aChars);
        }
    }

}
