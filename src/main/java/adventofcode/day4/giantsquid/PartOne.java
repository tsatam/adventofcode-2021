package adventofcode.day4.giantsquid;

import adventofcode.Solver;

import java.util.List;

public class PartOne implements Solver {
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
        var boards = Parser.getBoards(input);

        for(var draw : draws) {
            for(var board : boards) {
                board.applyDraw(draw);

                if(board.hasWon()) {
                    int sum = board.calculateSum();
                    return sum * draw;
                }
            }
        }
        return 0;
    }
}
