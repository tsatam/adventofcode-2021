package adventofcode.day1.sonarsweep;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private PartOne subject = new PartOne();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void singleInput_returns0() {
        List<String> input = List.of("100");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void twoInputs_ifSecondIsLessThanFirst_returns0() {
        List<String> input = List.of("100", "99");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void twoInputs_ifSecondIsGreaterThanFirst_returns1() {
        List<String> input = List.of("100", "101");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("7");
    }
}
