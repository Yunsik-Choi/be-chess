package softeer2nd.domain.chess.pieces;

public enum PieceRepresentation {
    WHITE_REPRESENTATION("p"),
    BLACK_REPRESENTATION("P"),
    WHITE_KNIGHT_REPRESENTATION("n"),
    BLACK_KNIGHT_REPRESENTATION("N");

    private final String value;

    PieceRepresentation(final String representation) {
        this.value = representation;
    }

    public String getValue() {
        return value;
    }
}
