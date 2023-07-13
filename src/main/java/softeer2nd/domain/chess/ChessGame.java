package softeer2nd.domain.chess;

import java.util.List;
import softeer2nd.domain.chess.pieces.Piece;

public class ChessGame {
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;
    public static final int WHITE_PAWN_LINE_Y = 6;
    public static final int WHITE_GENERAL_LINE_Y = 7;
    public static final int BLACK_PAWN_LINE_Y = 1;
    public static final int BLACK_GENERAL_LINE_Y = 0;

    private final ChessView chessView;
    private final ChessPointCalculator chessPointCalculator;
    private final Board board;

    public ChessGame(final ChessView chessView, final ChessPointCalculator chessPointCalculator, final Board board) {
        this.chessView = chessView;
        this.chessPointCalculator = chessPointCalculator;
        this.board = board;
    }

    public void initializeEmpty() {
        board.initializeEmpty();
    }

    public void initialize() {
        board.initialize();
    }

    public String show() {
        return chessView.board(board);
    }

    public int pieceCount() {
        return this.board.pieceCount();
    }

    public void move(final String sourcePosition, final String targetPosition) {
        board.move(sourcePosition, targetPosition);
    }

    public Piece findPiece(final String position) {
        return this.board.findPiece(position);
    }

    public void addPiece(final String position, final Piece piece) {
        this.board.addPiece(position, piece);
    }

    public double calculatePoint(final Color color) {
        return this.chessPointCalculator.calculatePoint(board, color);
    }

    public List<Piece> sortByPoint(final Color color, final PieceComparator pieceComparator) {
        return this.board.sortByPoint(color, pieceComparator);
    }
}
