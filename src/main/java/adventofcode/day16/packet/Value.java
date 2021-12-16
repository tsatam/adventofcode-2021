package adventofcode.day16.packet;

public record Value(Header header, long value) implements Packet {

    @Override
    public int getVersionSum() {
        return header.version();
    }
}
