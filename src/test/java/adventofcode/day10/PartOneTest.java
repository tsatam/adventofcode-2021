package adventofcode.day10;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver subject = new SyntaxScoring.PartOne();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void validInput_returns0() {
        List<String> input = List.of("({[<>]})");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void illegalInput_firstCharIsRound_returns3() {
        List<String> input = List.of(")");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("3");
    }

    @Test
    void illegalInput_firstCharIsBox_returns57() {
        List<String> input = List.of("]");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("57");
    }

    @Test
    void illegalInput_firstCharIsBrace_returns1197() {
        List<String> input = List.of("}");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1197");
    }

    @Test
    void illegalInput_firstCharIsAngle_returns25137() {
        List<String> input = List.of(">");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("25137");
    }

    @Test
    void multipleLines_returnsSumOfAllIllegalLines() {
        List<String> input = List.of(
            ")",
            "()",
            "}"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1200");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("26397");
    }
}
