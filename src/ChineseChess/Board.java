package ChineseChess;

public class Board {
	private Piece[][] pieces;

	public Board() {
		pieces = new Piece[11][12];
	}

	public void initializeBoard() {
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 11; j++) {
				pieces[i][j] = null;
			}
		}
		pieces[1][1] = new Rook(true, 1, 1);
		pieces[1][2] = new Horse(true, 1, 2);
		pieces[1][3] = new Elephant(true, 1, 3);
		pieces[1][4] = new Guard(true, 1, 4);
		pieces[1][5] = new General(true, 1, 5);
		pieces[1][6] = new Guard(true, 1, 6);
		pieces[1][7] = new Elephant(true, 1, 7);
		pieces[1][8] = new Horse(true, 1, 8);
		pieces[1][9] = new Rook(true, 1, 9);
		pieces[3][2] = new Cannon(true, 3, 2);
		pieces[3][8] = new Cannon(true, 3, 8);
		pieces[4][1] = new Soldier(true, 4, 1);
		pieces[4][3] = new Soldier(true, 4, 3);
		pieces[4][5] = new Soldier(true, 4, 5);
		pieces[4][7] = new Soldier(true, 4, 7);
		pieces[4][9] = new Soldier(true, 4, 9);

		pieces[10][1] = new Rook(false, 10, 1);
		pieces[10][2] = new Horse(false, 10, 2);
		pieces[10][3] = new Elephant(false, 10, 3);
		pieces[10][4] = new Guard(false, 10, 4);
		pieces[10][5] = new General(false, 10, 5);
		pieces[10][6] = new Guard(false, 10, 6);
		pieces[10][7] = new Elephant(false, 10, 7);
		pieces[10][8] = new Horse(false, 10, 8);
		pieces[10][9] = new Rook(false, 10, 9);
		pieces[8][2] = new Cannon(false, 8, 2);
		pieces[8][8] = new Cannon(false, 8, 8);
		pieces[7][1] = new Soldier(false, 7, 1);
		pieces[7][3] = new Soldier(false, 7, 3);
		pieces[7][5] = new Soldier(false, 7, 5);
		pieces[7][7] = new Soldier(false, 7, 7);
		pieces[7][9] = new Soldier(false, 7, 9);
	}

	public void printBoard() {

		for (int i = 1; i < 10; i++) {
			if (i == 1)
				System.out.print("   ");
			System.out.print(" " + (i));
		}

		for (int i = 1; i < 11; i++) {
			if (i == 1)
				System.out.print("\n   -------------------\n");
			if (i != 10)
				System.out.print(" " + (i) + "| ");
			else
				System.out.print("10| ");

			for (int j = 1; j < 10; j++) {
				if (pieces[i][j] == null) {
					System.out.print(". ");
				} else {
					System.out.print(pieces[i][j].getDisplaySymbol() + " ");
				}
			}
			System.out.print("|\n");
		}
		System.out.print("   -------------------\n\n");
	}

	public Piece getPiece(int x, int y) {
		return pieces[x][y];
	}

	public void movePiece(int fromX, int fromY, int toX, int toY) {
		Piece piece = pieces[fromX][fromY];

		if (piece != null && piece.checkMove(toX, toY, this)) {
			Piece targetPiece = pieces[toX][toY];
			if (targetPiece == null || targetPiece.isRed() != piece.isRed()) {
				pieces[toX][toY] = piece;
				pieces[fromX][fromY] = null;
				pieces[toX][toY].getPosition().setX(toX);
				pieces[toX][toY].getPosition().setY(toY);
			} else {
				System.out.println("Nước đi không hợp lệ: có quân tại vị trí đến.");
			}
		} else {
			System.out.println("Nước đi không hợp lệ.");
		}
	}

	public boolean isPathClear(int fromX, int fromY, int toX, int toY) {

		if (fromX == toX) {
			int minY = Math.min(fromY, toY);
			int maxY = Math.max(fromY, toY);
			for (int y = minY + 1; y < maxY; y++) {
				if (pieces[fromX][y] != null)
					return false;
			}
		} else if (fromY == toY) {
			int minX = Math.min(fromX, toX);
			int maxX = Math.max(fromX, toX);
			for (int x = minX + 1; x < maxX; x++) {
				if (pieces[x][fromY] != null)
					return false;
			}
		}
		return true;
	}

	public Piece findOpposingGeneral(Piece piece) {
		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 10; j++) {
				Piece currentPiece = pieces[i][j];
				if (currentPiece instanceof General && currentPiece.isRed() != piece.isRed()) {
					return currentPiece;
				}
			}
		}
		return null;
	}
}