package softeer2nd.domain.chess.pieces;

import static softeer2nd.domain.chess.ChessGame.WIDTH;

import java.util.Objects;
import softeer2nd.domain.chess.ChessGame;

public class Position {
    private static final int POSITION_LENGTH = 2;

    private final int x;
    private final int y;

    public Position(final String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("위치 정보가 잘못됐습니다.");
        }
        int xPos = lowerCaseToInt(position);
        int yPos = convertMathCode(position);

        validationPositionOutOfBound(xPos, yPos);
        this.x = xPos;
        this.y = yPos;
    }

    public Position(final int x, final int y) {
        validationPositionOutOfBound(x, y);
        this.x = x;
        this.y = y;
    }

    private int lowerCaseToInt(final String position) {
        return position.charAt(0) - 'a';
    }

    private int convertMathCode(final String position) {
        return ChessGame.HEIGHT - Character.getNumericValue(position.charAt(1));
    }

    private void validationPositionOutOfBound(final int xPos, final int yPos) {
        if (xPos < 0 || xPos >= WIDTH || yPos < 0 || yPos >= ChessGame.HEIGHT) {
            throw new IllegalArgumentException("위치 정보가 체스판의 크기를 벗어났습니다.");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
