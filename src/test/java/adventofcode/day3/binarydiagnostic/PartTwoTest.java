package adventofcode.day3.binarydiagnostic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PartTwoTest {
    private final PartTwo subject = new PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void singleInput_returnsSelfMultipliedBySelf() {
        List<String> input = List.of("0101");
        var result = subject.solve(input);
        assertThat(result).isEqualTo(Integer.toString(0b0101 * 0b0101));
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("230");
    }
}