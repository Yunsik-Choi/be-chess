package softeer2nd;

import softeer2nd.controller.ChessGameController;
import softeer2nd.domain.chess.Board;
import softeer2nd.view.ConsoleOutputView;

public class Main {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new ConsoleOutputView());

        Board board = new Board();

        board.initialize();

        chessGameController.print(board);
    }
}
