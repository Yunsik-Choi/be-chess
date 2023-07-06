package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static softeer2nd.domain.chess.pieces.Piece.Type.BISHOP;
import static softeer2nd.domain.chess.pieces.Piece.Type.KING;
import static softeer2nd.domain.chess.pieces.Piece.Type.KNIGHT;
import static softeer2nd.domain.chess.pieces.Piece.Type.NO_PIECE;
import static softeer2nd.domain.chess.pieces.Piece.Type.PAWN;
import static softeer2nd.domain.chess.pieces.Piece.Type.QUEEN;
import static softeer2nd.domain.chess.pieces.Piece.Type.ROOK;

import java.lang.reflect.Constructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;

@DisplayName("기물 관련 기능")
public class PieceTest {
    private Piece white;
    private Piece black;

    @BeforeEach
    void setUp() {
        this.white = Piece.createWhitePawn();
        this.black = Piece.createBlackPawn();
    }

    @DisplayName("생성자로 전달된 색상인 white 또는 black 으로 기물이 생성되어야 한다")
    @Test
    public void create() {
        verifyPieceBy(white, Color.WHITE, PAWN);
        verifyPieceBy(black, Color.BLACK, PAWN);
    }

    @DisplayName("기물의 출력 문자열을 반환한다.")
    @Test
    void getRepresentation() {
        assertAll(
                () -> assertThat(white.getType()).isEqualTo(PAWN),
                () -> assertThat(black.getType()).isEqualTo(PAWN)
        );
    }

    @DisplayName("기물 정적 팩토리 메서드 관련 기능")
    @Nested
    class createFactoryMethod {
        private Piece createPieceBy(
                final Color color,
                final Type type
        ) throws Exception {
            Constructor<Piece> declaredConstructor = ReflectionUtils.getDeclaredConstructor(Piece.class);
            declaredConstructor.setAccessible(true);
            Piece piece = declaredConstructor.newInstance(color, type);
            declaredConstructor.setAccessible(false);
            return piece;
        }

        @DisplayName("흰색 폰을 생성한다.")
        @Test
        void createWhitePawn() throws Exception {
            assertThat(Piece.createWhitePawn()).isEqualTo(createPieceBy(Color.WHITE, PAWN));
        }

        @DisplayName("검은색 폰을 생성한다.")
        @Test
        void createBlackPawn() throws Exception {
            assertThat(Piece.createBlackPawn()).isEqualTo(createPieceBy(Color.BLACK, PAWN));
        }

        @DisplayName("흰색 나이트을 생성한다.")
        @Test
        void createWhiteKnight() throws Exception {
            assertThat(Piece.createWhiteKnight())
                    .isEqualTo(createPieceBy(Color.WHITE, KNIGHT));
        }

        @DisplayName("검은색 나이트을 생성한다.")
        @Test
        void createBlackKnight() throws Exception {
            assertThat(Piece.createBlackKnight())
                    .isEqualTo(createPieceBy(Color.BLACK, KNIGHT));
        }

        @DisplayName("흰색 룩을 생성한다.")
        @Test
        void createWhiteRook() throws Exception {
            assertThat(Piece.createWhiteRook())
                    .isEqualTo(createPieceBy(Color.WHITE, ROOK));
        }

        @DisplayName("검은색 룩을 생성한다.")
        @Test
        void createBlackRook() throws Exception {
            assertThat(Piece.createBlackRook())
                    .isEqualTo(createPieceBy(Color.BLACK, ROOK));
        }

        @DisplayName("흰색 비숍을 생성한다.")
        @Test
        void createWhiteBishop() throws Exception {
            assertThat(Piece.createWhiteBishop())
                    .isEqualTo(createPieceBy(Color.WHITE, BISHOP));
        }

        @DisplayName("검은색 비숍을 생성한다.")
        @Test
        void createBlackBishop() throws Exception {
            assertThat(Piece.createBlackBishop())
                    .isEqualTo(createPieceBy(Color.BLACK, BISHOP));
        }

        @DisplayName("흰색 퀸을 생성한다.")
        @Test
        void createWhiteQueen() throws Exception {
            assertThat(Piece.createWhiteQueen())
                    .isEqualTo(createPieceBy(Color.WHITE, QUEEN));
        }

        @DisplayName("검은색 퀸을 생성한다.")
        @Test
        void createBlackQueen() throws Exception {
            assertThat(Piece.createBlackQueen())
                    .isEqualTo(createPieceBy(Color.BLACK, QUEEN));
        }

        @DisplayName("흰색 킹을 생성한다.")
        @Test
        void createWhiteKing() throws Exception {
            assertThat(Piece.createWhiteKing())
                    .isEqualTo(createPieceBy(Color.WHITE, KING));
        }

        @DisplayName("검은색 킹을 생성한다.")
        @Test
        void createBlackKing() throws Exception {
            assertThat(Piece.createBlackKing())
                    .isEqualTo(createPieceBy(Color.BLACK, KING));
        }

        @DisplayName("빈칸을 생성한다.")
        @Test
        void createNoPiece() throws Exception {
            assertThat(Piece.createNoPiece())
                    .isEqualTo(createPieceBy(Color.NOCOLOR, NO_PIECE));
        }
    }

    @DisplayName("백색 기물이면 true를 반환한다.")
    @Test
    void isWhite() {
        Assertions.assertThat(Piece.createWhitePawn().isWhite()).isTrue();
    }

    @DisplayName("흑색 기물이면 true를 반환한다.")
    @Test
    void isBlack() {
        Assertions.assertThat(Piece.createBlackPawn().isBlack()).isTrue();
    }

    @DisplayName("기물 이름의 값을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(PAWN.getWhiteRepresentation()).isEqualTo("p"),
                () -> assertThat(PAWN.getBlackRepresentation()).isEqualTo("P")
        );
    }

    private void verifyPieceBy(
            final Piece piece,
            final Color color,
            final Type type
    ) {
        assertAll(
                () -> assertThat(piece.getColor()).isEqualTo(color),
                () -> assertThat(piece.getType()).isEqualTo(type)
        );
    }
}
