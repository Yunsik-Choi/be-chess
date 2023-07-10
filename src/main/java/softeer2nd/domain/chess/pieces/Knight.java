package softeer2nd.domain.chess.pieces;

public class Knight extends Piece {
    private static final String REPRESENTATION = "n";
    private static final double POINT = 2.5;

    protected Knight(final Color color, final Position position) {
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
        return new Knight(this.color, targetPosition);
    }
}
