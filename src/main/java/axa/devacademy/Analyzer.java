package axa.devacademy;

public class Analyzer implements AnalyzerInterface {

	private static final int DISTANCE_TO_EDGE = 3;
	private static final int DIRECTION_RIGHT = 1;
	private static final int DIRECTION_LEFT = -1;
	private Grid grid;

	public Analyzer(Grid grid) {
		this.grid = grid;
	}

	/* (non-Javadoc)
	 * @see axa.devacademy.AnalyzerInterface#hasPlayerWon()
	 */
	@Override
	public boolean hasPlayerWon() {
		
		return hasWonOnARow() || hasWonOnAColumn() || hasWonOnARightDiagonal() || hasWonOnALeftDiagonal();
	}

	private boolean hasWonOnALeftDiagonal() {
		for (int row = 0; row < DISTANCE_TO_EDGE; row++) {
			for (int column = Grid.NUMBER_OF_COLUMNS-1; column >= DISTANCE_TO_EDGE; column--) {
				if (isCellEmpty(row, column))
					continue;
				if (fourInADiagonal(row, column, DIRECTION_LEFT))
					return true;
			}
		}
		return false;
	}

	private boolean hasWonOnARightDiagonal() {
		for (int row = 0; row < DISTANCE_TO_EDGE; row++) {
			for (int column = 0; column < Grid.NUMBER_OF_COLUMNS - DISTANCE_TO_EDGE; column++) {
				if (isCellEmpty(row, column))
					continue;
				if (fourInADiagonal(row, column, DIRECTION_RIGHT))
					return true;
			}
		}
		return false;
	}

	private boolean hasWonOnAColumn() {
		for (int row = 0; row < Grid.NUMBER_OF_ROWS - DISTANCE_TO_EDGE; row++) {
			for (int column = 0; column < Grid.NUMBER_OF_COLUMNS; column++) {
				if (isCellEmpty(row, column))
					continue;
				if (fourInAColumn(row, column))
					return true;
			}
		}
		return false;
	}

	private boolean hasWonOnARow() {
		for (int row = 0; row < Grid.NUMBER_OF_ROWS; row++) {
			for (int column = 0; column < Grid.NUMBER_OF_COLUMNS - DISTANCE_TO_EDGE; column++) {
				if (isCellEmpty(row, column))
					continue;
				if (fourInARow(row, column))
					return true;
			}
		}
		return false;
	}

	private boolean fourInADiagonal(int row, int column, int direction) {
		return grid.getCell(column, row) == grid.getCell(column + 1 * direction, row + 1)
				&& grid.getCell(column + 1 * direction, row + 1) == grid.getCell(column + 2 * direction, row + 2)
				&& grid.getCell(column + 2 * direction, row + 2) == grid.getCell(column + 3 * direction, row + 3);
	}

	private boolean fourInARow(int row, int column) {
		return grid.getCell(column, row) == grid.getCell(column + 1, row)
				&& grid.getCell(column + 1, row) == grid.getCell(column + 2, row)
				&& grid.getCell(column + 2, row) == grid.getCell(column + 3, row);
	}

	private boolean fourInAColumn(int row, int column) {
		return grid.getCell(column, row) == grid.getCell(column, row + 1)
				&& grid.getCell(column, row + 1) == grid.getCell(column, row + 2)
				&& grid.getCell(column, row + 2) == grid.getCell(column, row + 3);

	}

	private boolean isCellEmpty(int row, int column) {
		return grid.getCell(column, row) == TokenType.EMPTY;
	}

	/* (non-Javadoc)
	 * @see axa.devacademy.AnalyzerInterface#isDraw()
	 */
	@Override
	public boolean isDraw() {
		boolean isAllFilled = true;
		for (int column = 0; column < Grid.NUMBER_OF_COLUMNS; column++) {
			for (int row = 0; row < Grid.NUMBER_OF_ROWS; row++) 
				if (isCellEmpty(row, column)) {
					isAllFilled = false;
					break;
				}
		}
		return isAllFilled && hasPlayerWon() == false;
	}

}
