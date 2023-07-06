package softeer2nd.domain.chess.pieces;

import static softeer2nd.domain.chess.pieces.Piece.Type.*;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR
    }

    public enum Type {
        PAWN("p"),
        KNIGHT("n"),
        ROOK("r"),
        BISHOP("b"),
        QUEEN("q"),
        KING("k"),
        NO_PIECE(".");

        private final String representation;

        Type(final String representation) {
            this.representation = representation;
        }

        public String getWhiteRepresentation() {
            return representation;
        }

        public String getBlackRepresentation() {
            return representation.toUpperCase();
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
        return new Piece(Color.WHITE, PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, KING);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, KING);
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
