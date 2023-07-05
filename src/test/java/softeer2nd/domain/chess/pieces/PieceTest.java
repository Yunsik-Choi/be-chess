package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.BLACK_REPRESENTATION;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.WHITE_REPRESENTATION;

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
        this.white = new Piece(PieceColor.WHITE, WHITE_REPRESENTATION);
        this.black = new Piece(PieceColor.BLACK, BLACK_REPRESENTATION);
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
            return ReflectionUtils.getDeclaredConstructor(Piece.class).newInstance(color, representation);
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
