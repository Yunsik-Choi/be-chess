package softeer2nd.controller;

import softeer2nd.domain.chess.ChessGame;
import softeer2nd.domain.chess.DefaultBoard;
import softeer2nd.domain.chess.DefaultChessPointCalculator;
import softeer2nd.domain.chess.DefaultChessView;
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
        ChessGame chessGame = new ChessGame(new DefaultChessView(), new DefaultChessPointCalculator(),
                new DefaultBoard());
        while (true) {
            try {
                String command = inputView.command();
                if (Command.isNotExistsCommand(command)) {
                    throw new IllegalArgumentException("잘못된 커맨드 입력입니다.");
                }
                if (Command.isEnd(command)) {
                    break;
                }
                if (Command.isStart(command)) {
                    chessGame.initialize();
                }
                if (Command.isMove(command)) {
                    chessGame.move(Command.moveSource(command), Command.moveTarget(command));
                }
                outputView.print(chessGame.show());
            } catch (Exception e) {
                outputView.print(e.getMessage());
            }
        }
    }
}
