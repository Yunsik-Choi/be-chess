package softeer2nd.domain.chess.pieces;

public class Pawn extends Piece {
    private static final String REPRESENTATION = "p";
    private static final double POINT = 1.0;

    protected Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public String getRepresentationPerPiece() {
        if (isBlack()) {
            return REPRESENTATION.toUpperCase();
        }
        return REPRESENTATION;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public Piece move(final Position targetPosition) {
        return new Pawn(this.color, targetPosition);
    }
}
