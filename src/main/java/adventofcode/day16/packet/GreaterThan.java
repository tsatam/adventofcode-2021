package adventofcode.day16.packet;

import java.util.List;

public record GreaterThan(Header header, List<Packet> subPackets) implements Operator {
    @Override
    public long value() {
        assert subPackets().size() == 2;
        return subPackets().get(0).value() > subPackets().get(1).value() ? 1 : 0;
    }
}
