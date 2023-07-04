package softeer2nd.domain.chess.pieces;

public class Point {
    private final int x;
    private final int y;

    public Point(final int x, final int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("좌표는 음수가 될 수 없습니다.");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
