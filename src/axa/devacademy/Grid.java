package axa.devacademy;

public class Grid {

	public static final int NUMBER_OF_ROWS = 6;
	public static final int NUMBER_OF_COLUMNS = 7;
	private TokenType[][] grid = new TokenType[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
	private int[] numberOfTokensInColumn = new int[NUMBER_OF_COLUMNS];

	public Grid() {
		for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				grid[i][j] = TokenType.EMPTY;
			}
		}
	}

	public TokenType getCell(int column, int row) {
		return grid[column][row];
	}

	public void addToken(int column, TokenType tokenType) throws ColumnOverflowException {
		int position = getCurrentColumnPosition(column);
		this.grid[column][position] = tokenType;
	}

	private int getCurrentColumnPosition(int column) throws ColumnOverflowException {
		int position = numberOfTokensInColumn[column];
		if (position >= NUMBER_OF_ROWS) {
			throw new ColumnOverflowException();
		}
		numberOfTokensInColumn[column]++;
		return position;
	}

	@Override
	public String toString() {
		String gridResult = "";
		for (int row = NUMBER_OF_ROWS-1; row >= 0; row--) {
			for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {				
				gridResult += grid[column][row].toString();
			}
			gridResult += "\n";
		}
		return gridResult;
	}


	
}
