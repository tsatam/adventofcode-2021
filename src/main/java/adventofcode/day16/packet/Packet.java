package adventofcode.day16.packet;

public sealed interface Packet permits Operator, Value {
    Header header();
    int getVersionSum();
    long value();
}
