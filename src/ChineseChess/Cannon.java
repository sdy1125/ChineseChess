package ChineseChess;

public class Cannon extends Piece {
    public Cannon(boolean isRed, int x, int y) {
        super(6, isRed, x, y, "C");
    }

    @Override
    public boolean checkMove(int tx, int ty, Board board) {
        int cx = position.getX();
        int cy = position.getY();

        if (board.getPiece(tx, ty) == null) { 
            if (tx == cx) {
                int minY = Math.min(cy, ty);
                int maxY = Math.max(cy, ty);
                int count = 0;
                for (int y = minY + 1; y < maxY; y++) {
                    if (board.getPiece(cx, y) != null) {
                        count++;
                    }
                }
                return count == 0;
            } else if (ty == cy) { 
                int minX = Math.min(cx, tx);
                int maxX = Math.max(cx, tx);
                int count = 0;
                for (int x = minX + 1; x < maxX; x++) {
                    if (board.getPiece(x, cy) != null) {
                        count++;
                    }
                }
                return count == 0;
            }
        } else { 
            if (tx == cx) { 
                int minY = Math.min(cy, ty);
                int maxY = Math.max(cy, ty);
                int count = 0;
                for (int y = minY + 1; y < maxY; y++) {
                    if (board.getPiece(cx, y) != null) {
                        count++;
                    }
                }
                return count == 1;
            } else if (ty == cy) { 
                int minX = Math.min(cx, tx);
                int maxX = Math.max(cx, tx);
                int count = 0;
                for (int x = minX + 1; x < maxX; x++) {
                    if (board.getPiece(x, cy) != null) {
                        count++;
                    }
                }
                return count == 1;
            }
        }
        return false;
    }
}
