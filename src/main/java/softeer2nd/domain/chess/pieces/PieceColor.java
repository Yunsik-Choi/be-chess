package softeer2nd.domain.chess.pieces;

public enum PieceColor {
    WHITE("white"),
    BLACK("black");

    private final String value;

    PieceColor(final String color) {
        this.value = color;
    }

    public String getValue() {
        return value;
    }
}
