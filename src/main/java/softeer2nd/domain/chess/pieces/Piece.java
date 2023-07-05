package softeer2nd.domain.chess.pieces;

import java.util.Objects;

public class Piece {
    private final PieceColor color;
    private final PieceRepresentation representation;

    private Piece(final PieceColor color, final PieceRepresentation representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(PieceColor.WHITE, PieceRepresentation.WHITE_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(PieceColor.BLACK, PieceRepresentation.BLACK_REPRESENTATION);
    }

    public String getColor() {
        return this.color.getValue();
    }

    public String getRepresentation() {
        return this.representation.getValue();
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
        return Objects.equals(color, piece.color) && representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation);
    }
}
