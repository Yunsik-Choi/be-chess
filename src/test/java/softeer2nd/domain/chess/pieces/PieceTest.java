package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static softeer2nd.domain.chess.pieces.Piece.BLACK_COLOR;
import static softeer2nd.domain.chess.pieces.Piece.WHITE_COLOR;
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
        this.white = new Piece(WHITE_COLOR, WHITE_REPRESENTATION, new Point(0, 0));
        this.black = new Piece(BLACK_COLOR, BLACK_REPRESENTATION, new Point(0, 1));
    }

    @DisplayName("생성자로 전달된 색상인 white 또는 black 으로 기물이 생성되어야 한다")
    @Test
    public void create() {
        verifyPieceBy(white, WHITE_COLOR, WHITE_REPRESENTATION.getValue(), 0, 0);
        verifyPieceBy(black, BLACK_COLOR, BLACK_REPRESENTATION.getValue(), 0, 1);
    }

    @DisplayName("기물을 기본생성자로 생성한다.")
    @Test
    public void create_기본생성자() {
        Piece piece = new Piece(PieceRepresentation.WHITE_REPRESENTATION, new Point(0, 0));

        verifyPieceBy(piece, WHITE_COLOR, WHITE_REPRESENTATION.getValue(), 0, 0);
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
            final String representation,
            final int x,
            final int y
    ) {
        assertAll(
                () -> assertThat(piece.getColor()).isEqualTo(color),
                () -> assertThat(piece.getRepresentation()).isEqualTo(representation),
                () -> assertThat(piece.getPoint().getX()).isEqualTo(x),
                () -> assertThat(piece.getPoint().getY()).isEqualTo(y)
        );
    }
}
