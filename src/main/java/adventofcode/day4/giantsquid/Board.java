package adventofcode.day4.giantsquid;

import java.util.Arrays;
import java.util.List;

public class Board {
    private int[][] board;

    public Board(int[][] board) {
        this.board = board;
    }

    public static Board fromInput(List<String> input) {
        int[][] board = new int[5][5];
        for(int row = 0; row < 5; row++){
            var inputForRow = input.get(row);
            for(int column = 0; column < 5; column++) {
                board[row][column] = Integer.parseInt(inputForRow.substring(column * 3, column * 3 + 2).trim());
            }
        }

        return new Board(board);
    }

    public void applyDraw(int draw) {
        for(int row = 0; row < 5; row++){
            for(int column = 0; column < 5; column++) {
                if(board[row][column] == draw) {
                    board[row][column] = Integer.MIN_VALUE;
                }
            }
        }
    }

    public boolean hasWon() {
        for(int row = 0; row < 5; row++) {
            var rowHasWon = Arrays.stream(board[row])
                    .allMatch(value -> value == Integer.MIN_VALUE);
            if(rowHasWon) {
                return true;
            }
        }

        for(int column = 0; column < 5; column++) {
            int finalColumn = column;
            var columnHasWon = Arrays.stream(board)
                    .mapToInt(row -> row[finalColumn])
                    .allMatch(value -> value == Integer.MIN_VALUE);
            if(columnHasWon) {
                return true;
            }
        }

        return false;
    }

    public int calculateSum() {
        return Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(value -> value >= 0)
                .sum();
    }
}
