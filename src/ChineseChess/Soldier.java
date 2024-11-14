package ChineseChess;

public class Soldier extends Piece {
	public Soldier(boolean isRed, int x, int y) {
		super(7, isRed, x, y, "P");
	}

	@Override
	public boolean checkMove(int tx, int ty, Board board) {
		int cx = position.getX();
		int cy = position.getY();
		int direction = (isRed) ? 1 : -1;
		int moveX = tx - cx;
		int moveY = ty - cy;

		Piece targetPiece = board.getPiece(tx, ty);
		if (targetPiece != null && targetPiece.isRed == isRed) {
			return false;
		}
		boolean crossedRiver = (isRed && cy > 5) || (!isRed && cy < 6);
		if (crossedRiver) {
			return Math.abs(moveX) <= 1 && Math.abs(moveY) <= 1;
		} else {
			return moveY == 0 && moveX == direction;
		}
	}
}