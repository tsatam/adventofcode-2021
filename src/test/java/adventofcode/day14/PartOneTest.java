package adventofcode.day14;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PartOneTest {
    private final Solver subject = new ExtendedPolymerization.PartOne();

    @Test
    void emptyInput_returns0() {
        List<String> input = List.of();
        var result = subject.solve(input);
        assertThat(result).isEqualTo("0");
    }

    @Test
    void noPairs_usesTemplateForFinalCalculation() {
        List<String> input = List.of(
            "AAAB",
            ""
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void noPairsThatAreInTemplate_usesTemplateForFinalCalculation() {
        List<String> input = List.of(
            "AAAB",
            "",
            "BA -> C",
            "XA -> I"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void pairInTemplateOnce_insertsOnlyOnce() {
        List<String> input = List.of(
            "ABCD",
            "",
            "BC -> A"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void sampleInput() {
        List<String> input = List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1588");
    }
}
