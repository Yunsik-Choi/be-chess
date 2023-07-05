package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.BLACK_REPRESENTATION;
import static softeer2nd.domain.chess.pieces.PieceRepresentation.WHITE_REPRESENTATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
