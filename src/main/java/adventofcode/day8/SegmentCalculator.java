package adventofcode.day8;

import java.util.Arrays;
import java.util.stream.Stream;

public record SegmentCalculator(Display display) {

    private String[] inputs() {
        return display.inputs();
    }

    public String[] calculateSequences() {
        var s1 = calculate1Sequence();
        var s4 = calculate4Sequence();
        var s7 = calculate7Sequence();
        var s8 = calculate8Sequence();
        var s0 = calculate0Sequence(s4);
        var s3 = calculate3Sequence(s1);
        var s9 = calculate9Sequence(s0, s1);
        var s6 = calculate6Sequence(s0, s9);
        var s2 = calculate2Sequence(s1, s3, s6);
        var s5 = calculate5Sequence(s2, s3);

        return new String[]{s0, s1, s2, s3, s4, s5, s6, s7, s8, s9};
    }


    private String calculate0Sequence(String s4) {
        var middleSegment = middleSegment(s4);

        return Arrays.stream(inputs())
            .filter(input -> input.length() == 6)
            .filter(input -> !sequenceContainsChar(input, middleSegment))
            .findAny()
            .orElseThrow();
    }

    private String calculate1Sequence() {
        return Arrays.stream(inputs())
            .filter(value -> value.length() == 2)
            .findFirst()
            .orElseThrow();
    }

    private String calculate2Sequence(String s1, String s3, String s6) {
        var upperRightSegment = upperRightSegment(s1, s6);

        return Arrays.stream(inputs())
            .filter(input -> input.length() == 5)
            .filter(input -> !input.equals(s3))
            .filter(input -> sequenceContainsChar(input, upperRightSegment))
            .findAny()
            .orElseThrow();
    }

    private String calculate3Sequence(String s1) {
        return Arrays.stream(inputs())
            .filter(input -> input.length() == 5)
            .filter(input -> sequenceContainsString(input, s1))
            .findAny()
            .orElseThrow();
    }

    private String calculate4Sequence() {
        return Arrays.stream(inputs())
            .filter(value -> value.length() == 4)
            .findFirst()
            .orElseThrow();
    }

    private String calculate5Sequence(String s2, String s3) {
        return Arrays.stream(inputs())
            .filter(input -> input.length() == 5)
            .filter(input -> !input.equals(s2))
            .filter(input -> !input.equals(s3))
            .findAny()
            .orElseThrow();
    }

    private String calculate6Sequence(String s0, String s9) {
        return Arrays.stream(inputs())
            .filter(input -> input.length() == 6)
            .filter(input -> !input.equals(s0))
            .filter(input -> !input.equals(s9))
            .findAny()
            .orElseThrow();
    }

    private String calculate7Sequence() {
        return Arrays.stream(inputs())
            .filter(value -> value.length() == 3)
            .findFirst()
            .orElseThrow();
    }

    private String calculate8Sequence() {
        return Arrays.stream(inputs())
            .filter(value -> value.length() == 7)
            .findFirst()
            .orElseThrow();
    }

    private String calculate9Sequence(String s0, String s1) {
        return Arrays.stream(inputs())
            .filter(input -> input.length() == 6)
            .filter(input -> !input.equals(s0))
            .filter(input -> sequenceContainsString(input, s1))
            .findAny()
            .orElseThrow();
    }

    private char middleSegment(String s4) {
        return Stream.of('a', 'b', 'c', 'd', 'e', 'f', 'g')
            .filter(c ->
                Arrays.stream(inputs())
                    .filter(segment -> sequenceContainsChar(segment, c))
                    .count() == 7
            )
            .filter(c -> sequenceContainsChar(s4, c))
            .findAny()
            .orElseThrow();
    }

    private char upperRightSegment(String s1, String s6) {
        return s1.chars()
            .filter(c -> s6.chars().noneMatch(other -> other == c))
            .mapToObj(i -> (char) i)
            .findAny()
            .orElseThrow();
    }

    private static boolean sequenceContainsString(String sequence, String other) {
        return other.chars().allMatch(c -> sequenceContainsChar(sequence, (char) c));
    }

    private static boolean sequenceContainsChar(String sequence, char other) {
        return sequence.contains("" + other);
    }
}
