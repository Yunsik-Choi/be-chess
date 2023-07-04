package softeer2nd.chess.pieces;

import java.util.Objects;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    private final String color;

    public Pawn() {
        this("white");
    }

    public Pawn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pawn pawn = (Pawn) o;
        return Objects.equals(color, pawn.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
