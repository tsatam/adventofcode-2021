package adventofcode.day7;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new TheTreacheryOfWhales.PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void singleCrab_returns0() {
        List<String> input = List.of("1");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void twoCrabs_returnsTwiceTheDistanceBetweenThemAndTheCenterPoint() {
        List<String> input = List.of("1,5");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("6");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of("16,1,2,0,4,2,7,1,2,14");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("168");
    }
}
