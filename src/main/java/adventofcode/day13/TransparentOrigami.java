package adventofcode.day13;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.List;

@Day(13)
public abstract sealed class TransparentOrigami implements Solver {
    @Override
    public String solve(List<String> input) {
        if(input.isEmpty()) {
            return "0";
        }
        var board = OrigamiBoard.fromInput(input);
        return calculateResult(board);
    }

    abstract String calculateResult(OrigamiBoard board);

    @Part(1)
    public static final class PartOne extends TransparentOrigami{
        @Override
        String calculateResult(OrigamiBoard board) {
            board.processFold();
            var count = board.count();
            return Integer.toString(count);
        }
    }

    @Part(2)
    public static final class PartTwo extends TransparentOrigami{
        @Override
        String calculateResult(OrigamiBoard board) {
            board.processAllFolds();
            return board.print();
        }
    }
}
