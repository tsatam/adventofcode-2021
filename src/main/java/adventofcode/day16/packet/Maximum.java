package adventofcode.day16.packet;

import java.util.List;

public record Maximum(Header header, List<Packet> subPackets) implements Operator {
    @Override
    public long value() {
        return subPackets().stream()
            .mapToLong(Packet::value)
            .max()
            .orElse(0);
    }
}
