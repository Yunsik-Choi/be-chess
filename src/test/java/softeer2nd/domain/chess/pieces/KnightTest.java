package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece.Color;

@DisplayName("나이트 관련 기능")
class KnightTest {
    private Knight white;
    private Knight black;

    @BeforeEach
    void setUp() {
        black = new Knight(Color.BLACK, new Position(1, 1), Direction.knightDirection());
        white = new Knight(Color.WHITE, new Position(2, 2), Direction.knightDirection());
    }

    @DisplayName("나이트을 생성한다.")
    @Test
    void create() {
        assertAll(
                () -> assertThat(new Knight(Color.BLACK, new Position(0, 1), Direction.diagonalDirection()))
                        .isEqualTo(new Knight(Color.BLACK, new Position(0, 1), Direction.diagonalDirection())),
                () -> assertThat(new Knight(Color.BLACK, new Position(2, 2), Direction.diagonalDirection()))
                        .isEqualTo(new Knight(Color.BLACK, new Position(2, 2), Direction.diagonalDirection()))
        );
    }

    @DisplayName("나이트의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("n"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("N")
        );
    }

    @DisplayName("나이트의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertAll(
                () -> assertThat(white.getPoint()).isEqualTo(2.5),
                () -> assertThat(black.getPoint()).isEqualTo(2.5)
        );
    }

    @DisplayName("나이트의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertAll(
                () -> assertThat(white.getDirections()).isEqualTo(Direction.knightDirection()),
                () -> assertThat(black.getDirections()).isEqualTo(Direction.knightDirection())
        );
    }
}
