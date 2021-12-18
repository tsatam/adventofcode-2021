package adventofcode.day18;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.List;

@Day(18)
public abstract sealed class Snailfish implements Solver {
    private static final boolean LOG_REDUCE = true;
    private static final boolean LOG_SUM = true;

    @Override
    public String solve(List<String> input) {
        var finalElement = input.stream()
                .map(Snailfish::parseInput)
                .reduce(Element::add)
                .orElseThrow();

        return Integer.toString(finalElement.magnitude());
    }

    @Part(1)
    public static final class PartOne extends Snailfish {
    }

    @Part(2)
    public static final class PartTwo extends Snailfish {
    }

    private sealed interface Element permits Pair, Value {
        int magnitude();

        default Element add(Element other) {
            var result = new Pair(this, other).reduce();
            if(LOG_SUM) System.out.printf("  %s%n+ %s%n= %s%n%n", this, other, result);

            return result;
        }

        default Element reduce() {
            var original = this.toString();
            if(LOG_REDUCE) System.out.printf("    REDUCE:%n");
            if(LOG_REDUCE) System.out.printf("    %s%n", original);
            while (true) {
                var reduction = Reducer.reducePair(original);
                if (original.equals(reduction)) {
                    break;
                }
                original = reduction;
            }
            if(LOG_REDUCE) System.out.printf("%n");
            return parsePair(original);
        }
    }

    private record Pair(Element x, Element y) implements Element {
        @Override
        public int magnitude() {
            return x.magnitude() * 3 + y.magnitude() * 2;
        }

        @Override
        public String toString() {
            return "[%s,%s]".formatted(x().toString(), y().toString());
        }
    }

    private record Value(int value) implements Element {
        @Override
        public int magnitude() {
            return value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    public static Element parseInput(String input) {
        if (input.startsWith("[") && input.endsWith("]")) {
            return parsePair(input);
        } else {
            return parseValue(input);
        }
    }

    public static Pair parsePair(String pair) {
        var indexOfComma = indexOfCommaInPair(pair);
        var left = pair.substring(1, indexOfComma);
        var right = pair.substring(indexOfComma + 1, pair.length() - 1);
        return new Pair(parseInput(left), parseInput(right));
    }

    public static Value parseValue(String value) {
        return new Value(Integer.parseInt(value));
    }

    public static int indexOfCommaInPair(String pair) {
        var parens = 0;
        for (int i = 1; i < pair.length() - 1; i++) {
            switch (pair.charAt(i)) {
                case '[' -> parens++;
                case ']' -> parens--;
                case ',' -> {
                    if (parens == 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

}

