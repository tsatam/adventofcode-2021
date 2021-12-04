package adventofcode.day4;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver subject = new GiantSquid.PartOne();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void oneBoardWithAll0_returns0() {
        List<String> input = List.of(
                "0",
                "",
                " 0  0  0  0  0",
                " 0  0  0  0  0",
                " 0  0  0  0  0",
                " 0  0  0  0  0",
                " 0  0  0  0  0"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }
    @Test
    void oneBoardWithAll1_returns0() {
        List<String> input = List.of(
                "1",
                "",
                " 1  1  1  1  1",
                " 1  1  1  1  1",
                " 1  1  1  1  1",
                " 1  1  1  1  1",
                " 1  1  1  1  1"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void oneBoardWithOnly1sInTopColumn_returnsSumOfAllRemainingNumbers() {
        List<String> input = List.of(
                "1",
                "",
                " 1  1  1  1  1",
                " 2  2  2  2  2",
                " 2  2  2  2  2",
                " 2  2  2  2  2",
                " 2  2  2  2  2"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("40");
    }

    @Test
    void oneBoardWithOnly1sInFirstRow_returnsSumOfAllRemainingNumbers() {
        List<String> input = List.of(
                "1",
                "",
                " 1  2  2  2  2",
                " 1  2  2  2  2",
                " 1  2  2  2  2",
                " 1  2  2  2  2",
                " 1  2  2  2  2"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("40");
    }

    @Test
    void twoBoards_ifSecondBoardWinsFirst_returnsScoreOfSecondBoard() {
        List<String> input = List.of(
                "3",
                "",
                " 1  1  1  1  1",
                " 2  2  2  2  2",
                " 2  2  2  2  2",
                " 2  2  2  2  2",
                " 2  2  2  2  2",
                "",
                " 3  3  3  3  3",
                " 1  1  1  1  1",
                " 1  1  1  1  1",
                " 1  1  1  1  1",
                " 1  1  1  1  1"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("60");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
                "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
                "",
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19",
                "",
                " 3 15  0  2 22",
                " 9 18 13 17  5",
                "19  8  7 25 23",
                "20 11 10 24  4",
                "14 21 16 12  6",
                "",
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("4512");
    }
}