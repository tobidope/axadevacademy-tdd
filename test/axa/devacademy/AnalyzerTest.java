package axa.devacademy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnalyzerTest {

	Grid grid;
	Analyzer analyzer;

	@Before
	public void setUp() {
		grid = new Grid();
		analyzer = new Analyzer(grid);
	}

	@Test
	public void nobodyHasWonOnEmptyGrid() {
		assertFalse(analyzer.hasPlayerWon());
	}

	@Test
	public void someoneHasWonIfFourRedTokenAreInARowStartingAt0_0() throws ColumnOverflowException {
		grid.addToken(0, TokenType.RED);
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.RED);
		grid.addToken(3, TokenType.RED);
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void someoneHasWonIfFourRedTokenAreInARowStartingAt1_0() throws ColumnOverflowException {
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.RED);
		grid.addToken(3, TokenType.RED);
		grid.addToken(4, TokenType.RED);
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void nobodyHasWonIfThreeRedTokenAreInARowStartingAt0_0() throws ColumnOverflowException {
		grid.addToken(0, TokenType.RED);
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.RED);
		assertFalse(analyzer.hasPlayerWon());
	}

	@Test
	public void nobodyHasWonIfFourTokenInARowButDifferentColoursStartingAtRow0() throws ColumnOverflowException {
		grid.addToken(0, TokenType.YELLOW);
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.RED);
		grid.addToken(3, TokenType.RED);
		assertFalse(analyzer.hasPlayerWon());
	}

	@Test
	public void someOneHasWonIfFourTokenAreInARowStartingAt2_1() throws ColumnOverflowException {

		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.RED);
		grid.addToken(1, TokenType.YELLOW);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.YELLOW);
		assertEquals(
				".......\n" +
				".......\n" +
				".......\n" +
				".......\n" +
				".YYYY..\n" +
				".RYYR..\n",
				grid.toString());
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void someOneHasWonIfFourTokenAreInARowStartingAt2_5() throws ColumnOverflowException {

		// Row 1
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.RED);
		// Row 2
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.RED);
		// Row 3
		grid.addToken(1, TokenType.YELLOW);
		grid.addToken(2, TokenType.RED);
		grid.addToken(3, TokenType.RED);
		grid.addToken(4, TokenType.YELLOW);
		// Row 4
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.RED);
		// Row 5
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.RED);
		// Row 6
		grid.addToken(1, TokenType.YELLOW);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(4, TokenType.YELLOW);
		assertEquals(
				".YYYY..\n" +
				".RYYR..\n" +
				".RYYR..\n" +
				".YRRY..\n" +
				".RYYR..\n" +
				".RYYR..\n",
				grid.toString());
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void someOneHasWonIfFourRedTokensAreInTheSameColumn() throws ColumnOverflowException {
		grid.addToken(0, TokenType.RED);
		grid.addToken(0, TokenType.RED);
		grid.addToken(0, TokenType.RED);
		grid.addToken(0, TokenType.RED);
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void someOneHasWonIfRedTokensAreInDiagonal() throws ColumnOverflowException {
		grid.addToken(0, TokenType.RED);
		grid.addToken(1, TokenType.YELLOW);
		grid.addToken(1, TokenType.RED);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(2, TokenType.YELLOW);
		grid.addToken(2, TokenType.RED);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(3, TokenType.RED);
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void someOneHasWonIfRedTokensAreInDiagonalLeft() throws ColumnOverflowException {
		grid.addToken(6, TokenType.RED);
		grid.addToken(5, TokenType.YELLOW);
		grid.addToken(5, TokenType.RED);
		grid.addToken(4, TokenType.YELLOW);
		grid.addToken(4, TokenType.YELLOW);
		grid.addToken(4, TokenType.RED);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(3, TokenType.YELLOW);
		grid.addToken(3, TokenType.RED);
		assertEquals(
				".......\n"+
				".......\n" +
				"...R...\n" +
				"...YR..\n" +
				"...YYR.\n" +
				"...YYYR\n",
				grid.toString());
		assertTrue(analyzer.hasPlayerWon());
	}

	@Test
	public void gridIsFullAndNoWinner() throws ColumnOverflowException {
		this.grid = createDrawGrid();
		assertTrue(analyzer.isDraw());
	}

	public static Grid createDrawGrid() throws ColumnOverflowException {
		Grid drawGrid = new Grid() ;
		TokenType currentColor = null;
		for (int column = 0; column < Grid.NUMBER_OF_COLUMNS; column++) {
			if (column != 3) {
				currentColor = TokenType.RED;
			} else {
				currentColor = TokenType.YELLOW;
			}
			
			for (int row = 0; row < Grid.NUMBER_OF_ROWS; row++) {
				drawGrid.addToken(column, currentColor);
				currentColor = (currentColor == TokenType.RED) ? TokenType.YELLOW : TokenType.RED;
			}
		}
		return drawGrid;
	}

	@Test
	public void grodIsEmptyAndNoDraw() throws ColumnOverflowException {
		assertFalse(analyzer.isDraw());
	}
	

	
}
