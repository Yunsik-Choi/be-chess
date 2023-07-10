package softeer2nd.domain.chess.pieces;

public class Bishop extends Piece {
    private static final String REPRESENTATION = "b";
    private static final double POINT = 3.0;

    protected Bishop(final Color color, final Position position) {
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
        return new Bishop(this.color, targetPosition);
    }
}
