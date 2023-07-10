package softeer2nd.domain.chess.pieces;

public class Blank extends Bishop {
    private static final String REPRESENTATION = ".";
    private static final double POINT = 0.0;

    protected Blank(final Color color, final Type type, final Position position) {
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
        return new Blank(this.color, this.type, targetPosition);
    }
}
