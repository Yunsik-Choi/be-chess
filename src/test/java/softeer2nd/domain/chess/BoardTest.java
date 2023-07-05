package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Point;

@DisplayName("체스 판 관련 기능")
public class BoardTest {
    private Board board;
    private Piece white;
    private Piece black;

    @BeforeEach
    void setUp() {
        this.board = new Board();
        this.white = new Piece(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION, new Point(0, 0));
        this.black = new Piece(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION, new Point(0, 1));
    }

    @DisplayName("체스 판을 생성한다.")
    @Test
    public void create() {
        board.add(white);
        verifyAddPiece(board, 1, white, 0);

        board.add(black);
        verifyAddPiece(board, 2, black, 1);
    }

    @DisplayName("체스 판에 Piece을 추가한다.")
    @Test
    public void add() {
        board.add(white);

        verifyAddPiece(board, 1, white, 0);
    }

    @DisplayName("체스 판에서 Piece을 추가된 순번으로 가져온다.")
    @Test
    public void findPiece() {
        board.add(white);
        board.add(black);

        assertAll(
                () -> assertThat(board.findPiece(0)).isEqualTo(white),
                () -> assertThat(board.findPiece(1)).isEqualTo(black)
        );
    }

    @DisplayName("체스판의 기물 수보다 큰 순번으로 가져올 경우 예외 처리한다.")
    @Test
    void findPieceIndexOutOfBoundsException() {
        board.add(white);

        assertThatThrownBy(() -> board.findPiece(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("체스 판에 추가된 Piece 의 개수를 반환한다.")
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
                () -> assertEquals("pppppppp", board.getWhitePiecesResult()),
                () -> assertEquals("PPPPPPPP", board.getBlackPiecesResult())
        );
    }

    @DisplayName("흰색 Piece의 결과를 출력한다.")
    @Test
    void getWhitePieceResult() {
        board.initialize();

        assertEquals("pppppppp", board.getWhitePiecesResult());
    }

    @DisplayName("검은색 Piece의 결과를 출력한다.")
    @Test
    void getBlackPieceResult() {
        board.initialize();

        assertEquals("PPPPPPPP", board.getBlackPiecesResult());
    }

    private void verifyAddPiece(final Board board, final int boardSize, final Piece piece, final int PieceIndex) {
        assertAll(
                () -> assertThat(boardSize).isEqualTo(board.size()),
                () -> assertThat(piece).isEqualTo(board.findPiece(PieceIndex))
        );
    }

    @DisplayName("체스판을 출력한다.")
    @Test
    void show() {
        board.initialize();

        assertThat(board.show()).isEqualTo(
                "........\n"
                        + "PPPPPPPP\n"
                        + "........\n"
                        + "........\n"
                        + "........\n"
                        + "........\n"
                        + "pppppppp\n"
                        + "........\n"
        );
    }
}
