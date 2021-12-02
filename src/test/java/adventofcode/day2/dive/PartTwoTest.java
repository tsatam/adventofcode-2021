package adventofcode.day2.dive;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final PartTwo subject = new PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void inputWith1DownAnd1Forward_returns1() {
        List<String> input = List.of("down 1", "forward 1");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void inputWith5DownAnd2Forward_returns20() {
        List<String> input = List.of("down 5", "forward 2");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("20");
    }
    @Test
    void inputWith5Down2Forward2Forward_returns80() {
        List<String> input = List.of("down 5", "forward 2", "forward 2");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("80");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("900");
    }
}
