package softeer2nd.domain.chess.pieces;

public class Rook extends Piece {
    private static final String REPRESENTATION = "r";
    private static final double POINT = 5.0;

    protected Rook(final Color color, final Position position) {
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
        return new Rook(this.color, targetPosition);
    }
}
