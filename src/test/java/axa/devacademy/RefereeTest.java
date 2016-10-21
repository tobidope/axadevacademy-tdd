package axa.devacademy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RefereeTest {

	private Referee referee;
	private Grid grid;
	
	@Before
	public void setup() {
		this.referee = new Referee();
		this.grid = new Grid();
		this.referee.setGrid(grid);
	}
	
	@Test
	public void whoStartsPlayingOnEmptyGrid_YellowExpected() {
		assertEquals(Player.YELLOW, referee.getNextPlayer());
	}
	
	@Test
	public void insertTokenOnEmptyGrid_TokenShouldBeInGridAndYellow() throws ColumnOverflowException {
		referee.insertToken(0);
		assertEquals(TokenType.YELLOW, grid.getCell(0, 0));
	}
	
	@Test
	public void insertTwoTokensInColumnZero_TokenShouldBeYellowAndRed() throws ColumnOverflowException {
		referee.insertToken(0);
		referee.insertToken(0);
		assertEquals(TokenType.YELLOW, grid.getCell(0, 0));
		assertEquals(TokenType.RED, grid.getCell(0, 1));
	}
	
	@Test
	public void getNextPlayer_ShouldNotChangeWhenNoInsertHappens() {
		assertEquals(Player.YELLOW, referee.getNextPlayer());
		assertEquals(Player.YELLOW, referee.getNextPlayer());
	}
	
	@Test
	public void nobodyHasWonOnNoMoves(){
		assertEquals(Player.NONE,referee.someoneHasWon());
	}
	
	@Test
	public void playerHasWon() throws ColumnOverflowException{
		referee.insertToken(0);//Y
		referee.insertToken(1);//R
		referee.insertToken(2);//Y
		referee.insertToken(1);//R
		referee.insertToken(3);//Y
		referee.insertToken(1);//R
		referee.insertToken(3);//Y
		referee.insertToken(1);//R
		assertEquals(Player.RED, referee.someoneHasWon());
	}
	
	@Test
	public void nobodyHasWonOnDraw() throws ColumnOverflowException{
		referee.setGrid(AnalyzerTest.createDrawGrid());
		assertEquals(Player.NONE, referee.someoneHasWon());
	}

	@Test
	public void gameHasFinishedOnDraw() throws ColumnOverflowException{
		referee.setGrid(AnalyzerTest.createDrawGrid());
		assertTrue(referee.hasGameFinished());
	}
	
	@Test
	public void OnEmptyGridTheGameIsNotFinished() {
		assertFalse(referee.hasGameFinished());
	}
	
	@Test
	public void testWithAnalyzerStub_redPlayerWinsOnEmptyGrid() {
		StubAnalyzer a = new StubAnalyzer();
		a.setHasPlayerWon(true);
		this.referee = new Referee(a);
		assertEquals(Player.RED, this.referee.someoneHasWon());
	}
	
	
	
}
