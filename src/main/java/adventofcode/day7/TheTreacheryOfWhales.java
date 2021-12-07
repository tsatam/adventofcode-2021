package adventofcode.day7;

import adventofcode.Solver;

import java.util.Arrays;
import java.util.List;

public abstract sealed class TheTreacheryOfWhales implements Solver {
    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }

        var crabs = parseInput(input.get(0));

        var summary = Arrays.stream(crabs)
            .summaryStatistics();

        var minimumFuelConsumption = Integer.MAX_VALUE;

        for (int positionToCheck = summary.getMin(); positionToCheck <= summary.getMax(); positionToCheck++) {
            var fuelConsumption = calculateFuelConsumption(crabs, positionToCheck);
            if (fuelConsumption < minimumFuelConsumption) {
                minimumFuelConsumption = fuelConsumption;
            }
        }

        return Integer.toString(minimumFuelConsumption);
    }

    protected abstract int calculateFuelConsumption(int[] crabs, int positionToCheck);

    private static int[] parseInput(String input) {
        return Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    public static final class PartOne extends TheTreacheryOfWhales {
        protected int calculateFuelConsumption(int[] crabs, int positionToCheck) {
            return Arrays.stream(crabs)
                .map(position -> positionToCheck - position)
                .map(Math::abs)
                .sum();
        }
    }

    public static final class PartTwo extends TheTreacheryOfWhales {
        protected int calculateFuelConsumption(int[] crabs, int positionToCheck) {
            return Arrays.stream(crabs)
                .map(position -> positionToCheck - position)
                .map(Math::abs)
                .map(PartTwo::calculateSumFromOneToValue)
                .sum();
        }

        private static int calculateSumFromOneToValue(int value) {
            return (value * (value + 1)) / 2;
        }
    }
}
