package softeer2nd.domain.chess.pieces;

import static softeer2nd.domain.chess.pieces.Piece.Type.*;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN("p", 1.0),
        KNIGHT("n", 2.5),
        ROOK("r", 5.0),
        BISHOP("b", 3.0),
        QUEEN("q", 9.0),
        KING("k", 0.0),
        NO_PIECE(".", 0.0);

        public static final double DUPLICATE_FILE_PAWN_POINT = 0.5;

        private final String representation;

        private final double point;

        Type(final String representation, final double point) {
            this.representation = representation;
            this.point = point;
        }

        public String getWhiteRepresentation() {
            return representation;
        }

        public String getBlackRepresentation() {
            return representation.toUpperCase();
        }

        public double getPoint() {
            return point;
        }

    }

    private final Color color;

    private final Type type;

    private Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createNoPiece() {
        return new Piece(Color.NOCOLOR, NO_PIECE);
    }

    public static Piece createWhitePawn() {
        return createWhite(PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(KING);
    }

    public static Piece createBlackKing() {
        return createBlack(KING);
    }

    private static Piece createWhite(final Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(final Type type) {
        return new Piece(Color.BLACK, type);
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public String getRepresentationPerPiece() {
        if (this.color.equals(Color.BLACK)) {
            return this.type.representation.toUpperCase();
        }
        return this.type.representation;
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isPawn() {
        return this.type.equals(PAWN);
    }

    public boolean isNoPiece() {
        return this.type == NO_PIECE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return Objects.equals(color, piece.color) && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
