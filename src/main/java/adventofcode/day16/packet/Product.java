package adventofcode.day16.packet;

import java.util.List;

public record Product(Header header, List<Packet> subPackets) implements Operator {
    @Override
    public long value() {
        return subPackets().stream()
            .mapToLong(Packet::value)
            .reduce((a, b) -> a * b)
            .orElse(0);
    }
}
