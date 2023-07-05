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
    private static final String BOARD_EMPTY_REPRESENTATION = ".";

    private final List<Piece> pieces = new ArrayList<>();

    public void initialize() {
        pieces.clear();

        initializePiece(PieceColor.WHITE, PieceRepresentation.WHITE_REPRESENTATION);
        initializePiece(PieceColor.BLACK, PieceRepresentation.BLACK_REPRESENTATION);
    }

    private void initializePiece(final PieceColor color, final PieceRepresentation representation) {
        IntStream.range(0, Line.WIDTH)
                .forEach(x -> pieces.add(new Piece(color, representation)));
    }

    public String show() {
        List<List<String>> result = initializeBoardRepresentation();

        return convertBoardRepresentation(result);
    }

    private String convertBoardRepresentation(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join("", list))
                .map(StringUtils::appendNewLine)
                .collect(Collectors.joining());
    }

    private List<List<String>> initializeBoardRepresentation() {
        return IntStream.range(0, Board.HEIGHT_SIZE)
                .mapToObj(y -> IntStream.range(0, Line.WIDTH)
                        .mapToObj(x -> BOARD_EMPTY_REPRESENTATION).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }
}
