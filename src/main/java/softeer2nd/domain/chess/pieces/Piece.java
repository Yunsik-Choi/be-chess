package softeer2nd.domain.chess.pieces;

import static softeer2nd.domain.chess.pieces.PieceRepresentation.*;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR
    }

    private final Color color;
    private final PieceRepresentation representation;

    private Piece(final Color color, final PieceRepresentation representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, BLACK_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, BLACK_KING_REPRESENTATION);
    }

    public Color getColor() {
        return this.color;
    }

    public String getRepresentation() {
        return this.representation.getValue();
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
        return Objects.equals(color, piece.color) && representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation);
    }
}
