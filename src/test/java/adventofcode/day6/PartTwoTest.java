package adventofcode.day6;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new LanternfishSolver.PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("3,4,3,1,2");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("26984457539");
    }
}
