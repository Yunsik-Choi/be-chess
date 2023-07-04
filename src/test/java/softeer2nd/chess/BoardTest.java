package softeer2nd.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

@DisplayName("체스 판 관련 기능")
public class BoardTest {
    @DisplayName("체스 판을 생성한다.")
    @Test
    public void create() {
        Board board = new Board();

        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        verifyAddPawn(board, 1, white, 0);

        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(black);
        verifyAddPawn(board, 2, black, 1);
    }

    @DisplayName("체스 판에 Pawn을 추가한다.")
    @Test
    public void add() {
        Board board = new Board();
        Pawn white = new Pawn(Pawn.WHITE_COLOR);

        board.add(white);

        verifyAddPawn(board, 1, white, 0);
    }

    @DisplayName("체스 판에서 Pawn을 추가된 순번으로 가져온다.")
    @Test
    public void findPawn() {
        Board board = new Board();
        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        Pawn black = new Pawn(Pawn.BLACK_COLOR);

        board.add(white);
        board.add(black);

        assertAll(
                () -> assertThat(board.findPawn(0)).isEqualTo(white),
                () -> assertThat(board.findPawn(1)).isEqualTo(black)
        );
    }

    @DisplayName("체스 판에 추가된 Pawn 의 개수를 반환한다.")
    @Test
    void size() {
        Board board = new Board();

        assertThat(board.size()).isZero();

        Pawn white = new Pawn(Pawn.WHITE_COLOR);

        board.add(white);

        assertThat(board.size()).isOne();

        Pawn black = new Pawn(Pawn.BLACK_COLOR);

        board.add(black);

        assertThat(board.size()).isEqualTo(2);
    }

    private void verifyAddPawn(final Board board, final int boardSize, final Pawn pawn, final int pawnIndex) {
        assertAll(
                () -> assertThat(boardSize).isEqualTo(board.size()),
                () -> assertThat(pawn).isEqualTo(board.findPawn(pawnIndex))
        );
    }
}
