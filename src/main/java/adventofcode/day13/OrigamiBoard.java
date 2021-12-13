package adventofcode.day13;

import adventofcode.common.Board;
import adventofcode.common.BoundingBox;
import adventofcode.common.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class OrigamiBoard {
    private Board board;
    private final Queue<Fold> folds;

    private OrigamiBoard(Board board, Queue<Fold> folds) {
        this.board = board;
        this.folds = folds;
    }

    public static OrigamiBoard fromInput(List<String> input) {
        var divider = input.indexOf("");
        var points = input.subList(0, divider)
                .stream()
                .map(Point::fromInput)
                .toList();
        var folds = input.subList(divider + 1, input.size())
                .stream()
                .map(Fold::fromInput)
                .toList();
        var boundingBox = BoundingBox.fromPoints(points);

        var board = Board.fromBoundingBox(boundingBox);

        for (var point : points) {
            board.set(point, 1);
        }

        return new OrigamiBoard(board, new LinkedList<>(folds));
    }

    public void processAllFolds() {
        while(!folds.isEmpty()) {
            processFold();
        }

        return;
    }

    public String print() {
        var points = board.points();
        StringBuilder b = new StringBuilder();
        for (int[] point : points) {
            for (int i : point) {
                var c = switch (i) {
                    case 1 -> '#';
                    case 0 -> ' ';
                    default -> '.';
                };
                b.append(c);
            }
            b.append(System.lineSeparator());
        }
        return b.toString();
    }

    public void processFold() {
        var fold = folds.remove();

        var oldSize = board.size();
        var oldXBound = oldSize.max().x() + 1;
        var oldYBound = oldSize.max().y() + 1;

        this.board = switch (fold.direction()) {
            case X -> {
                var newBoard = new Board(new int[oldYBound][fold.position()]);

                for (var x = 0; x < fold.position(); x++) {
                    for (var y = 0; y < oldYBound; y++) {
                        var p = new Point(x, y);
                        if (board.at(p) == 1) {
                            newBoard.set(p, 1);
                        }
                    }
                }
                for (var x = fold.position() + 1; x < oldXBound; x++) {
                    for (var y = 0; y < oldYBound; y++) {
                        var oldP = new Point(x, y);
                        var newP = new Point((2 * fold.position()) - x, y);
                        if (board.at(oldP) == 1) {
                            newBoard.set(newP, 1);
                        }
                    }
                }

                yield newBoard;
            }
            case Y -> {
                var newBoard = new Board(new int[fold.position()][oldXBound]);

                for (var x = 0; x < oldXBound; x++) {
                    for (var y = 0; y < fold.position(); y++) {
                        var p = new Point(x, y);
                        if (board.at(p) == 1) {
                            newBoard.set(p, 1);
                        }
                    }
                }
                for (var x = 0; x < oldXBound; x++) {
                    for (var y = fold.position() + 1; y < oldYBound; y++) {
                        var oldP = new Point(x, y);
                        var newP = new Point(x, (2 * fold.position()) - y);
                        if (board.at(oldP) == 1) {
                            newBoard.set(newP, 1);
                        }
                    }
                }

                yield newBoard;
            }
        };
    }

    public int count() {
        return board.sum();
    }
}
