package adventofcode.day16.packet;

import java.util.List;

public record Sum(Header header, List<Packet> subPackets) implements Operator {
    @Override
    public long value() {
        return subPackets().stream()
            .mapToLong(Packet::value)
            .sum();
    }
}
