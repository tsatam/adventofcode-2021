package adventofcode.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final SonarSweep subject = new SonarSweep.PartTwo();

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
    void fourInputs_ifSecondSlidingWindowIsLessThanFirst_returns0() {
        List<String> input = List.of("100", "100","100","99");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void fourInputs_ifSecondSlidingWindowIsGreaterThanFirst_returns1() {
        List<String> input = List.of("100", "100","100","101");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("5");
    }
}
