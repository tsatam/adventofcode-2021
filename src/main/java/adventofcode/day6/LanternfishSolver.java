package adventofcode.day6;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Day(6)
public abstract sealed class LanternfishSolver implements Solver {
    private static final int REPRODUCTION_TIME = 6;
    private static final int DEFAULT_TIMER = 8;

    private final BigInteger[] fishAtDays = new BigInteger[DEFAULT_TIMER + 1];

    public LanternfishSolver() {
        Arrays.fill(fishAtDays, BigInteger.ZERO);
    }

    @Override
    public String solve(List<String> input) {
        if(input.isEmpty()) {
            return "0";
        }
        readInput(input.get(0));

        for(int day = 0; day < daysToSimulate(); day++) {
            var fishAt0 = fishAtDays[0];

            System.arraycopy(fishAtDays, 1, fishAtDays, 0, fishAtDays.length - 1);

            fishAtDays[REPRODUCTION_TIME] = fishAtDays[REPRODUCTION_TIME].add(fishAt0);
            fishAtDays[DEFAULT_TIMER] = fishAt0;
        }
        return Arrays.stream(fishAtDays)
            .reduce(BigInteger.ZERO, BigInteger::add)
            .toString();
    }

    private void readInput(String input) {
        Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .forEach(timer -> fishAtDays[timer] = fishAtDays[timer].add(BigInteger.ONE));
    }

    protected abstract int daysToSimulate();

    @Part(1)
    public static final class PartOne extends LanternfishSolver {
        @Override
        protected int daysToSimulate() {
            return 80;
        }
    }

    @Part(2)
    public static final class PartTwo extends LanternfishSolver {
        @Override
        protected int daysToSimulate() {
            return 256;
        }
    }
}
