package softeer2nd.domain.chess;

public enum Turn {
    WHITE(Color.WHITE),
    BLACK(Color.BLACK);

    private final Color color;

    Turn(final Color color) {
        this.color = color;
    }

    public Turn end() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean is(final Color color) {
        return this.color.equals(color);
    }
}
