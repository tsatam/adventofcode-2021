package adventofcode.day2.dive;

import java.util.List;

public class Parser {
    public static List<Command> parseInput(List<String> input) {
        return input.stream().map(Command::fromInput).toList();
    }
}
