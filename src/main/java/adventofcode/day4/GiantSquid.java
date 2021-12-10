package adventofcode.day4;

import adventofcode.Solver;
import adventofcode.annotations.Day;
import adventofcode.annotations.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Day(4)
public abstract sealed class GiantSquid implements Solver {
    @Override
    public String solve(List<String> input) {
        if(input.size() < 7 || input.size() % 6 != 1) {
            return "0";
        }
        var draws = Parser.getDraws(input);
        var boards = Parser.getBoards(input);
        var winningScore = calculateWinningScore(draws, boards);
        return Integer.toString(winningScore);
    }

    protected abstract int calculateWinningScore(int[] draws, List<Board> boards);

    @Part(1)
    public static final class PartOne extends GiantSquid {
        @Override
        protected int calculateWinningScore(int[] draws, List<Board> boards) {
            for (int draw : draws) {
                for (Board board : boards) {
                    board.applyDraw(draw);

                    if (board.hasWon()) {
                        int sum = board.calculateSum();
                        return sum * draw;
                    }
                }
            }
            return 0;
        }
    }

    @Part(2)
    public static final class PartTwo extends GiantSquid {
        @Override
        protected int calculateWinningScore(int[] draws, List<Board> boards) {
            var boardsStillInPlay = new ArrayList<>(boards);
            var winningBoardScores = new Stack<Integer>();

            for(var draw : draws) {
                for(var board : List.copyOf(boardsStillInPlay)) {
                    board.applyDraw(draw);

                    if(board.hasWon()) {
                        int sum = board.calculateSum();
                        winningBoardScores.push(sum * draw);
                        boardsStillInPlay.remove(board);
                    }
                }
            }
            return winningBoardScores.pop();
        }
    }
}
