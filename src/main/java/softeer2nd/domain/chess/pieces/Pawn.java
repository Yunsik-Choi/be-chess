package softeer2nd.domain.chess.pieces;

import java.util.Objects;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final String WHITE_REPRESENTATION = "p";
    public static final String BLACK_REPRESENTATION = "P";

    private final String color;
    private final String representation;

    private Point point;

    public Pawn(final Point point) {
        this(WHITE_COLOR, WHITE_REPRESENTATION, point);
    }

    public Pawn(final String color, final String representation, final Point point) {
        this.color = color;
        this.representation = representation;
        this.point = point;
    }

    public String getColor() {
        return this.color;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public Point getPoint() {
        return this.point;
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
