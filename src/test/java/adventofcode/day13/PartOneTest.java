package adventofcode.day13;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver subject = new TransparentOrigami.PartOne();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void canFoldAlongX() {
        List<String> input = List.of(
                "0,0",
                "0,2",
                "2,0",
                "2,2",
                "",
                "fold along x=1"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void canFoldAlongY() {
        List<String> input = List.of(
                "0,0",
                "0,2",
                "2,0",
                "2,2",
                "",
                "fold along y=1"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
                "6,10",
                "0,14",
                "9,10",
                "0,3",
                "10,4",
                "4,11",
                "6,0",
                "6,12",
                "4,1",
                "0,13",
                "10,12",
                "3,4",
                "3,0",
                "8,4",
                "1,10",
                "2,14",
                "8,10",
                "9,0",
                "",
                "fold along y=7",
                "fold along x=5"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("17");
    }


}