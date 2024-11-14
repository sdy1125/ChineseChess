package ChineseChess;

public class Elephant extends Piece {
	public Elephant(boolean isRed, int x, int y) {
		super(3, isRed, x, y, "E");
	}

	@Override
	public boolean checkMove(int tx, int ty, Board board) {
		int cx = position.getX();
		int cy = position.getY();

		if (Math.abs(tx - cx) != 2 || Math.abs(ty - cy) != 2) {
			return false;
		}

		if (isRed()) {
			if (tx < 1 || tx > 5) {
				return false;
			}
		} else {
			if (tx < 6 || tx > 10) {
				return false;
			}
		}

		int midX = (cx + tx) / 2;
		int midY = (cy + ty) / 2;
		if (board.getPiece(midX, midY) != null) {
			return false;
		}

		return board.getPiece(tx, ty) == null || board.getPiece(tx, ty).isRed() != isRed();
	}
}