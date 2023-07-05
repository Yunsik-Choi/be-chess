package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("기물 이름 관련 기능")
class PieceRepresentationTest {

    @DisplayName("기물 이름의 값을 가져온다.")
    @Test
    void getValue() {
        assertAll(
                () -> assertThat(PieceRepresentation.WHITE_REPRESENTATION.getValue()).isEqualTo("p"),
                () -> assertThat(PieceRepresentation.BLACK_REPRESENTATION.getValue()).isEqualTo("P")
        );
    }
}
