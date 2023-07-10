package softeer2nd.domain.chess.pieces;

public class Rook extends Piece {
    private static final String REPRESENTATION = "r";
    private static final double POINT = 5.0;

    protected Rook(final Color color, final Type type, final Position position) {
        super(color, type, position);
    }

    @Override
    public String getWhiteRepresentation() {
        return REPRESENTATION;
    }

    @Override
    public String getBlackRepresentation() {
        return REPRESENTATION.toUpperCase();
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public Piece move(final Position targetPosition) {
        return new Rook(this.color, this.type, targetPosition);
    }
}
