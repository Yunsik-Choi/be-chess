package softeer2nd.domain.chess.pieces;

import java.util.Objects;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR
    }

    protected final Color color;
    protected final Position position;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public static Piece createBlank(final Position position) {
        return new Blank(Color.NOCOLOR, position);
    }

    public static Piece createWhitePawn(final Position position) {
        return new Pawn(Color.WHITE, position);
    }

    public static Piece createBlackPawn(final Position position) {
        return new Pawn(Color.BLACK, position);
    }

    public static Piece createWhiteKnight(final Position position) {
        return new Knight(Color.WHITE, position);
    }

    public static Piece createBlackKnight(final Position position) {
        return new Knight(Color.BLACK, position);
    }

    public static Piece createWhiteRook(final Position position) {
        return new Rook(Color.WHITE, position);
    }

    public static Piece createBlackRook(final Position position) {
        return new Rook(Color.BLACK, position);
    }

    public static Piece createWhiteBishop(final Position position) {
        return new Bishop(Color.WHITE, position);
    }

    public static Piece createBlackBishop(final Position position) {
        return new Bishop(Color.BLACK, position);
    }

    public static Piece createWhiteQueen(final Position position) {
        return new Queen(Color.WHITE, position);
    }

    public static Piece createBlackQueen(final Position position) {
        return new Queen(Color.BLACK, position);
    }

    public static Piece createWhiteKing(final Position position) {
        return new King(Color.WHITE, position);
    }

    public static Piece createBlackKing(final Position position) {
        return new King(Color.BLACK, position);
    }

    public abstract String getRepresentationPerPiece();

    public abstract double getPoint();

    public abstract Piece move(final Position position);

    public Color getColor() {
        return this.color;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isPawn() {
        return new Pawn(this.color, this.getPosition()).equals(this);
    }

    public boolean isNoPiece() {
        return new Blank(this.color, this.getPosition()).equals(this);
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
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
