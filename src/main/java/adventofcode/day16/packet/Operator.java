package adventofcode.day16.packet;

import java.util.List;

public sealed interface Operator extends Packet permits Sum, Product, Minimum, Maximum,
    GreaterThan, LessThan, EqualTo {
    Header header();

    List<Packet> subPackets();

    default int getVersionSum() {
        return header().version() + subPackets().stream().mapToInt(Packet::getVersionSum).sum();
    }
}
