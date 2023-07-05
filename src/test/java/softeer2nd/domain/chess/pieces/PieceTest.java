package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.BLACK_KNIGHT_REPRESENTATION;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.BLACK_REPRESENTATION;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.WHITE_KNIGHT_REPRESENTATION;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.WHITE_REPRESENTATION;

import java.lang.reflect.Constructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

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
        verifyPieceBy(white, PieceColor.WHITE.getValue(), WHITE_REPRESENTATION.getValue());
        verifyPieceBy(black, PieceColor.BLACK.getValue(), BLACK_REPRESENTATION.getValue());
    }

    @DisplayName("기물의 출력 문자열을 반환한다.")
    @Test
    void getRepresentation() {
        assertAll(
                () -> assertThat(white.getRepresentation()).isEqualTo(WHITE_REPRESENTATION.getValue()),
                () -> assertThat(black.getRepresentation()).isEqualTo(BLACK_REPRESENTATION.getValue())
        );
    }

    @DisplayName("기물 정적 팩토리 메서드 관련 기능")
    @Nested
    class createFactoryMethod {
        private Piece createPieceBy(
                final PieceColor color,
                final PieceRepresentation representation
        ) throws Exception {
            Constructor<Piece> declaredConstructor = ReflectionUtils.getDeclaredConstructor(Piece.class);
            declaredConstructor.setAccessible(true);
            Piece piece = declaredConstructor.newInstance(color, representation);
            declaredConstructor.setAccessible(false);
            return piece;
        }

        @DisplayName("흰색 폰을 생성한다.")
        @Test
        void createWhitePawn() throws Exception {
            assertThat(Piece.createWhitePawn()).isEqualTo(createPieceBy(PieceColor.WHITE, WHITE_REPRESENTATION));
        }

        @DisplayName("검은색 폰을 생성한다.")
        @Test
        void createBlackPawn() throws Exception {
            assertThat(Piece.createBlackPawn()).isEqualTo(createPieceBy(PieceColor.BLACK, BLACK_REPRESENTATION));
        }

        @DisplayName("흰색 나이트을 생성한다.")
        @Test
        void createWhiteKnight() throws Exception {
            assertThat(Piece.createWhiteKnight())
                    .isEqualTo(createPieceBy(PieceColor.WHITE, WHITE_KNIGHT_REPRESENTATION));
        }

        @DisplayName("검은색 나이트을 생성한다.")
        @Test
        void createBlackKnight() throws Exception {
            assertThat(Piece.createBlackKnight())
                    .isEqualTo(createPieceBy(PieceColor.BLACK, BLACK_KNIGHT_REPRESENTATION));
        }

        @DisplayName("흰색 룩을 생성한다.")
        @Test
        void createWhiteRook() throws Exception {
            assertThat(Piece.createWhiteRook())
                    .isEqualTo(createPieceBy(PieceColor.WHITE, PieceRepresentation.WHITE_ROOK_REPRESENTATION));
        }

        @DisplayName("검은색 룩을 생성한다.")
        @Test
        void createBlackRook() throws Exception {
            assertThat(Piece.createBlackRook())
                    .isEqualTo(createPieceBy(PieceColor.BLACK, PieceRepresentation.BLACK_ROOK_REPRESENTATION));
        }

        @DisplayName("흰색 비숍을 생성한다.")
        @Test
        void createWhiteBishop() throws Exception {
            assertThat(Piece.createWhiteBishop())
                    .isEqualTo(createPieceBy(PieceColor.WHITE, PieceRepresentation.WHITE_BISHOP_REPRESENTATION));
        }

        @DisplayName("검은색 비숍을 생성한다.")
        @Test
        void createBlackBishop() throws Exception {
            assertThat(Piece.createBlackBishop())
                    .isEqualTo(createPieceBy(PieceColor.BLACK, PieceRepresentation.BLACK_BISHOP_REPRESENTATION));
        }

        @DisplayName("흰색 퀸을 생성한다.")
        @Test
        void createWhiteQueen() throws Exception {
            assertThat(Piece.createWhiteQueen())
                    .isEqualTo(createPieceBy(PieceColor.WHITE, PieceRepresentation.WHITE_QUEEN_REPRESENTATION));
        }

        @DisplayName("검은색 퀸을 생성한다.")
        @Test
        void createBlackQueen() throws Exception {
            assertThat(Piece.createBlackQueen())
                    .isEqualTo(createPieceBy(PieceColor.BLACK, PieceRepresentation.BLACK_QUEEN_REPRESENTATION));
        }

        @DisplayName("흰색 킹을 생성한다.")
        @Test
        void createWhiteKing() throws Exception {
            assertThat(Piece.createWhiteKing())
                    .isEqualTo(createPieceBy(PieceColor.WHITE, PieceRepresentation.WHITE_KING_REPRESENTATION));
        }

        @DisplayName("검은색 킹을 생성한다.")
        @Test
        void createBlackKing() throws Exception {
            assertThat(Piece.createBlackKing())
                    .isEqualTo(createPieceBy(PieceColor.BLACK, PieceRepresentation.BLACK_KING_REPRESENTATION));
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

    private void verifyPieceBy(
            final Piece piece,
            final String color,
            final String representation
    ) {
        assertAll(
                () -> assertThat(piece.getColor()).isEqualTo(color),
                () -> assertThat(piece.getRepresentation()).isEqualTo(representation)
        );
    }
}
