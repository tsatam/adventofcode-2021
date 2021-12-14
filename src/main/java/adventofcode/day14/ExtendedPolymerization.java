package adventofcode.day14;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Day(14)
public abstract sealed class ExtendedPolymerization implements Solver {

    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }

        var polymer = new Polymer(input);
        polymer.applyRules(numberOfSteps());
        var output = polymer.differenceBetweenMaxAndMinCharacters();

        return Long.toString(output);
    }

    protected abstract int numberOfSteps();

    @Part(1)
    public static final class PartOne extends ExtendedPolymerization {

        @Override
        protected int numberOfSteps() {
            return 10;
        }
    }

    @Part(2)
    public static final class PartTwo extends ExtendedPolymerization {
        @Override
        protected int numberOfSteps() {
            return 40;
        }
    }


    private static class Polymer {
        private final List<InsertionRule> insertionRules;

        private Map<String, Long> pairCounts = new HashMap<>();

        public Polymer(List<String> input) {
            String template = input.get(0);
            insertionRules = input.subList(2, input.size())
                .stream()
                .map(InsertionRule::fromInput)
                .toList();


            for(int i = 0; i < template.length() - 1; i++) {
                pairCounts.compute(template.substring(i, i + 2), (k, v) -> v == null ? (long) 1 : v + (long) 1);
            }
            pairCounts.put(template.substring(template.length() - 1), 1L);
        }

        public void applyRules(int times) {
            for(int i = 0; i < times; i++) {
                applyRules();
            }
        }

        public void applyRules() {
            var newPairCounts = new HashMap<>(pairCounts);

            for (var rule : insertionRules) {
                if(pairCounts.containsKey(rule.pair())) {
                    var count = pairCounts.get(rule.pair());
                    var newLeadingPair = rule.pair().charAt(0) + "" + rule.insertion();
                    var newTrailingPair = rule.insertion() + "" + rule.pair().charAt(1);

                    newPairCounts.compute(newLeadingPair, (k, v) -> v == null ? count : v + count);
                    newPairCounts.compute(newTrailingPair, (k, v) -> v == null ? count : v + count);
                    newPairCounts.compute(rule.pair(), (k, v) -> v == null ? 0 : v - count);
                }
            }
            this.pairCounts = newPairCounts;
        }

        public long differenceBetweenMaxAndMinCharacters() {
            var ss = pairCounts.entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getKey().charAt(0), Collectors.summingLong(Map.Entry::getValue)))
                .values()
                .stream()
                .mapToLong(l -> l)
                .summaryStatistics();

            return ss.getMax() - ss.getMin();
        }
    }

    private record InsertionRule(String pair, char insertion) {
        public static InsertionRule fromInput(String input) {
            var split = input.split(" -> ");
            var pair = split[0];
            var output = split[1].charAt(0);
            return new InsertionRule(pair, output);
        }
    }
}

