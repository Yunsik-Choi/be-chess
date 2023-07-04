package softeer2nd.controller;

import softeer2nd.domain.chess.Board;
import softeer2nd.view.OutputView;

public class ChessGameController {
    private final OutputView outputView;

    public ChessGameController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void print(final Board board) {
        outputView.print(board);
    }
}
