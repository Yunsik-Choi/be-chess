package softeer2nd.controller;

import softeer2nd.domain.chess.Board;
import softeer2nd.view.InputView;
import softeer2nd.view.OutputView;

public class ChessGameController {
    private final OutputView outputView;
    private final InputView inputView;

    public ChessGameController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void main() {
        Board board = new Board();
        while (true) {
            String command = inputView.command();
            if (Command.isNotExistsCommand(command)) {
                throw new IllegalArgumentException("잘못된 커맨드 입력입니다.");
            }
            if (Command.isEnd(command)) {
                break;
            }
            if (Command.isStart(command)) {
                board.initialize();
            }
            if (Command.isMove(command)) {
                board.move(Command.moveSource(command), Command.moveTarget(command));
            }
            outputView.print(board.show());
        }
    }
}
