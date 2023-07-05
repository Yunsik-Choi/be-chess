package softeer2nd.domain.chess.pieces;

import java.util.Objects;

public class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    private final String color;
    private final PieceRepresentation representation;
    private final Point point;

    public Piece(final String color, final PieceRepresentation representation, final Point point) {
        this.color = color;
        this.representation = representation;
        this.point = point;
    }

    public String getColor() {
        return this.color;
    }

    public String getRepresentation() {
        return this.representation.getValue();
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
        final Piece piece = (Piece) o;
        return Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
