package adventofcode.day5;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new HydrothermalVenture.PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void oneLine_returns0() {
        List<String> input = List.of(
                "1,1 -> 2,2"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void twoLinesWithNoOverlap_returns0() {
        List<String> input = List.of(
                "1,1 -> 1,2",
                "2,2 -> 3,3"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void twoLinesWithOverlap_returns1() {
        List<String> input = List.of(
                "1,1 -> 1,2",
                "1,1 -> 2,2"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void twoOfTheSameLine_returnsLengthOfLine() {
        List<String> input = List.of(
                "1,1 -> 5,5",
                "1,1 -> 5,5"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("5");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("12");
    }
}
