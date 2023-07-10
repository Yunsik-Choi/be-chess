package softeer2nd.domain.chess.pieces;

public class Queen extends Piece {
    private static final String REPRESENTATION = "q";
    private static final double POINT = 9.0;

    protected Queen(final Color color, final Type type, final Position position) {
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
        return new Queen(this.color, this.type, targetPosition);
    }
}
