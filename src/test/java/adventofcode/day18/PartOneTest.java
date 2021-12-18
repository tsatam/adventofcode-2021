package adventofcode.day18;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver subject = new Snailfish.PartOne();

    @Test
    void singlePairOfValues_returnsMagnitude() {
        var input = List.of("[1,1]");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("5");
    }

    @Test
    void sampleInput_1() {
        var input = List.of(
                "[1,2]",
                "[[3,4],5]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("143");
    }

    @Test
    void sampleInput_2() {
        var input = List.of(
                "[[[[4,3],4],4],[7,[[8,4],9]]]",
                "[1,1]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1384");
    }

    @Test
    void sampleInput_3() {
        var input = List.of(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("445");
    }

    @Test
    void sampleInput_4() {
        var input = List.of(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]",
                "[5,5]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("791");
    }
    @Test
    void sampleInput_5() {
        var input = List.of(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]",
                "[5,5]",
                "[6,6]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("1137");
    }
    @Test
    void sampleInput_6() {
        var input = List.of(
                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
                "[7,[5,[[3,8],[1,4]]]]",
                "[[2,[2,2]],[8,[8,1]]]",
                "[2,9]",
                "[1,[[[9,3],9],[[9,0],[0,7]]]]",
                "[[[5,[7,4]],7],1]",
                "[[[[4,2],2],6],[8,7]]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("3488");
    }

    @Test
    void sampleInput_homeworkAssignment() {
        var input = List.of(
                "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
                "[[[5,[2,8]],4],[5,[[9,9],0]]]",
                "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
                "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
                "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
                "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
                "[[[[5,4],[7,7]],8],[[8,3],8]]",
                "[[9,3],[[9,9],[6,[4,9]]]]",
                "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
                "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
        );
        var result = subject.solve(input);
        assertThat(result).isEqualTo("4140");
    }
}
