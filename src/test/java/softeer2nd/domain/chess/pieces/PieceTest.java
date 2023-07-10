package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece.Color;

@DisplayName("기물 관련 기능")
public class PieceTest {
    private Piece white;
    private Piece black;
    private Position a1;
    private Position a3;

    @BeforeEach
    void setUp() {
        a1 = new Position("a1");
        a3 = new Position("a3");
        this.white = Piece.createWhitePawn(a1);
        this.black = Piece.createBlackPawn(a3);
    }

    @DisplayName("생성자로 전달된 색상인 white 또는 black 으로 기물이 생성되어야 한다")
    @Test
    public void create() {
        verifyPieceBy(white, Color.WHITE);
        verifyPieceBy(black, Color.BLACK);
    }

    @DisplayName("기물의 출력 문자열을 반환한다.")
    @Test
    void getRepresentation() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("p"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("P")
        );
    }

    @DisplayName("기물 정적 팩토리 메서드 관련 기능")
    @Nested
    class createFactoryMethod {
        @DisplayName("흰색 폰을 생성한다.")
        @Test
        void createWhitePawn() {
            assertThat(Piece.createWhitePawn(a1)).isEqualTo(new Pawn(Color.WHITE, a1, Direction.whitePawnDirection()));
        }

        @DisplayName("검은색 폰을 생성한다.")
        @Test
        void createBlackPawn() {
            assertThat(Piece.createBlackPawn(a1)).isEqualTo(new Pawn(Color.BLACK, a1, Direction.blackPawnDirection()));
        }

        @DisplayName("흰색 나이트을 생성한다.")
        @Test
        void createWhiteKnight() {
            assertThat(Piece.createWhiteKnight(a1))
                    .isEqualTo(new Knight(Color.WHITE, a1, Direction.knightDirection()));
        }

        @DisplayName("검은색 나이트을 생성한다.")
        @Test
        void createBlackKnight() {
            assertThat(Piece.createBlackKnight(a1))
                    .isEqualTo(new Knight(Color.BLACK, a1, Direction.knightDirection()));
        }

        @DisplayName("흰색 룩을 생성한다.")
        @Test
        void createWhiteRook() {
            assertThat(Piece.createWhiteRook(a1))
                    .isEqualTo(new Rook(Color.WHITE, a1, Direction.linearDirection()));
        }

        @DisplayName("검은색 룩을 생성한다.")
        @Test
        void createBlackRook() {
            assertThat(Piece.createBlackRook(a1))
                    .isEqualTo(new Rook(Color.BLACK, a1, Direction.linearDirection()));
        }

        @DisplayName("흰색 비숍을 생성한다.")
        @Test
        void createWhiteBishop() {
            assertThat(Piece.createWhiteBishop(a1))
                    .isEqualTo(new Bishop(Color.WHITE, a1, Direction.diagonalDirection()));
        }

        @DisplayName("검은색 비숍을 생성한다.")
        @Test
        void createBlackBishop() {
            assertThat(Piece.createBlackBishop(a1))
                    .isEqualTo(new Bishop(Color.BLACK, a1, Direction.diagonalDirection()));
        }

        @DisplayName("흰색 퀸을 생성한다.")
        @Test
        void createWhiteQueen() {
            assertThat(Piece.createWhiteQueen(a1))
                    .isEqualTo(new Queen(Color.WHITE, a1, Direction.everyDirection()));
        }

        @DisplayName("검은색 퀸을 생성한다.")
        @Test
        void createBlackQueen() {
            assertThat(Piece.createBlackQueen(a1))
                    .isEqualTo(new Queen(Color.BLACK, a1, Direction.everyDirection()));
        }

        @DisplayName("흰색 킹을 생성한다.")
        @Test
        void createWhiteKing() {
            assertThat(Piece.createWhiteKing(a1))
                    .isEqualTo(new King(Color.WHITE, a1, Direction.everyDirection()));
        }

        @DisplayName("검은색 킹을 생성한다.")
        @Test
        void createBlackKing() {
            assertThat(Piece.createBlackKing(a1))
                    .isEqualTo(new King(Color.BLACK, a1, Direction.everyDirection()));
        }

        @DisplayName("빈칸을 생성한다.")
        @Test
        void createNoPiece() {
            assertThat(Piece.createBlank(a1))
                    .isEqualTo(new Blank(Color.NOCOLOR, a1, Direction.blankDirection()));
        }
    }

    @DisplayName("백색 기물이면 true를 반환한다.")
    @Test
    void isWhite() {
        Assertions.assertThat(Piece.createWhitePawn(a1).isWhite()).isTrue();
    }

    @DisplayName("흑색 기물이면 true를 반환한다.")
    @Test
    void isBlack() {
        Assertions.assertThat(Piece.createBlackPawn(a1).isBlack()).isTrue();
    }

    @DisplayName("기물 이름의 값을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("p"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("P")
        );
    }

    @DisplayName("기물이 NO PIECE이면 true를 반환한다")
    @Test
    void isNoPiece() {
        assertAll(
                () -> assertThat(Piece.createBlank(a1).isNoPiece()).isTrue(),
                () -> assertThat(Piece.createBlackPawn(a1).isNoPiece()).isFalse()
        );
    }

    @DisplayName("기물을 팩토리 메서드로 생성한다.")
    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(a1), Piece.createBlackPawn(a1));
        verifyPiece(Piece.createWhiteKnight(a1), Piece.createBlackKnight(a1));
        verifyPiece(Piece.createWhiteRook(a1), Piece.createBlackRook(a1));
        verifyPiece(Piece.createWhiteBishop(a1), Piece.createBlackBishop(a1));
        verifyPiece(Piece.createWhiteQueen(a1), Piece.createBlackQueen(a1));
        verifyPiece(Piece.createWhiteKing(a1), Piece.createBlackKing(a1));

        Piece blank = Piece.createBlank(a1);
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece) {
        assertTrue(whitePiece.isWhite());

        assertTrue(blackPiece.isBlack());
    }

    private void verifyPieceBy(
            final Piece piece,
            final Color color
    ) {
        assertThat(piece.getColor()).isEqualTo(color);
    }
}
