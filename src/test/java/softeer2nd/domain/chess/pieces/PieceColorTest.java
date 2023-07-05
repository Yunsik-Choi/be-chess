package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("기물 색상 관련 기능")
class PieceColorTest {

    @DisplayName("기물의 색상 값을 가져온다.")
    @Test
    void getColor() {
        assertAll(
                () -> assertThat(PieceColor.WHITE.getValue()).isEqualTo("white"),
                () -> assertThat(PieceColor.BLACK.getValue()).isEqualTo("black")
        );
    }
}
