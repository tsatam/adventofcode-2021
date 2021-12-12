package adventofcode.day12;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new PassagePathing.PartTwo();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void directPath_returns0() {
        List<String> input = List.of(
                "start-end"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void singlePath_throughLargeCave_returns1() {
        List<String> input = List.of(
                "start-A",
                "A-end"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void singlePath_throughSmallCave_returns1() {
        List<String> input = List.of(
                "start-a",
                "a-end"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void multiplePaths_canVisitSmallCaveUpToTwice() {
        List<String> input = List.of(
                "start-a",
                "a-b",
                "a-end"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void sampleInput_simple() {
        List<String> input = List.of(
                "start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("36");
    }

    @Test
    void sampleInput_medium() {
        List<String> input = List.of(
                "dc-end",
                "HN-start",
                "start-kj",
                "dc-start",
                "dc-HN",
                "LN-dc",
                "HN-end",
                "kj-sa",
                "kj-HN",
                "kj-dc"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("103");
    }

    @Test
    void sampleInput_complex() {
        List<String> input = List.of(
                "fs-end",
                "he-DX",
                "fs-he",
                "start-DX",
                "pj-DX",
                "end-zg",
                "zg-sl",
                "zg-pj",
                "pj-he",
                "RW-he",
                "fs-DX",
                "pj-RW",
                "zg-RW",
                "start-pj",
                "he-WI",
                "zg-he",
                "pj-fs",
                "start-RW"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("3509");
    }
}
