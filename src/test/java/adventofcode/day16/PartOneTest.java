package adventofcode.day16;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final Solver subject = new PacketDecoder.PartOne();

    @Test
    void sampleInput1() {
        List<String> input = List.of("8A004A801A8002F478");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("16");

    }

    @Test
    void sampleInput2() {
        List<String> input = List.of("620080001611562C8802118E34");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("12");

    }

    @Test
    void sampleInput3() {
        List<String> input = List.of("C0015000016115A2E0802F182340");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("23");

    }

    @Test
    void sampleInput4() {
        List<String> input = List.of("A0016C880162017C3686B18A3D4780");
        var result = subject.solve(input);
        assertThat(result).isEqualTo("31");
    }
}
