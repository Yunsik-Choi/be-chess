package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static softeer2nd.domain.chess.ChessGame.WIDTH;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Position;

@DisplayName("체스판 행 관련 기능")
class RankTest {
    private Position a1;

    @BeforeEach
    void setUp() {
        a1 = new Position("a1");
    }

    @DisplayName("행을 생성한다.")
    @Test
    void create() {
        Rank rank = new Rank();

        Assertions.assertAll(
                () -> assertThat(rank.getPieces()).containsOnlyNulls(),
                () -> assertThat(rank.getPieces()).hasSize(8)
        );
    }

    @DisplayName("행에 기물을 추가한다.")
    @ParameterizedTest(name = "index : {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void set(int index) {
        Rank rank = new Rank();
        Piece piece = Piece.createBlackPawn(a1);

        rank.set(index, piece);

        assertThat(rank.getPieces().get(index)).isEqualTo(piece);
    }

    @DisplayName("기물 추가시 행에 사이즈를 벗어나면 예외 처리한다.")
    @Test
    void setIndexOutOfSize() {
        Rank rank = new Rank();
        Piece piece = Piece.createWhitePawn(a1);

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> rank.set(-1, piece))
                        .isInstanceOf(IndexOutOfBoundsException.class),
                () -> assertThatThrownBy(() -> rank.set(WIDTH, piece))
                        .isInstanceOf(IndexOutOfBoundsException.class)
        );
    }

    @DisplayName("행에 번호로 추가된 기물을 가져온다.")
    @ParameterizedTest(name = "index : {0}")
    @ValueSource(strings = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"})
    void getPiece(String position) {
        Rank rank = new Rank();
        Piece piece = Piece.createBlackPawn(new Position(position));

        rank.set(new Position(position).getX(), piece);

        assertThat(rank.getPiece(new Position(position).getX())).isEqualTo(Piece.createBlackPawn(new Position(position)));
    }
}
