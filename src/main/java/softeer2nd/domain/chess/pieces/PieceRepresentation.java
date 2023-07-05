package softeer2nd.domain.chess.pieces;

public enum PieceRepresentation {
    WHITE_REPRESENTATION("p"),
    BLACK_REPRESENTATION("P"),
    WHITE_KNIGHT_REPRESENTATION("n"),
    BLACK_KNIGHT_REPRESENTATION("N"),
    WHITE_ROOK_REPRESENTATION("r"),
    BLACK_ROOK_REPRESENTATION("R"), WHITE_BISHOP_REPRESENTATION("b"), BLACK_BISHOP_REPRESENTATION("B");

    private final String value;

    PieceRepresentation(final String representation) {
        this.value = representation;
    }

    public String getValue() {
        return value;
    }
}
