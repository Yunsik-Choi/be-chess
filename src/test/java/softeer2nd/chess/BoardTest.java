package softeer2nd.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

@DisplayName("체스 판 관련 기능")
public class BoardTest {

    private Board board;
    private Pawn white;
    private Pawn black;

    @BeforeEach
    void setUp() {
        this.board = new Board();
        this.white = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        this.black = new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    @DisplayName("체스 판을 생성한다.")
    @Test
    public void create() {
        board.add(white);
        verifyAddPawn(board, 1, white, 0);

        board.add(black);
        verifyAddPawn(board, 2, black, 1);
    }

    @DisplayName("체스 판에 Pawn을 추가한다.")
    @Test
    public void add() {
        board.add(white);

        verifyAddPawn(board, 1, white, 0);
    }

    @DisplayName("체스 판에서 Pawn을 추가된 순번으로 가져온다.")
    @Test
    public void findPawn() {
        board.add(white);
        board.add(black);

        assertAll(
                () -> assertThat(board.findPawn(0)).isEqualTo(white),
                () -> assertThat(board.findPawn(1)).isEqualTo(black)
        );
    }

    @DisplayName("체스판의 기물 수보다 큰 순번으로 가져올 경우 예외 처리한다.")
    @Test
    void findPawnIndexOutOfBoundsException() {
        board.add(white);

        assertThatThrownBy(() -> board.findPawn(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("체스 판에 추가된 Pawn 의 개수를 반환한다.")
    @Test
    void size() {
        assertThat(board.size()).isZero();

        board.add(white);

        assertThat(board.size()).isOne();

        board.add(black);

        assertThat(board.size()).isEqualTo(2);
    }

    @DisplayName("체스판을 초기화한다.")
    @Test
    void initialize() {
        board.initialize();

        assertAll(
                () -> assertEquals("pppppppp", board.getWhitePawnsResult()),
                () -> assertEquals("PPPPPPPP", board.getBlackPawnsResult())
        );
    }

    private void verifyAddPawn(final Board board, final int boardSize, final Pawn pawn, final int pawnIndex) {
        assertAll(
                () -> assertThat(boardSize).isEqualTo(board.size()),
                () -> assertThat(pawn).isEqualTo(board.findPawn(pawnIndex))
        );
    }
}
