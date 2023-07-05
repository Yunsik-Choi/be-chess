package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("체스판 행 관련 기능")
class LineTest {
    @DisplayName("행을 생성한다.")
    @Test
    void create() {
        Line line = new Line();

        Assertions.assertAll(
                () -> assertThat(line.getPieces()).containsOnlyNulls(),
                () -> assertThat(line.getPieces()).hasSize(8)
        );
    }
}
