package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.common.util.StringUtils.appendNewLine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;
import softeer2nd.domain.chess.pieces.Position;

@DisplayName("체스 판 관련 기능")
public class BoardTest {
    private Board board;

    private Position position;

    @BeforeEach
    void setUp() {
        this.board = new Board();
        this.position = new Position("a1");
    }

    @DisplayName("체스판을 초기화한다.")
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

        assertEquals(Piece.createBlackRook(new Position("a8")), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(new Position("h8")), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(new Position("a1")), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(new Position("h1")), board.findPiece("h1"));
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
        Piece piece = Piece.createBlackRook(this.position);
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
    }

    @DisplayName("체스 프로그램 점수를 계산한다.")
    @Test
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(position));
        addPiece("e6", Piece.createBlackQueen(position));
        addPiece("b8", Piece.createBlackKing(position));
        addPiece("c8", Piece.createBlackRook(position));

        addPiece("f2", Piece.createWhitePawn(position));
        addPiece("g2", Piece.createWhitePawn(position));
        addPiece("e1", Piece.createWhiteRook(position));
        addPiece("f1", Piece.createWhiteKing(position));

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);
    }

    @DisplayName("기물의 점수가 높은 순으로 정렬한다.")
    @Test
    void sortByPointDesc() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(position));
        addPiece("e6", Piece.createBlackQueen(position));
        addPiece("b8", Piece.createBlackKing(position));
        addPiece("c8", Piece.createBlackRook(position));

        addPiece("f2", Piece.createWhitePawn(position));
        addPiece("g2", Piece.createWhitePawn(position));
        addPiece("e1", Piece.createWhiteRook(position));
        addPiece("f1", Piece.createWhiteKing(position));

        assertAll(
                () -> assertThat(board.sortByPoint(Color.BLACK, PieceComparator.SORT_BY_POINT_DESC)).containsExactly(
                        Piece.createBlackQueen(position),
                        Piece.createBlackRook(position),
                        Piece.createBlackPawn(position),
                        Piece.createBlackKing(position)
                ),
                () -> assertThat(board.sortByPoint(Color.WHITE, PieceComparator.SORT_BY_POINT_DESC)).containsExactly(
                        Piece.createWhiteRook(position),
                        Piece.createWhitePawn(position),
                        Piece.createWhitePawn(position),
                        Piece.createWhiteKing(position)
                )
        );
    }

    @DisplayName("기물의 점수가 낮은 순으로 정렬한다.")
    @Test
    void sortByPointAsc() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(position));
        addPiece("e6", Piece.createBlackQueen(position));
        addPiece("b8", Piece.createBlackKing(position));
        addPiece("c8", Piece.createBlackRook(position));

        addPiece("f2", Piece.createWhitePawn(position));
        addPiece("g2", Piece.createWhitePawn(position));
        addPiece("e1", Piece.createWhiteRook(position));
        addPiece("f1", Piece.createWhiteKing(position));

        assertAll(
                () -> assertThat(board.sortByPoint(Color.BLACK, PieceComparator.SORT_BY_POINT_ASC)).containsExactly(
                        Piece.createBlackKing(position),
                        Piece.createBlackPawn(position),
                        Piece.createBlackRook(position),
                        Piece.createBlackQueen(position)
                ),
                () -> assertThat(board.sortByPoint(Color.WHITE, PieceComparator.SORT_BY_POINT_ASC)).containsExactly(
                        Piece.createWhiteKing(position),
                        Piece.createWhitePawn(position),
                        Piece.createWhitePawn(position),
                        Piece.createWhiteRook(position)
                )
        );
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
