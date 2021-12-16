package adventofcode.day15;

import adventofcode.Solver;
import adventofcode.day14.ExtendedPolymerization;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

class PartTwoTest {
    private final Solver subject = new Chiton.PartTwo();

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
        assertThat(result).isEqualTo("315");
    }
}
