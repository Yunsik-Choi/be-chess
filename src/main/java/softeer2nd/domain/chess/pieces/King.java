package softeer2nd.domain.chess.pieces;

public class King extends Piece {
    private static final String REPRESENTATION = "k";
    private static final double POINT = 0.0;

    protected King(final Color color, final Type type, final Position position) {
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
        return new King(this.color, this.type, targetPosition);
    }
}
