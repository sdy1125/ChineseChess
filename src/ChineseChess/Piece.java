package ChineseChess;

public abstract class Piece {
	protected int value;
	protected boolean isRed;
	protected Position position;
	protected String symbol;

	public Piece(int value, boolean isRed, int x, int y, String symbol) {
		this.value = value;
		this.isRed = isRed;
		this.position = new Position(x, y);
		this.symbol = symbol;
	}

	public abstract boolean checkMove(int targetX, int targetY, Board board);

	public int getValue() {
		return isRed ? value : -value;
	}

	public String getDisplaySymbol() {
		return isRed ? Color.red + symbol + Color.reset : Color.black + symbol + Color.reset;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isRed() {
		return isRed;
	}
}