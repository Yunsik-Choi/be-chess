package softeer2nd.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.Board;
import softeer2nd.domain.chess.pieces.Pawn;

public class ConsoleOutputView implements OutputView {
    private static final String BOARD_EMPTY_REPRESENTATION = ".";

    @Override
    public void print(final Board board) {
        List<List<String>> result = initializeBoardRepresentation();

        board.getPawns()
                .forEach(pawn -> setRepresentation(result, pawn));

        System.out.println(convertBoardRepresentation(result));
    }

    private void setRepresentation(final List<List<String>> result, final Pawn pawn) {
        result.get(pawn.getPoint().getY())
                .set(pawn.getPoint().getX(), pawn.getRepresentation());
    }

    private String convertBoardRepresentation(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join(" ", list))
                .collect(Collectors.joining("\n"));
    }

    private List<List<String>> initializeBoardRepresentation() {
        return IntStream.range(0, Board.HEIGHT_SIZE)
                .mapToObj(y -> IntStream.range(0, Board.WIDTH_SIZE)
                        .mapToObj(x -> BOARD_EMPTY_REPRESENTATION).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }
}
