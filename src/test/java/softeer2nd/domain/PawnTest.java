package softeer2nd.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("폰 관련 기능")
public class PawnTest {
    private static final String PAWN_COLOR_WHITE = "white";
    private static final String PAWN_COLOR_BLACK = "black";

    @DisplayName("생성자로 전달된 색상인 white 또는 black 으로 폰이 생성되어야 한다")
    @ParameterizedTest(name = "color : {0}")
    @ValueSource(strings = {PAWN_COLOR_WHITE, PAWN_COLOR_BLACK})
    public void create(final String color) {
        Pawn pawn = new Pawn(color);

        verifyPawnBy(pawn, color);
    }

    private void verifyPawnBy(final Pawn pawn, final String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
