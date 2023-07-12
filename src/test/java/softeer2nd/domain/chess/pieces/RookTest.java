package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece.Color;

@DisplayName("룩 관련 기능")
class RookTest {
    private Rook white;
    private Rook black;

    @BeforeEach
    void setUp() {
        black = new Rook(Color.BLACK, new Position(1, 1), Direction.linearDirection());
        white = new Rook(Color.WHITE, new Position(2, 2), Direction.linearDirection());
    }

    @DisplayName("룩을 생성한다.")
    @Test
    void create() {
        assertAll(
                () -> assertThat(new Rook(Color.BLACK, new Position(0, 1), Direction.linearDirection()))
                        .isEqualTo(new Rook(Color.BLACK, new Position(0, 1), Direction.linearDirection())),
                () -> assertThat(new Rook(Color.BLACK, new Position(2, 2), Direction.linearDirection()))
                        .isEqualTo(new Rook(Color.BLACK, new Position(2, 2), Direction.linearDirection()))
        );
    }

    @DisplayName("룩의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("r"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("R")
        );
    }

    @DisplayName("룩의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertAll(
                () -> assertThat(white.getPoint()).isEqualTo(5.0),
                () -> assertThat(black.getPoint()).isEqualTo(5.0)
        );
    }

    @DisplayName("룩의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertAll(
                () -> assertThat(white.getDirections()).isEqualTo(Direction.linearDirection()),
                () -> assertThat(black.getDirections()).isEqualTo(Direction.linearDirection())
        );
    }
}
