package adventofcode.day15;

import adventofcode.Solver;
import adventofcode.day14.ExtendedPolymerization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PartOneTest {
    private final Solver subject = new Chiton.PartOne();

    @Test
    void cavernOfSize2_returnsExitValue() {
        List<String> input = List.of(
            "57"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("7");
    }

    @Test
    void optimalPathIsShortest_takes() {
        List<String> input = List.of(
            "11111",
            "99991",
            "99991",
            "99991",
            "99991"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("8");
    }

    @Test
    void willTakePathGoingBackUp() {
        List<String> input = List.of(
            "19111",
            "19191",
            "11191",
            "99991",
            "99991"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("12");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("40");
    }
}
