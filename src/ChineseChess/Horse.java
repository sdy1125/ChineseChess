package ChineseChess;

public class Horse extends Piece {
	public Horse(boolean isRed, int x, int y) {
		super(4, isRed, x, y, "K");
	}

	@Override
	public boolean checkMove(int targetX, int targetY, Board board) {
		int currentX = position.getX();
		int currentY = position.getY();

		int dx = Math.abs(targetX - currentX);
		int dy = Math.abs(targetY - currentY);

		if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {

			if (dx == 1) {

				int midY = currentY + (targetY > currentY ? 1 : -1);
				return board.getPiece(currentX, midY) == null;
			} else {

				int midX = currentX + (targetX > currentX ? 1 : -1);
				return board.getPiece(midX, currentY) == null;
			}
		}
		return false;
	}
}
