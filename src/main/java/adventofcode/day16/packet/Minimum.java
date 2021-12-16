package adventofcode.day16.packet;

import java.util.List;

public record Minimum(Header header, List<Packet> subPackets) implements Operator {
    @Override
    public long value() {
        return subPackets().stream()
            .mapToLong(Packet::value)
            .min()
            .orElse(0);
    }
}
