package adventofcode.day10;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new SyntaxScoring.PartTwo();

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
    void incompleteInput_missingRoundBracket_returns1() {
        List<String> input = List.of("(");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void incompleteInput_missingBoxBracket_returns2() {
        List<String> input = List.of("[");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void incompleteInput_missingBraceBracket_returns3() {
        List<String> input = List.of("{");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("3");
    }

    @Test
    void incompleteInput_missingAngleBracket_returns4() {
        List<String> input = List.of("<");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("4");
    }

    @Test
    void incompleteInput_missingMultiple_returnsCorrectScore() {
        List<String> input = List.of("<{([");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("294");
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
        assertThat(result).isEqualTo("288957");
    }
}
