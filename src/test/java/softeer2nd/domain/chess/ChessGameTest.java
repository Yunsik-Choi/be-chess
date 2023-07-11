package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.common.util.StringUtils.appendNewLine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Position;

@DisplayName("체스 판 관련 기능")
public class ChessGameTest {
    private ChessGame chessGame;

    private Position position;

    @BeforeEach
    void setUp() {
        this.chessGame = new ChessGame(new DefaultChessView(), new DefaultChessPointCalculator(), new DefaultBoard());
        this.position = new Position("a1");
    }

    @DisplayName("체스판을 초기화한다.")
    @Test
    public void create() {
        chessGame.initialize();
        assertEquals(32, chessGame.pieceCount());

        String blankRank = appendNewLine("........");

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                chessGame.show());
    }

    @DisplayName("주어진 위치의 기물을 조회한다.")
    @Test
    public void findPiece() {
        chessGame.initialize();

        assertEquals(Piece.createBlackRook(new Position("a8")), chessGame.findPiece("a8"));
        assertEquals(Piece.createBlackRook(new Position("h8")), chessGame.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(new Position("a1")), chessGame.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(new Position("h1")), chessGame.findPiece("h1"));
    }

    @DisplayName("빈 체스판을 생성한다.")
    @Test
    void initializeEmpty() {
        chessGame.initializeEmpty();

        String blankRank = appendNewLine("........");

        assertEquals(
                blankRank + blankRank + blankRank + blankRank + blankRank + blankRank + blankRank + blankRank,
                chessGame.show()
        );
    }

    @DisplayName("임의의 기물을 체스판 위에 추가한다.")
    @Test
    void addPiece() {
        chessGame.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook(new Position(position));
        chessGame.addPiece(position, piece);

        assertEquals(piece, chessGame.findPiece(position));
    }

    @DisplayName("체스 프로그램 점수를 계산한다.")
    @Test
    void calculatePoint() {
        chessGame.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(position));
        addPiece("e6", Piece.createBlackQueen(position));
        addPiece("b8", Piece.createBlackKing(position));
        addPiece("c8", Piece.createBlackRook(position));

        addPiece("f2", Piece.createWhitePawn(position));
        addPiece("g2", Piece.createWhitePawn(position));
        addPiece("e1", Piece.createWhiteRook(position));
        addPiece("f1", Piece.createWhiteKing(position));

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);
    }

    @DisplayName("기물의 점수가 높은 순으로 정렬한다.")
    @Test
    void sortByPointDesc() {
        chessGame.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(new Position("b6")));
        addPiece("e6", Piece.createBlackQueen(new Position("e6")));
        addPiece("b8", Piece.createBlackKing(new Position("b8")));
        addPiece("c8", Piece.createBlackRook(new Position("c8")));

        addPiece("f2", Piece.createWhitePawn(new Position("f2")));
        addPiece("g2", Piece.createWhitePawn(new Position("g2")));
        addPiece("e1", Piece.createWhiteRook(new Position("e1")));
        addPiece("f1", Piece.createWhiteKing(new Position("f1")));

        assertAll(
                () -> assertThat(
                        chessGame.sortByPoint(Color.BLACK, PieceComparator.SORT_BY_POINT_DESC)).containsExactly(
                        Piece.createBlackQueen(new Position("e6")),
                        Piece.createBlackRook(new Position("c8")),
                        Piece.createBlackPawn(new Position("b6")),
                        Piece.createBlackKing(new Position("b8"))
                ),
                () -> assertThat(
                        chessGame.sortByPoint(Color.WHITE, PieceComparator.SORT_BY_POINT_DESC)).containsExactly(
                        Piece.createWhiteRook(new Position("e1")),
                        Piece.createWhitePawn(new Position("f2")),
                        Piece.createWhitePawn(new Position("g2")),
                        Piece.createWhiteKing(new Position("f1"))
                )
        );
    }

    @DisplayName("기물의 점수가 낮은 순으로 정렬한다.")
    @Test
    void sortByPointAsc() {
        chessGame.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(new Position("b6")));
        addPiece("e6", Piece.createBlackQueen(new Position("e6")));
        addPiece("b8", Piece.createBlackKing(new Position("b8")));
        addPiece("c8", Piece.createBlackRook(new Position("c8")));

        addPiece("f2", Piece.createWhitePawn(new Position("f2")));
        addPiece("g2", Piece.createWhitePawn(new Position("g2")));
        addPiece("e1", Piece.createWhiteRook(new Position("e1")));
        addPiece("f1", Piece.createWhiteKing(new Position("f1")));

        assertAll(
                () -> assertThat(
                        chessGame.sortByPoint(Color.BLACK, PieceComparator.SORT_BY_POINT_ASC)).containsExactly(
                        Piece.createBlackKing(new Position("b8")),
                        Piece.createBlackPawn(new Position("b6")),
                        Piece.createBlackRook(new Position("c8")),
                        Piece.createBlackQueen(new Position("e6"))
                ),
                () -> assertThat(
                        chessGame.sortByPoint(Color.WHITE, PieceComparator.SORT_BY_POINT_ASC)).containsExactly(
                        Piece.createWhiteKing(new Position("f1")),
                        Piece.createWhitePawn(new Position("f2")),
                        Piece.createWhitePawn(new Position("g2")),
                        Piece.createWhiteRook(new Position("e1"))
                )
        );
    }

    @DisplayName("기물 이동 관련 테스트")
    @Nested
    class MovePiece {
        @DisplayName("흰색 폰을 이동시킨다.")
        @Test
        public void moveWhitePawn() {
            chessGame.initialize();
            String sourcePosition = "b2";
            String targetPosition = "b3";

            chessGame.move(sourcePosition, targetPosition);

            assertAll(
                    () -> assertEquals(
                            Piece.createBlank(new Position(sourcePosition)), chessGame.findPiece(sourcePosition)
                    ),
                    () -> assertEquals(
                            Piece.createWhitePawn(new Position(targetPosition)), chessGame.findPiece(targetPosition)
                    )
            );
        }
    }

    private void setBlackPawn(final String position) {
        Piece leftDiagonalBlackPiece = Piece.createBlackPawn(new Position(position));
        chessGame.move(position, leftDiagonalBlackPiece);
    }

    private void setWhitePawn(final String position) {
        Piece leftDiagonalBlackPiece = Piece.createBlackPawn(new Position(position));
        chessGame.move(position, leftDiagonalBlackPiece);
    }

    private void addPiece(String position, Piece piece) {
        chessGame.addPiece(position, piece);
    }
}
