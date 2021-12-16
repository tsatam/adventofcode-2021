package adventofcode.day16.packet;

public record Header(int version, int typeId) {

    public Type type() {
        return Type.fromId(typeId());
    }
}
