package adventofcode.day6;

import adventofcode.Solver;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract sealed class LanternfishSolver implements Solver {
    private static final int REPRODUCTION_TIME = 6;
    private static final int DEFAULT_TIMER = 8;
    private static final Map<String, BigInteger> dp = new HashMap<>();

    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }

        return parseInput(input.get(0))
                .mapToObj(timer -> calculateCount(timer, daysToSimulate()))
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();
    }

    protected abstract int daysToSimulate();

    private static IntStream parseInput(String input) {
        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt);
    }

    private static BigInteger calculateCount(int timer, int days) {
        if (days == 0) {
            return BigInteger.ONE;
        }
        var key = "%d-%d".formatted(timer, days);
        if (dp.containsKey(key)) {
            return dp.get(key);
        } else {
            BigInteger result;
            if (timer == 0) {
                result = calculateCount(REPRODUCTION_TIME, days - 1)
                        .add(calculateCount(DEFAULT_TIMER, days - 1));
            } else {
                result = calculateCount(timer - 1, days - 1);
            }
            dp.put(key, result);
            return result;
        }
    }

    public static final class PartOne extends LanternfishSolver {
        @Override
        protected int daysToSimulate() {
            return 80;
        }
    }

    public static final class PartTwo extends LanternfishSolver {

        @Override
        protected int daysToSimulate() {
            return 256;
        }
    }


}
