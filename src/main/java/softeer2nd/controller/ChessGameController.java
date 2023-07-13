package softeer2nd.controller;

import static softeer2nd.controller.Command.*;

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

    public void start() {
        ChessGame chessGame = new ChessGame(
                new DefaultChessView(),
                new DefaultChessPointCalculator(),
                new DefaultBoard()
        );

        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        try {
            outputView.printCommandInputGuideLine(values());
            String command = inputView.command();
            if (isNotExistsCommand(command)) {
                throw new IllegalArgumentException("잘못된 커맨드 입력입니다.");
            }
            if (isEnd(command)) {
                return;
            }
            if (isStart(command)) {
                chessGame.initialize();
            }
            if (isMove(command)) {
                chessGame.move(moveSource(command), moveTarget(command));
            }
            outputView.print(chessGame.show());
        } catch (Exception e) {
            outputView.print(e.getMessage());
        }
        play(chessGame);
    }
}
