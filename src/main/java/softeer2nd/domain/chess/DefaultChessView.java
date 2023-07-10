package softeer2nd.domain.chess;

import static softeer2nd.domain.chess.ChessGame.HEIGHT;
import static softeer2nd.domain.chess.ChessGame.WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.common.util.StringUtils;
import softeer2nd.domain.chess.pieces.Piece;

public class DefaultChessView implements ChessView {
    @Override
    public String board(final Board board) {
        return boardRepresentationFormatting(boardRepresentation(board.getRanks()));
    }

    private List<List<String>> boardRepresentation(final List<Rank> ranks) {
        List<List<String>> result = IntStream.range(0, HEIGHT)
                .mapToObj(y -> new ArrayList<String>())
                .collect(Collectors.toList());

        IntStream.range(0, HEIGHT)
                .forEach(y -> setLineRepresentation(ranks, result, y));

        return result;
    }

    private void setLineRepresentation(final List<Rank> ranks, final List<List<String>> result, final int y) {
        Rank rank = ranks.get(y);

        IntStream.range(0, WIDTH)
                .forEach(x -> result.get(y).add(x, getPieceRepresentation(rank.getPieces().get(x))));
    }

    private String getPieceRepresentation(final Piece piece) {
        return piece.getRepresentationPerPiece();
    }

    private String boardRepresentationFormatting(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join("", list))
                .map(StringUtils::appendNewLine)
                .collect(Collectors.joining());
    }
}
