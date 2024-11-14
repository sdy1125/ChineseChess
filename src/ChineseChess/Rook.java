package ChineseChess;

public class Rook extends Piece {
	public Rook(boolean isRed, int x, int y) {
		super(5, isRed, x, y, "R");
	}

	@Override
	public boolean checkMove(int tx, int ty, Board board) {
		int cx = position.getX();
		int cy = position.getY();

		if (cx == tx && cy == ty) {
			return false;
		}

		if (cx == tx) {
			int step = (ty > cy) ? 1 : -1;
			for (int y = cy + step; y != ty; y += step) {
				if (board.getPiece(cx, y) != null) {
					return false;
				}
			}

			return board.getPiece(tx, ty) == null || board.getPiece(tx, ty).isRed() != isRed();
		} else if (cy == ty) {
			int step = (tx > cx) ? 1 : -1;
			for (int x = cx + step; x != tx; x += step) {
				if (board.getPiece(x, cy) != null) {
					return false;
				}
			}

			return board.getPiece(tx, ty) == null || board.getPiece(tx, ty).isRed() != isRed();
		}
		return false;
	}
}