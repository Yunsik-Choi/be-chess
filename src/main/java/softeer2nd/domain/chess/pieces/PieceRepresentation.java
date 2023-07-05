package softeer2nd.domain.chess.pieces;

public enum PieceRepresentation {
    WHITE_REPRESENTATION("p"),
    BLACK_REPRESENTATION("P");

    private final String value;

    PieceRepresentation(final String representation) {
        this.value = representation;
    }

    public String getValue() {
        return value;
    }
}
