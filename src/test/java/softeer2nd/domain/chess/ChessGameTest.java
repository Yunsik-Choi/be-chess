package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.common.util.StringUtils.appendNewLine;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.domain.chess.pieces.Piece;
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
        @DisplayName("폰 이동 관련 테스트")
        @Nested
        class Pawn {
            @DisplayName("흰색 폰을 이동시킨다.")
            @ValueSource(strings = {"a3", "b3", "c3"})
            @ParameterizedTest(name = "position : {0}")
            public void moveWhitePawn(String targetPosition) {
                chessGame.initialize();
                setBlackPawn("a3");
                setBlackPawn("c3");
                String sourcePosition = "b2";

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

            @DisplayName("검은색 폰을 이동시킨다.")
            @ValueSource(strings = {"a6", "b6", "c6"})
            @ParameterizedTest(name = "position : {0}")
            public void moveBlackPawn(String targetPosition) {
                chessGame.initialize();
                setWhitePawn("a6");
                setWhitePawn("c6");
                String sourcePosition = "b7";

                chessGame.move(sourcePosition, targetPosition);

                assertAll(
                        () -> assertEquals(
                                Piece.createBlank(new Position(sourcePosition)), chessGame.findPiece(sourcePosition)
                        ),
                        () -> assertEquals(
                                Piece.createBlackPawn(new Position(targetPosition)), chessGame.findPiece(targetPosition)
                        )
                );
            }

            @DisplayName("전방에 다른 기물이 없거나 대각선에 다른 색상 기물이 없다면 폰은 대각선으로 이동할 수 없다.")
            @ValueSource(strings = {"a3", "b3", "c3"})
            @ParameterizedTest(name = "position : {0}")
            public void canNotMovePawn(String targetPosition) {
                chessGame.initialize();
                setWhitePawn("b3");
                String sourcePosition = "b2";

                Assertions.assertThatThrownBy(() -> chessGame.move(sourcePosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("비숍 이동 관련 테스트")
        @Nested
        class Bishop {
            @DisplayName("비숍을 이동시킨다.")
            @ValueSource(strings = {"a7", "b6", "c5", "a1", "b2", "c3", "e3", "f2", "g1", "e5", "f6", "g7", "h8"})
            @ParameterizedTest(name = "position : {0}")
            void move(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackBishop(new Position(originPosition)));

                chessGame.move(originPosition, targetPosition);

                assertEquals(
                        Piece.createBlackBishop(new Position(targetPosition)), chessGame.findPiece(targetPosition)
                );
            }

            @DisplayName("비숍은 대각선으로 아군을 마주치기 전까지 이동할 수 있다.")
            @ValueSource(strings = {"a7", "b2", "f2", "g7"})
            @ParameterizedTest(name = "position : {0}")
            void moveAlly(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackBishop(new Position(originPosition)));
                setBlackPawn("a7");
                setBlackPawn("b2");
                setBlackPawn("f2");
                setBlackPawn("g7");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("비숍은 대각선으로 적군의 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"a7", "b2", "f2", "g7"})
            @ParameterizedTest(name = "position : {0}")
            void moveEnemy(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackBishop(new Position(originPosition)));
                setBlackPawn("b6");
                setBlackPawn("c3");
                setBlackPawn("e3");
                setBlackPawn("f6");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("비숍은 선형 방향으로 이동할 수 없다.")
            @ValueSource(strings = {"d5", "c4", "d3", "e4"})
            @ParameterizedTest(name = "position : {0}")
            void moveLinear(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackBishop(new Position(originPosition)));

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("공백 이동 관련 테스트")
        @Nested
        class Blank {
            @DisplayName("공백은 이동할 수 없습니다.")
            @ValueSource(strings = {"c4", "c3", "c2", "d2", "e2", "e3", "e4", "d4"})
            @ParameterizedTest(name = "position : {0}")
            void move(String targetPosition) {
                chessGame.initialize();
                String originPosition = "d3";

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }


        @DisplayName("룩 이동 관련 테스트")
        @Nested
        class Rook {
            @DisplayName("룩을 이동시킨다.")
            @ValueSource(strings = {"d8", "d7", "d6", "d5", "d3", "d2", "d1", "a4", "b4", "c4", "e4", "f4", "g4", "h4"})
            @ParameterizedTest(name = "position : {0}")
            void move(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackRook(new Position(originPosition)));

                chessGame.move(originPosition, targetPosition);

                assertEquals(
                        Piece.createBlackRook(new Position(targetPosition)), chessGame.findPiece(targetPosition)
                );
            }

            @DisplayName("룩은 대각선으로 이동할 수 없습니다.")
            @ValueSource(strings = {"a7", "b6", "c5", "a1", "b2", "c3", "e3", "f2", "g1", "e5", "f6", "g7", "h8"})
            @ParameterizedTest(name = "position : {0}")
            void moveDiagonal(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackRook(new Position(originPosition)));

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("룩은 선형으로 적군의 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"d7", "d1", "b4", "f4"})
            @ParameterizedTest(name = "position : {0}")
            void moveEnemy(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackRook(new Position(originPosition)));
                setWhitePawn("d6");
                setWhitePawn("d2");
                setWhitePawn("c4");
                setWhitePawn("e4");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("룩은 선형으로 아군의 전 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"d7", "d1", "b4", "e4"})
            @ParameterizedTest(name = "position : {0}")
            void moveAlly(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackRook(new Position(originPosition)));
                setBlackPawn("d7");
                setBlackPawn("d1");
                setBlackPawn("b4");
                setBlackPawn("e4");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("퀸 이동 관련 테스트")
        @Nested
        class Queen {
            @DisplayName("퀸을 이동시킨다.")
            @ValueSource(strings = {"d8", "d7", "d6", "d5", "d3", "d2", "d1", "a4", "b4", "c4", "e4", "f4", "g4", "h4",
                    "a7", "b6", "c5", "a1", "b2", "c3", "e3", "f2", "g1", "e5", "f6", "g7", "h8"})
            @ParameterizedTest(name = "position : {0}")
            void move(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createWhiteQueen(new Position(originPosition)));

                chessGame.move(originPosition, targetPosition);

                assertEquals(Piece.createWhiteQueen(new Position(targetPosition)), chessGame.findPiece(targetPosition));
            }

            @DisplayName("퀸은 아군의 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"c5", "d5", "e5", "c4", "c3", "d3", "e3", "e4"})
            @ParameterizedTest(name = "position : {0}")
            void moveAlly(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createWhiteQueen(new Position(originPosition)));
                setWhitePawn("c5");
                setWhitePawn("d5");
                setWhitePawn("e5");
                setWhitePawn("c4");
                setWhitePawn("c3");
                setWhitePawn("d3");
                setWhitePawn("e3");
                setWhitePawn("e4");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("퀸은 적군의 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"b6", "d6", "f6", "f4", "f2", "d2", "b2", "b4"})
            @ParameterizedTest(name = "position : {0}")
            void moveEnemy(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createWhiteQueen(new Position(originPosition)));
                setWhitePawn("c5");
                setWhitePawn("d5");
                setWhitePawn("e5");
                setWhitePawn("c4");
                setWhitePawn("c3");
                setWhitePawn("d3");
                setWhitePawn("e3");
                setWhitePawn("e4");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("킹 이동 관련 테스트")
        @Nested
        class King {
            @DisplayName("킹을 이동시킨다.")
            @ValueSource(strings = {"c5", "c4", "c3", "d3", "d5", "e5", "e4", "e3"})
            @ParameterizedTest(name = "position : {0}")
            void move(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackKing(new Position(originPosition)));

                chessGame.move(originPosition, targetPosition);

                assertEquals(Piece.createBlackKing(new Position(targetPosition)), chessGame.findPiece(targetPosition));
            }

            @DisplayName("킹은 아군의 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"c5", "d5", "e5", "c4", "c3", "d3", "e3", "e4"})
            @ParameterizedTest(name = "position : {0}")
            void moveAlly(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createWhiteKing(new Position(originPosition)));
                setWhitePawn("c5");
                setWhitePawn("d5");
                setWhitePawn("e5");
                setWhitePawn("c4");
                setWhitePawn("c3");
                setWhitePawn("d3");
                setWhitePawn("e3");
                setWhitePawn("e4");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("킹은 적군의 위치까지 이동할 수 있다.")
            @ValueSource(strings = {"c5", "d5", "e5", "c4", "c3", "d3", "e3", "e4"})
            @ParameterizedTest(name = "position : {0}")
            void moveEnemy(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackKing(new Position(originPosition)));
                setWhitePawn("c5");
                setWhitePawn("d5");
                setWhitePawn("e5");
                setWhitePawn("c4");
                setWhitePawn("c3");
                setWhitePawn("d3");
                setWhitePawn("e3");
                setWhitePawn("e4");

                Assertions.assertThatCode(() -> chessGame.move(originPosition, targetPosition))
                        .doesNotThrowAnyException();
            }
        }

        @DisplayName("나이트 이동 관련 테스트")
        @Nested
        class Knight {
            @DisplayName("나이트를 이동시킨다.")
            @ValueSource(strings = {"c6", "e6", "f5", "f3", "e2", "c2", "b3", "b5"})
            @ParameterizedTest(name = "position : {0}")
            void move(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackKnight(new Position(originPosition)));
                setBlackPawn("c5");
                setBlackPawn("d5");
                setBlackPawn("e5");
                setBlackPawn("c4");
                setBlackPawn("c3");
                setBlackPawn("d3");
                setBlackPawn("e3");
                setBlackPawn("e4");

                chessGame.move(originPosition, targetPosition);

                assertEquals(Piece.createBlackKnight(new Position(targetPosition)), chessGame.findPiece(targetPosition));
            }

            @DisplayName("나이트가 이동하려는 곳에 아군이 있으면 예외처리한다.")
            @ValueSource(strings = {"c6", "e6", "f5", "f3", "e2", "c2", "b3", "b5"})
            @ParameterizedTest(name = "position : {0}")
            void moveAlly(String targetPosition) {
                chessGame.initializeEmpty();
                String originPosition = "d4";
                chessGame.addPiece(originPosition, Piece.createBlackKnight(new Position(originPosition)));
                setBlackPawn("c6");
                setBlackPawn("e6");
                setBlackPawn("f5");
                setBlackPawn("f3");
                setBlackPawn("e2");
                setBlackPawn("c2");
                setBlackPawn("b3");
                setBlackPawn("b5");

                Assertions.assertThatThrownBy(() -> chessGame.move(originPosition, targetPosition))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    private void setBlackPawn(final String position) {
        Piece leftDiagonalBlackPiece = Piece.createBlackPawn(new Position(position));
        chessGame.addPiece(position, leftDiagonalBlackPiece);
    }

    private void setWhitePawn(final String position) {
        Piece leftDiagonalBlackPiece = Piece.createWhitePawn(new Position(position));
        chessGame.addPiece(position, leftDiagonalBlackPiece);
    }

    private void addPiece(String position, Piece piece) {
        chessGame.addPiece(position, piece);
    }
}
