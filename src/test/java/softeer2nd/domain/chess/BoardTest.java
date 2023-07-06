package softeer2nd.domain.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.common.util.StringUtils.appendNewLine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;

@DisplayName("체스 판 관련 기능")
public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }

    @Test
    public void create() {
        board.initialize();
        assertEquals(32, board.pieceCount());

        String blankRank = appendNewLine("........");

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.show());
    }

    @DisplayName("기물의 색과 종류를 받아 개수를 반환한다.")
    @Test
    void pieceCount() {
        board.initialize();

        Assertions.assertAll(
                () -> assertEquals(8, board.pieceCount(Color.WHITE, Type.PAWN)),
                () -> assertEquals(8, board.pieceCount(Color.BLACK, Type.PAWN)),
                () -> assertEquals(2, board.pieceCount(Color.BLACK, Type.ROOK)),
                () -> assertEquals(1, board.pieceCount(Color.BLACK, Type.QUEEN))
        );
    }

    @DisplayName("주어진 위치의 기물을 조회한다.")
    @Test
    public void findPiece() {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @DisplayName("빈 체스판을 생성한다.")
    @Test
    void initializeEmpty() {
        board.initializeEmpty();

        String blankRank = appendNewLine("........");

        assertEquals(
                blankRank + blankRank + blankRank + blankRank + blankRank + blankRank + blankRank + blankRank,
                board.show()
        );
    }

    @DisplayName("임의의 기물을 체스판 위에 추가한다.")
    @Test
    void move() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
    }
}
