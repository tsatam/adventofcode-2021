package adventofcode.day2;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver subject = new Dive.PartOne();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void inputWith1ForwardAnd1Down_returns1() {
        List<String> input = List.of("forward 1", "down 1");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void inputWith1Forward1Down1Up_returns0() {
        List<String> input = List.of("forward 1", "down 1", "up 1");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("150");
    }
}
