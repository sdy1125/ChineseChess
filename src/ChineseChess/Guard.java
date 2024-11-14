package ChineseChess;

public class Guard extends Piece {
	public Guard(boolean isRed, int x, int y) {
		super(2, isRed, x, y, "M");
	}

	@Override
	public boolean checkMove(int tx, int ty, Board board) {
		int cx = position.getX();
		int cy = position.getY();

		if (Math.abs(tx - cx) != 1 || Math.abs(ty - cy) != 1) {
			return false;
		}

		if (isRed()) {

			if (tx < 1 || tx > 3 || ty < 4 || ty > 6) {
				return false;
			}
		} else {

			if (tx < 8 || tx > 10 || ty < 4 || ty > 6) {
				return false;
			}
		}

		Piece targetPiece = board.getPiece(tx, ty);
		if (targetPiece != null && targetPiece.isRed() == this.isRed()) {
			return false;
		}

		return true;
	}
}