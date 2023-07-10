package softeer2nd.domain.chess.pieces;

public class Knight extends Piece {
    private static final String REPRESENTATION = "n";
    private static final double POINT = 2.5;

    protected Knight(final Color color, final Type type, final Position position) {
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
        return new Knight(this.color, this.type, targetPosition);
    }
}
