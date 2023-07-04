package softeer2nd.controller;

import softeer2nd.domain.chess.Board;
import softeer2nd.view.InputView;
import softeer2nd.view.OutputView;

public class ChessGameController {
    private static final String START = "start";
    private static final String END = "end";

    private final OutputView outputView;
    private final InputView inputView;

    public ChessGameController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void main() {
        while (true) {
            String command = inputView.command();
            if (isNotExistsCommand(command)) {
                throw new IllegalArgumentException("잘못된 커맨드 입력입니다.");
            }
            if (command.equals(END)) {
                break;
            }
            Board board = new Board();
            board.initialize();
            outputView.print(board);
        }
    }

    private static boolean isNotExistsCommand(final String command) {
        return !command.equals(START) && !command.equals(END);
    }
}
