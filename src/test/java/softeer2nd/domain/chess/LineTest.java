package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.domain.chess.pieces.Piece;

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

    @DisplayName("행에 기물을 추가한다.")
    @ParameterizedTest(name = "index : {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void set(int index) {
        Line line = new Line();
        Piece piece = Piece.createBlackPawn();

        line.set(index, piece);

        assertThat(line.getPieces().get(index)).isEqualTo(piece);
    }

    @DisplayName("기물 추가시 행에 사이즈를 벗어나면 예외 처리한다.")
    @Test
    void setIndexOutOfSize() {
        Line line = new Line();
        Piece piece = Piece.createWhitePawn();

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> line.set(-1, piece))
                        .isInstanceOf(IndexOutOfBoundsException.class),
                () -> assertThatThrownBy(() -> line.set(Line.WIDTH, piece))
                        .isInstanceOf(IndexOutOfBoundsException.class)
        );
    }
}
