package adventofcode.day17;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver solver = new TrickShot.PartOne();

    @Test
    void sampleInput() {
        var input = List.of(
            "target area: x=20..30, y=-10..-5"
        );
        var result = solver.solve(input);
        assertThat(result).isEqualTo("45");
    }
}

/*
var v: velocity
var t: target
var s: steps <==

var f: v-s=f


t = v(v+1)/2 - f(f+1)/2
t = (v^2 + v - f^2 - f)/2
t = (v^2 + v - v^2 + 2vs - s^2 - v + s)/2
t = (2vs - v + s)/2
2t = 2vs - v + s
2t + v = s(2v + 1)
s = (2t + v) / (2v + 1)


0: 0
1: n
2: 2n - 1
3: 3n - 2

s: sn - s - 1

v_y = iv_y - s





 */