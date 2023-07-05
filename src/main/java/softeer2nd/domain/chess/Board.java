package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.common.util.StringUtils;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.PieceColor;
import softeer2nd.domain.chess.pieces.PieceRepresentation;

public class Board {
    public static final int HEIGHT_SIZE = 8;

    private static final int WHITE_PAWN_LINE_Y = 6;
    private static final int BLACK_PAWN_LINE_Y = 1;
    private static final String BOARD_EMPTY_REPRESENTATION = ".";

    private final List<Line> lines = new ArrayList<>();

    public void initialize() {
        lines.clear();
        IntStream.range(0, HEIGHT_SIZE).forEach(y -> lines.add(new Line()));
        initializePiece(lines.get(WHITE_PAWN_LINE_Y), PieceColor.WHITE, PieceRepresentation.WHITE_REPRESENTATION);
        initializePiece(lines.get(BLACK_PAWN_LINE_Y), PieceColor.BLACK, PieceRepresentation.BLACK_REPRESENTATION);
    }

    private void initializePiece(
            final Line line,
            final PieceColor color,
            final PieceRepresentation representation
    ) {
        IntStream.range(0, Line.WIDTH)
                .forEach(x -> line.set(x, new Piece(color, representation)));
    }

    public String show() {
        List<List<String>> result = initializeBoardRepresentation();

        setBoardRepresentation(result);

        return boardRepresentationFormatting(result);
    }

    private List<List<String>> initializeBoardRepresentation() {
        return IntStream.range(0, Board.HEIGHT_SIZE)
                .mapToObj(y -> IntStream.range(0, Line.WIDTH)
                        .mapToObj(x -> BOARD_EMPTY_REPRESENTATION).collect(Collectors.toList())
                ).collect(Collectors.toUnmodifiableList());
    }

    private void setBoardRepresentation(final List<List<String>> result) {
        IntStream.range(0, HEIGHT_SIZE)
                .forEach(y -> setLineRepresentation(result, y));
    }

    private void setLineRepresentation(final List<List<String>> result, final int y) {
        Line line = lines.get(y);

        IntStream.range(0, Line.WIDTH)
                .forEach(x -> result.get(y).set(x, getPieceRepresentation(line.getPieces().get(x))));
    }

    private String getPieceRepresentation(final Piece piece) {
        if (piece == null) {
            return BOARD_EMPTY_REPRESENTATION;
        }
        return piece.getRepresentation();
    }

    private String boardRepresentationFormatting(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join("", list))
                .map(StringUtils::appendNewLine)
                .collect(Collectors.joining());
    }
}
