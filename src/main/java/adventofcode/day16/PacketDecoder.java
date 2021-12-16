package adventofcode.day16;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;
import adventofcode.day16.packet.Packet;

import java.util.List;

@Day(16)
public abstract sealed class PacketDecoder implements Solver {
    @Override
    public String solve(List<String> input) {
        assert input.size() == 1;

        var bits = Parser.parseBits(input.get(0));
        var packet = Parser.parsePacket(bits);
        return Long.toString(getResult(packet));
    }

    protected abstract long getResult(Packet packet);

    @Part(1)
    public static final class PartOne extends PacketDecoder {
        @Override
        protected long getResult(Packet packet) {
            return packet.getVersionSum();
        }
    }

    @Part(2)
    public static final class PartTwo extends PacketDecoder {
        @Override
        protected long getResult(Packet packet) {
            return packet.value();
        }
    }

}
