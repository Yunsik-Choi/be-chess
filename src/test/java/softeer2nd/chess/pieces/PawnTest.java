package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Pawn.BLACK_COLOR;
import static softeer2nd.chess.pieces.Pawn.WHITE_COLOR;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("폰 관련 기능")
public class PawnTest {
    @DisplayName("생성자로 전달된 색상인 white 또는 black 으로 폰이 생성되어야 한다")
    @ParameterizedTest(name = "color : {0}")
    @ValueSource(strings = {WHITE_COLOR, BLACK_COLOR})
    public void create(final String color) {
        Pawn pawn = new Pawn(color);

        verifyPawnBy(pawn, color);
    }

    @DisplayName("폰을 기본생성자로 생성한다.")
    @Test
    public void create_기본생성자() {
        Pawn pawn = new Pawn();

        assertEquals(WHITE_COLOR, pawn.getColor());
    }

    private void verifyPawnBy(final Pawn pawn, final String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
