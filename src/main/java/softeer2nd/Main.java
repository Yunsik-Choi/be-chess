package softeer2nd;

import softeer2nd.controller.ChessGameController;
import softeer2nd.view.ConsoleInputView;
import softeer2nd.view.ConsoleOutputView;

public class Main {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(
                new ConsoleOutputView(),
                new ConsoleInputView()
        );

        chessGameController.main();
    }
}
