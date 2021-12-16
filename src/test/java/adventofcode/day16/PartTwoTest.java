package adventofcode.day16;

import adventofcode.Solver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {
    private final Solver subject = new PacketDecoder.PartTwo();

    @ParameterizedTest
    @CsvSource({
        "C200B40A82,3",
        "04005AC33890,54",
        "880086C3E88112,7",
        "CE00C43D881120,9",
        "D8005AC2A8F0,1",
        "F600BC2D8F,0",
        "9C005AC2F8F0,0",
        "9C0141080250320F1802104A08,1",
    })
    void sampleInputs(String inputLine, String expectedOutput) {
        List<String> input = List.of(inputLine);
        var result = subject.solve(input);
        assertThat(result).isEqualTo(expectedOutput);
    }
}
