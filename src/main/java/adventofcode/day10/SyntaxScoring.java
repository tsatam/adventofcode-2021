package adventofcode.day10;

import adventofcode.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public abstract sealed class SyntaxScoring implements Solver {
    @Override
    public String solve(List<String> input) {
        var result = getResult(input);

        return result.toString();
    }

    protected abstract Number getResult(List<String> input);


    public static final class PartOne extends SyntaxScoring {
        @Override
        protected Number getResult(List<String> input) {
            return input.stream()
                .map(SyntaxScoring::processLine)
                .filter(result -> result instanceof Result.Invalid)
                .map(invalid -> ((Result.Invalid) invalid).invalidCharacter())
                .mapToInt(SyntaxScoring::scoreIllegalCharacters)
                .sum();
        }
    }

    public static final class PartTwo extends SyntaxScoring {
        @Override
        protected Number getResult(List<String> input) {
            if(input.isEmpty()) return 0;
            var results = input.stream()
                .map(SyntaxScoring::processLine)
                .filter(result -> result instanceof Result.Incomplete)
                .map(incomplete -> ((Result.Incomplete) incomplete).bracketsRemaining())
                .mapToLong(PartTwo::calculateCompletionScore)
                .sorted()
                .toArray();

            return results[results.length / 2];
        }

        private static long calculateCompletionScore(Stack<Character> closingBrackets) {
            return fromStack(closingBrackets)
                .map(SyntaxScoring::scoreIncompleteCharacters)
                .mapToLong(Integer::longValue)
                .reduce(0L, (score, nextValue) -> (5 * score) + nextValue);
        }

        private static Stream<Character> fromStack(Stack<Character> stack) {
            var list = new ArrayList<Character>();
            while(!stack.isEmpty()) {
                list.add(stack.pop());
            }
            return list.stream();
        }
    }

    private static int scoreIllegalCharacters(char c) {
        return switch (c) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
    }

    private static int scoreIncompleteCharacters(char c) {
        return switch (c) {
            case ')' -> 1;
            case ']' -> 2;
            case '}' -> 3;
            case '>' -> 4;
            default -> 0;
        };
    }

    private static Result processLine(String line) {
        Stack<Character> currentChunk = new Stack<>();
        for (char c : line.toCharArray()) {
            switch (c) {
                case '(', '[', '{', '<' -> {
                    var closingBracket = getClosingBracket(c);
                    currentChunk.push(closingBracket);
                }

                case ')', ']', '}', '>' -> {
                    if (currentChunk.isEmpty() || currentChunk.pop() != c) return new Result.Invalid(c);
                }
            }
        }
        return new Result.Incomplete(currentChunk);
    }

    private static char getClosingBracket(char openingBracket) {
        return switch (openingBracket) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            case '<' -> '>';
            default -> throw new RuntimeException();
        };
    }

    private sealed interface Result {
        record Incomplete(Stack<Character> bracketsRemaining) implements Result {}
        record Invalid(Character invalidCharacter) implements Result {}
    }
}
