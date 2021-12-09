package adventofcode.day9;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new SmokeBasin.PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void oneByOneMap_returnsSingleValue() {
        List<String> input = List.of("4");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void threeByThreeMapWithSingleLowPointInCenterAndNoBoundaries_returnsEntireBasinSize() {
        List<String> input = List.of(
            "555",
            "535",
            "555"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("9");
    }

    @Test
    void threeBasins_returnsProductOfSize() {
        List<String> input = List.of(
            "21929",
            "99999",
            "33949",
            "23934"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("24");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1134");
    }
}
