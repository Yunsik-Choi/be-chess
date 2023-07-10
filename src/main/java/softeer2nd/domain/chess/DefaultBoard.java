package softeer2nd.domain.chess;

import static softeer2nd.domain.chess.ChessGame.BLACK_GENERAL_LINE_Y;
import static softeer2nd.domain.chess.ChessGame.BLACK_PAWN_LINE_Y;
import static softeer2nd.domain.chess.ChessGame.HEIGHT;
import static softeer2nd.domain.chess.ChessGame.WHITE_GENERAL_LINE_Y;
import static softeer2nd.domain.chess.ChessGame.WHITE_PAWN_LINE_Y;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;
import softeer2nd.domain.chess.pieces.Position;

public class DefaultBoard implements Board {
    private final List<Rank> ranks;

    public DefaultBoard() {
        this.ranks = new ArrayList<>();
    }

    @Override
    public void initializeEmpty() {
        ranks.clear();
        IntStream.range(0, HEIGHT).forEach(y -> ranks.add(y, Rank.createNoPiece(y)));
    }

    @Override
    public void initialize() {
        ranks.clear();
        IntStream.range(0, HEIGHT).forEach(y -> ranks.add(Rank.createNoPiece(y)));
        ranks.set(ChessGame.BLACK_GENERAL_LINE_Y, Rank.createBlackGeneral(BLACK_GENERAL_LINE_Y));
        ranks.set(WHITE_GENERAL_LINE_Y, Rank.createWhiteGeneral(WHITE_GENERAL_LINE_Y));
        ranks.set(BLACK_PAWN_LINE_Y, Rank.createBlackPawn(BLACK_PAWN_LINE_Y));
        ranks.set(WHITE_PAWN_LINE_Y, Rank.createWhitePawn(WHITE_PAWN_LINE_Y));
    }

    @Override
    public int pieceCount() {
        return this.ranks.stream()
                .mapToInt(rank -> Long.valueOf(rank.getPieces().stream().filter(Piece::isNoPiece).count()).intValue())
                .sum();
    }

    @Override
    public int pieceCount(final Color color, final Type type) {
        return this.ranks.stream()
                .mapToInt(rank -> rank.pieceCount(color, type))
                .sum();
    }

    @Override
    public void move(final String sourcePosition, final String targetPosition) {
        Piece piece = findPiece(sourcePosition);

        move(targetPosition, piece);
    }

    @Override
    public Piece findPiece(final String position) {
        Position findPiecePosition = new Position(position);

        return this.ranks.get(findPiecePosition.getY()).getPiece(findPiecePosition.getX());
    }

    @Override
    public void move(final String position, final Piece piece) {
        Position movePosition = new Position(position);
        Position originPosition = piece.getPosition();

        this.ranks.get(originPosition.getY()).set(originPosition.getX(), Piece.createBlank(originPosition));
        this.ranks.get(movePosition.getY()).set(movePosition.getX(), piece.move(movePosition));
    }

    @Override
    public List<Piece> sortByPoint(final Color color, final PieceComparator pieceComparator) {
        return ranks.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor().equals(color))
                .sorted(pieceComparator.getComparator())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Rank> getRanks() {
        return Collections.unmodifiableList(this.ranks);
    }
}
