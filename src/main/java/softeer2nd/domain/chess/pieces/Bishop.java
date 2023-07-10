package softeer2nd.domain.chess.pieces;

public class Bishop extends Piece {
    private static final String REPRESENTATION = "b";
    private static final double POINT = 3.0;

    protected Bishop(final Color color, final Type type, final Position position) {
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
        return new Bishop(this.color, this.type, targetPosition);
    }
}
