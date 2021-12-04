package adventofcode.day4.giantsquid;

import adventofcode.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PartTwo implements Solver {
    @Override
    public String solve(List<String> input) {
        if(input.size() < 7 || input.size() % 6 != 1) {
            return "0";
        }

        var winningScore = calculateWinningScore(input);

        return Integer.toString(winningScore);
    }

    private int calculateWinningScore(List<String> input) {
        var draws = Parser.getDraws(input);
        var boards = new ArrayList<>(Parser.getBoards(input));
        var winningBoardScores = new Stack<Integer>();

        for(var draw : draws) {
            for(var board : List.copyOf(boards)) {
                board.applyDraw(draw);

                if(board.hasWon()) {
                    int sum = board.calculateSum();
                    winningBoardScores.push(sum * draw);
                    boards.remove(board);
                }
            }
        }
        return winningBoardScores.pop();
    }
}
