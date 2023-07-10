package softeer2nd.domain.chess.pieces;

public class King extends Piece {
    private static final String REPRESENTATION = "k";
    private static final double POINT = 0.0;

    protected King(final Color color, final Position position) {
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
        return new King(this.color, targetPosition);
    }
}
