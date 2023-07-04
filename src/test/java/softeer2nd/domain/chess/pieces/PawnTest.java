package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static softeer2nd.domain.chess.pieces.Pawn.BLACK_COLOR;
import static softeer2nd.domain.chess.pieces.Pawn.BLACK_REPRESENTATION;
import static softeer2nd.domain.chess.pieces.Pawn.WHITE_COLOR;
import static softeer2nd.domain.chess.pieces.Pawn.WHITE_REPRESENTATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("폰 관련 기능")
public class PawnTest {
    private Pawn white;
    private Pawn black;

    @BeforeEach
    void setUp() {
        this.white = new Pawn(WHITE_COLOR, WHITE_REPRESENTATION, new Point(0, 0));
        this.black = new Pawn(BLACK_COLOR, BLACK_REPRESENTATION, new Point(0, 1));
    }

    @DisplayName("생성자로 전달된 색상인 white 또는 black 으로 폰이 생성되어야 한다")
    @Test
    public void create() {
        verifyPawnBy(white, WHITE_COLOR, WHITE_REPRESENTATION, 0, 0);
        verifyPawnBy(black, BLACK_COLOR, BLACK_REPRESENTATION, 0, 1);
    }

    @DisplayName("폰을 기본생성자로 생성한다.")
    @Test
    public void create_기본생성자() {
        Pawn pawn = new Pawn(new Point(0, 0));

        verifyPawnBy(pawn, WHITE_COLOR, WHITE_REPRESENTATION, 0, 0);
    }

    @DisplayName("폰의 출력 문자열을 반환한다.")
    @Test
    void getRepresentation() {
        assertAll(
                () -> assertThat(white.getRepresentation()).isEqualTo(WHITE_REPRESENTATION),
                () -> assertThat(black.getRepresentation()).isEqualTo(BLACK_REPRESENTATION)
        );
    }

    private void verifyPawnBy(
            final Pawn pawn,
            final String color,
            final String representation,
            final int x,
            final int y
    ) {
        assertAll(
                () -> assertThat(pawn.getColor()).isEqualTo(color),
                () -> assertThat(pawn.getRepresentation()).isEqualTo(representation),
                () -> assertThat(pawn.getPoint().getX()).isEqualTo(x),
                () -> assertThat(pawn.getPoint().getY()).isEqualTo(y)
        );
    }
}
