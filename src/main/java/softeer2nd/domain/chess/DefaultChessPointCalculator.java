package softeer2nd.domain.chess;

import static softeer2nd.domain.chess.ChessGame.HEIGHT;
import static softeer2nd.domain.chess.ChessGame.WIDTH;

import java.util.List;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;

public class DefaultChessPointCalculator implements ChessPointCalculator {
    @Override
    public double calculatePoint(final Board board, final Color color) {
        double totalPoint = board.getRanks().stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor().equals(color))
                .mapToDouble(piece -> piece.getType().getPoint())
                .sum();

        return calculateFileHasPawnOfPoint(board, totalPoint);
    }

    private double calculateFileHasPawnOfPoint(final Board board, final double point) {
        double calculateTotalDuplicatePawnDeduction = IntStream.range(0, WIDTH)
                .map(x -> this.countFileHasPawn(board.getRanks(), x))
                .filter(this::isFileHasManyPawn)
                .mapToDouble(count -> count * Type.DUPLICATE_FILE_PAWN_POINT)
                .sum();

        return point - calculateTotalDuplicatePawnDeduction;
    }

    private boolean isFileHasManyPawn(final int count) {
        return count > 1;
    }

    private int countFileHasPawn(final List<Rank> ranks, final int x) {
        long countOfRankHasPawn = IntStream.range(0, HEIGHT)
                .filter(y -> ranks.get(y).getPieces().get(x).isPawn())
                .count();

        return Long.valueOf(countOfRankHasPawn).intValue();
    }
}
