package adventofcode.day16.packet;

public enum Type {
    VALUE,

    SUM,
    PRODUCT,
    MINIMUM,
    MAXIMUM,
    GREATER_THAN,
    LESS_THAN,
    EQUAL_TO;

    public static Type fromId(int id) {
        return switch (id) {
            case 0 -> SUM;
            case 1 -> PRODUCT;
            case 2 -> MINIMUM;
            case 3 -> MAXIMUM;
            case 4 -> VALUE;
            case 5 -> GREATER_THAN;
            case 6 -> LESS_THAN;
            case 7 -> EQUAL_TO;
            default -> null;
        };
    }
}
