package softeer2nd.domain.chess;

import java.util.Optional;

public enum Color {
    WHITE, BLACK, NOCOLOR;

    public Optional<Color> getEnemy() {
        if (this.equals(WHITE)) {
            return Optional.of(BLACK);
        }
        if (this.equals(BLACK)) {
            return Optional.of(WHITE);
        }
        return Optional.empty();
    }
}
