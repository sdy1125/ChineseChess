package ChineseChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChess {
	private Board board;
	private List<String> moveHistory;
	private Scanner scanner;
	private boolean currentTurn;

	public ConsoleChess() {
		board = new Board();
		moveHistory = new ArrayList<>();
		scanner = new Scanner(System.in);
		currentTurn = true;
	}

	public void go() {
		board.initializeBoard();
		board.printBoard();

		while (true) {
			String turnText = currentTurn ? Color.yellow +"Lượt của quân Đỏ" : "Lượt của quân Đen";
			System.out.println(turnText);

			if (makeMove()) {
				if (checkGameOver()) {
					System.out.println((currentTurn ? "Quân Đỏ" : "Quân Đen") + " thắng");
					break;
				}
				currentTurn = !currentTurn;
			}
		}
	}

	private boolean makeMove() {

		System.out.println(Color.yellow + "Chọn quân cờ muốn di chuyển (x y): " + Color.reset);
		String input = scanner.nextLine();

		if (input.equalsIgnoreCase("exit")) {
			return false;
		}

		try {
			String[] coords = input.split(" ");
			int fromX = Integer.parseInt(coords[0]);
			int fromY = Integer.parseInt(coords[1]);

			Piece piece = board.getPiece(fromX, fromY);
			if (piece == null) {
				System.out.println(Color.yellow + "Không có quân cờ tại vị trí này" + Color.reset);
				return false;
			}

			if (piece.isRed() != currentTurn) {
				System.out.println(Color.yellow + "Không phải lượt của quân này" + Color.reset);
				return false;
			}
			displayPossibleMoves(fromX, fromY);
			System.out.println(Color.yellow + "Nhập vị trí muốn đi đến (x y): " + Color.reset);
			input = scanner.nextLine();
			coords = input.split(" ");
			int toX = Integer.parseInt(coords[0]);
			int toY = Integer.parseInt(coords[1]);

			if (piece.checkMove(toX, toY, board)) {
				board.movePiece(fromX, fromY, toX, toY);
				moveHistory.add(String.format( Color.yellow + "Quân (%d,%d) -> (%d,%d)" , fromX, fromY, toX, toY) + Color.reset);
				System.out.println(Color.yellow + "Lịch sử di chuyển:");
				for (String move : moveHistory) {
					System.out.println(move);
				}
				board.printBoard();
				return true;
			} else {
				System.out.println("Vị trí quân cần đến không hợp lệ!");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Đầu vào không hợp lệ!");
			return false;
		}

	}

	private boolean checkGameOver() {
		boolean redGeneralExists = false;
		boolean blackGeneralExists = false;
		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 10; j++) {
				Piece piece = board.getPiece(i, j);
				if (piece instanceof General) {
					if (piece.isRed()) {
						redGeneralExists = true;
					} else {
						blackGeneralExists = true;
					}
				}
			}
		}

		return !redGeneralExists || !blackGeneralExists;
	}

	private void displayPossibleMoves(int fromX, int fromY) {
		StringBuilder moves = new StringBuilder();
		Piece piece = board.getPiece(fromX, fromY);

		for (int x = 1; x < 11; x++) {
			for (int y = 1; y < 10; y++) {
				if (piece.checkMove(x, y, board)) {
					moves.append("(").append(x).append(",").append(y).append(") ");
				}
			}
		}

		System.out.println(Color.yellow + "Các nước đi có thể:");
		System.out.println(moves.toString());
	}
}