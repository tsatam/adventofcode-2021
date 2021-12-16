package adventofcode.day16;

import adventofcode.day16.packet.Header;
import adventofcode.day16.packet.LessThan;
import adventofcode.day16.packet.Maximum;
import adventofcode.day16.packet.Minimum;
import adventofcode.day16.packet.Sum;
import adventofcode.day16.packet.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BitParserTest {

    @ParameterizedTest
    @CsvSource({
        "0,0000",
        "1,0001",
        "2,0010",
        "3,0011",
        "4,0100",
        "5,0101",
        "6,0110",
        "7,0111",
        "8,1000",
        "9,1001",
        "A,1010",
        "B,1011",
        "C,1100",
        "D,1101",
        "E,1110",
        "F,1111"
    })
    void parsesSingleCharacter(String inputCharacter, String expectedBitsRaw) {
        var expectedBits = bitStringToList(expectedBitsRaw);
        assertThat(Parser.parseBits(inputCharacter)).isEqualTo(expectedBits);
    }

    @ParameterizedTest
    @CsvSource({
        "D2FE28,110100101111111000101000",
        "38006F45291200,00111000000000000110111101000101001010010001001000000000",
        "EE00D40C823060,11101110000000001101010000001100100000100011000001100000"
    })
    void parsesSampleInputs(String inputCharacter, String expectedBitsRaw) {
        var expectedBits = bitStringToList(expectedBitsRaw);
        assertThat(Parser.parseBits(inputCharacter)).isEqualTo(expectedBits);
    }

    @Test
    void valuePacket_returnsCorrectValue() {
        var input = bitStringToList("110100101111111000101000");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new Value(new Header(6, 4), 2021)
        );
    }

    @Test
    void operatorPacket_lengthType0_returnsCorrectValue() {
        var input = bitStringToList("00111000000000000110111101000101001010010001001000000000");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new LessThan(
                new Header(1, 6),
                List.of(
                    new Value(new Header(6, 4), 10),
                    new Value(new Header(2, 4), 20)
                )
            )
        );
    }

    @Test
    void operatorPacket_lengthType1_returnsCorrectValue() {
        var input = bitStringToList("11101110000000001101010000001100100000100011000001100000");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new Maximum(
                new Header(7, 3),
                List.of(
                    new Value(new Header(2, 4), 1),
                    new Value(new Header(4, 4), 2),
                    new Value(new Header(1, 4), 3)
                )
            )
        );
    }

    @Test
    void sampleInput1() {
        var input = Parser.parseBits("8A004A801A8002F478");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new Minimum(
                new Header(4, 2),
                List.of(
                    new Minimum(
                        new Header(1, 2),
                        List.of(
                            new Minimum(
                                new Header(5, 2),
                                List.of(
                                    new Value(new Header(6, 4), 15)
                                )
                            )
                        )
                    )
                )
            )
        );
    }

    @Test
    void sampleInput2() {
        var input = Parser.parseBits("620080001611562C8802118E34");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new Sum(
                new Header(3, 0),
                List.of(
                    new Sum(
                        new Header(0, 0),
                        List.of(
                            new Value(new Header(0, 4), 10),
                            new Value(new Header(5, 4), 11)
                        )
                    ),
                    new Sum(
                        new Header(1, 0),
                        List.of(
                            new Value(new Header(0, 4), 12),
                            new Value(new Header(3, 4), 13)
                        )
                    )
                )
            )
        );
    }

    @Test
    void sampleInput3() {
        var input = Parser.parseBits("C0015000016115A2E0802F182340");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new Sum(
                new Header(6, 0),
                List.of(
                    new Sum(
                        new Header(0, 0),
                        List.of(
                            new Value(new Header(0, 4), 10),
                            new Value(new Header(6, 4), 11)
                        )
                    ),
                    new Sum(
                        new Header(4, 0),
                        List.of(
                            new Value(new Header(7, 4), 12),
                            new Value(new Header(0, 4), 13)
                        )
                    )
                )
            )
        );
    }

    @Test
    void sampleInput4() {
        var input = Parser.parseBits("A0016C880162017C3686B18A3D4780");
        var packet = Parser.parsePacket(input);

        assertThat(packet).isEqualTo(
            new Sum(
                new Header(5, 0),
                List.of(
                    new Sum(
                        new Header(1, 0),
                        List.of(
                            new Sum(
                                new Header(3, 0),
                                List.of(
                                    new Value(new Header(7, 4), 6),
                                    new Value(new Header(6, 4), 6),
                                    new Value(new Header(5, 4), 12),
                                    new Value(new Header(2, 4), 15),
                                    new Value(new Header(2, 4), 15)
                                )
                            )
                        )
                    )
                )
            )
        );
    }

    private static List<Integer> bitStringToList(String rawBits) {
        return rawBits.chars().map(c -> c - '0').boxed().toList();
    }
}
