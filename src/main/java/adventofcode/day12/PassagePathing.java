package adventofcode.day12;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Day(12)
public abstract sealed class PassagePathing implements Solver {
    @Override
    public String solve(List<String> input) {
        if (input.isEmpty()) {
            return "0";
        }
        var caveSystem = parseInput(input);
        var paths = new CaveTraverser(caveSystem, this::caveIsTraversable).traverse();

        var count = paths.stream()
                .filter(this::pathIsValid)
                .count();

        return Long.toString(count);
    }

    protected abstract Predicate<String> caveIsTraversable(List<String> currentPath);
    protected abstract boolean pathIsValid(List<String> path);

    @Part(1)
    public static final class PartOne extends PassagePathing {

        protected Predicate<String> caveIsTraversable(List<String> currentPath) {
            return cave -> switch (CaveType.fromCave(cave)) {
                case LARGE, END -> true;
                case SMALL, START -> !currentPath.contains(cave);
            };
        }

        protected boolean pathIsValid(List<String> path) {
            return path.stream()
                    .map(CaveType::fromCave)
                    .anyMatch(type -> type.equals(CaveType.SMALL));
        }
    }

    @Part(2)
    public static final class PartTwo extends PassagePathing {
        protected Predicate<String> caveIsTraversable(List<String> currentPath) {
            return cave -> {
                var type = CaveType.fromCave(cave);

                return switch (type) {
                    case START -> !currentPath.contains(cave);
                    case END, LARGE -> true;
                    case SMALL -> {
                        var countsOfSmallCavesTraversed = currentPath.stream()
                                .filter(c -> CaveType.fromCave(c).equals(CaveType.SMALL))
                                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

                        boolean caveHasNotYetBeenTraversed = !countsOfSmallCavesTraversed.containsKey(cave);
                        boolean noOtherCavesHaveBeenTraversedTwice = countsOfSmallCavesTraversed.values().stream().noneMatch(v -> v >= 2);
                        yield caveHasNotYetBeenTraversed || noOtherCavesHaveBeenTraversedTwice;
                    }
                };
            };
        }

        @Override
        protected boolean pathIsValid(List<String> path) {
            return true;
        }
    }

    private static Map<String, List<String>> parseInput(List<String> input){
        var map = new HashMap<String, List<String>>();
        for(var line : input){
            var split = line.split("-");

            if(!map.containsKey(split[0])) {
                map.put(split[0], new ArrayList<>());
            }
            if(!map.containsKey(split[1])) {
                map.put(split[1], new ArrayList<>());
            }
            map.get(split[0]).add(split[1]);
            map.get(split[1]).add(split[0]);
        }
        map.replaceAll((k, v) -> Collections.unmodifiableList(map.get(k)));
        return Collections.unmodifiableMap(map);
    }

    private static class CaveTraverser {
        private final Map<String, List<String>> caveSystem;
        private final Function<List<String>, Predicate<String>> caveIsTraversable;

        public CaveTraverser(Map<String, List<String>> caveSystem, Function<List<String>, Predicate<String>> caveIsTraversable) {
            this.caveSystem = caveSystem;
            this.caveIsTraversable = caveIsTraversable;
        }

        public Set<List<String>> traverse() {
            return traverse(List.of("start"));
        }

        private Set<List<String>> traverse(List<String> currentPath) {
            var currentCave = currentPath.get(currentPath.size() - 1);
            var connections = caveSystem.get(currentCave);

            if(CaveType.fromCave(currentCave).equals(CaveType.END)) {
                return Set.of(currentPath);
            }

            return connections.stream()
                    .filter(caveIsTraversable.apply(currentPath))
                    .map(cave -> {
                        var newPath = new ArrayList<>(currentPath);
                        newPath.add(cave);
                        return traverse(Collections.unmodifiableList(newPath));
                    })
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
        }
    }

    private enum CaveType {
        START, END, LARGE, SMALL;

        static CaveType fromCave(String cave) {
            if(Objects.equals(cave, "start")) return START;
            if(Objects.equals(cave, "end")) return END;
            if(cave.equals(cave.toUpperCase(Locale.ROOT))) return LARGE;
            return SMALL;
        }
    }

}
