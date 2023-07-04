package softeer2nd.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("체스 판 관련 기능")
public class BoardTest {
    @DisplayName("체스 판을 생성한다.")
    @Test
    public void create() throws Exception {
        Board board = new Board();

        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        verifyAddPawn(board, white);

        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(black);
        verifyAddPawn(board, black);
    }

    @DisplayName("체스 판에 Pawn을 추가한다.")
    @Test
    public void add() {
        Board board = new Board();
        Pawn white = new Pawn(Pawn.WHITE_COLOR);

        board.add(white);

        verifyAddPawn(board, 1, white, 0);
    }

    private void verifyAddPawn(final Board board, final int boardSize, final Pawn pawn, final int pawnIndex) {
        assertAll(
                () -> assertThat(boardSize).isEqualTo(board.size()),
                () -> assertThat(pawn).isEqualTo(board.findPawn(pawnIndex))
        );
    }
}
