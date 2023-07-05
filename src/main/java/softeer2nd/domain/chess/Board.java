package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.common.util.StringUtils;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.PieceRepresentation;
import softeer2nd.domain.chess.pieces.Point;

public class Board {
    public static final int WIDTH_SIZE = 8;
    public static final int HEIGHT_SIZE = 8;
    private static final int WHITE_Piece_Y = 6;
    private static final int BLACK_Piece_Y = 1;
    private static final String BOARD_EMPTY_REPRESENTATION = ".";

    private final List<Piece> pieces = new ArrayList<>();

    public void initialize() {
        initializePiece(Piece.WHITE_COLOR, PieceRepresentation.WHITE_REPRESENTATION, WHITE_Piece_Y);
        initializePiece(Piece.BLACK_COLOR, PieceRepresentation.BLACK_REPRESENTATION, BLACK_Piece_Y);
    }

    private void initializePiece(final String color, final PieceRepresentation representation, final int y) {
        IntStream.range(0, WIDTH_SIZE)
                .forEach(x -> pieces.add(new Piece(color, representation, new Point(x, y))));
    }

    public void add(final Piece piece) {
        pieces.add(piece);
    }

    public Piece findPiece(final int index) {
        if (pieces.size() <= index) {
            throw new IndexOutOfBoundsException("체스 판에 추가된 기물의 수보다 큽니다.");
        }
        return pieces.get(index);
    }

    public int size() {
        return pieces.size();
    }

    public String getWhitePiecesResult() {
        return getPiecesResultOwnedBy(PieceRepresentation.WHITE_REPRESENTATION);
    }

    public String getBlackPiecesResult() {
        return getPiecesResultOwnedBy(PieceRepresentation.BLACK_REPRESENTATION);
    }

    private String getPiecesResultOwnedBy(final PieceRepresentation ownedRepresentation) {
        return pieces.stream()
                .map(Piece::getRepresentation)
                .filter(representation -> representation.equals(ownedRepresentation.getValue()))
                .collect(Collectors.joining());
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public String show() {
        List<List<String>> result = initializeBoardRepresentation();

        this.pieces.forEach(piece -> setRepresentation(result, piece));

        return convertBoardRepresentation(result);
    }

    private void setRepresentation(final List<List<String>> result, final Piece piece) {
        result.get(piece.getPoint().getY())
                .set(piece.getPoint().getX(), piece.getRepresentation());
    }

    private String convertBoardRepresentation(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join("", list))
                .map(StringUtils::appendNewLine)
                .collect(Collectors.joining());
    }

    private List<List<String>> initializeBoardRepresentation() {
        return IntStream.range(0, Board.HEIGHT_SIZE)
                .mapToObj(y -> IntStream.range(0, Board.WIDTH_SIZE)
                        .mapToObj(x -> BOARD_EMPTY_REPRESENTATION).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }
}
