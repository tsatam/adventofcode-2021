package adventofcode.day4.giantsquid;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Parser {
    public static int[] getDraws(List<String> input) {
        return Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public static List<Board> getBoards(List<String> input) {
        return IntStream.iterate(2, i -> i < input.size(), i -> i + 6)
                .mapToObj(i -> input.subList(i, i + 5))
                .map(Board::fromInput)
                .toList();
    }
}
