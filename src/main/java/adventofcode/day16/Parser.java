package adventofcode.day16;

import adventofcode.day16.packet.EqualTo;
import adventofcode.day16.packet.GreaterThan;
import adventofcode.day16.packet.Header;
import adventofcode.day16.packet.LessThan;
import adventofcode.day16.packet.Maximum;
import adventofcode.day16.packet.Minimum;
import adventofcode.day16.packet.Operator;
import adventofcode.day16.packet.Packet;
import adventofcode.day16.packet.Product;
import adventofcode.day16.packet.Sum;
import adventofcode.day16.packet.Type;
import adventofcode.day16.packet.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Parser {
    public static List<Integer> parseBits(String input) {
        return input.chars()
            .map(c -> Integer.parseInt((char) c + "", 16))
            .flatMap(fourBits -> "%1$4s"
                .formatted(Integer.toString(fourBits, 2)).replace(' ', '0')
                .chars()
                .map(c -> c - '0')
            )
            .boxed()
            .toList();
    }

    public static Packet parsePacket(List<Integer> rawBits) {
        return parsePacketRecursive(rawBits).packet();
    }

    record PacketAndFinalIndex(Packet packet, int index) {}

    private static PacketAndFinalIndex parsePacketRecursive(List<Integer> rawBits) {
        AtomicInteger iterator = new AtomicInteger();

        var version = (int) combineBits(rawBits.subList(iterator.get(), iterator.addAndGet(3)));
        var typeId = (int) combineBits(rawBits.subList(iterator.get(), iterator.addAndGet(3)));
        var type = Type.fromId(typeId);
        var header = new Header(version, typeId);

        Packet packet = switch (type) {
            case VALUE -> parseValue(
                rawBits,
                iterator,
                header
            );
            case SUM, PRODUCT, MINIMUM, MAXIMUM, GREATER_THAN, LESS_THAN, EQUAL_TO -> parsePacket(
                rawBits,
                iterator,
                type,
                header
            );
        };

        return new PacketAndFinalIndex(packet, iterator.get());
    }

    private static Value parseValue(List<Integer> rawBits, AtomicInteger iterator, Header header) {
        boolean atEnd;
        List<Integer> bitsForValue = new ArrayList<>();
        do {
            atEnd = rawBits.get(iterator.get()) == 0;
            bitsForValue.addAll(rawBits.subList(iterator.incrementAndGet(), iterator.addAndGet(4)));
        } while (!atEnd);
        var value = combineBits(bitsForValue);

        return new Value(header, value);
    }

    private static Packet parsePacket(List<Integer> rawBits, AtomicInteger iterator, Type type,
                                      Header header) {
        var lengthTypeId = rawBits.get(iterator.getAndIncrement());

        BiFunction<Header, List<Packet>, Operator> generator = switch (type) {
            case SUM -> Sum::new;
            case PRODUCT -> Product::new;
            case MINIMUM -> Minimum::new;
            case MAXIMUM -> Maximum::new;
            case GREATER_THAN -> GreaterThan::new;
            case LESS_THAN -> LessThan::new;
            case EQUAL_TO -> EqualTo::new;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

        var subPackets = switch (lengthTypeId) {
            case 0 -> getLengthType0SubPackets(rawBits, iterator);
            case 1 -> getLengthType1SubPackets(rawBits, iterator);
            default -> throw new RuntimeException();
        };

        return generator.apply(header, subPackets);
    }

    private static List<Packet> getLengthType0SubPackets(List<Integer> rawBits, AtomicInteger iterator) {
        var length = combineBits(rawBits.subList(iterator.get(), iterator.addAndGet(15)));
        var haltingPosition = iterator.get() + length;
        var subPackets = new ArrayList<Packet>();
        while (iterator.get() < haltingPosition) {
            subPackets.add(parseSubPacket(rawBits, iterator));
        }
        return List.copyOf(subPackets);
    }

    private static List<Packet> getLengthType1SubPackets(List<Integer> rawBits, AtomicInteger iterator) {
        var numberOfPackets = combineBits(rawBits.subList(iterator.get(), iterator.addAndGet(11)));

        return LongStream.range(0, numberOfPackets)
            .mapToObj(i -> parseSubPacket(rawBits, iterator))
            .toList();
    }

    private static Packet parseSubPacket(List<Integer> rawBits, AtomicInteger iterator) {
        var newPacketAndFinalIndex = parsePacketRecursive(
            rawBits.subList(iterator.get(), rawBits.size())
        );
        iterator.addAndGet(newPacketAndFinalIndex.index());
        return newPacketAndFinalIndex.packet();
    }

    public static long combineBits(List<Integer> bits) {
        return Long.parseLong(bits.stream()
            .map(Object::toString)
            .collect(Collectors.joining()), 2);
    }
}
