package axa.devacademy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
	
	private Grid grid;

	@Before
	public void setup() {
		this.grid = new Grid();
	}

	@Test
	public void test_OnEmptyGridShowStateOfCell0_0IsEmpty() {
		assertEquals(TokenType.EMPTY, grid.getCell(0 ,0));
	}
	

	@Test
	public void test_AddYellowTokenToColumnZeroAndStateOfCell0_0IsYellow() throws ColumnOverflowException {
		grid.addToken(0, TokenType.YELLOW);
		assertEquals(TokenType.YELLOW, grid.getCell(0 ,0));
	} 

	@Test
	public void test_AddRedTokenToColumnZeroAndStateOfCell0_0IsRed() throws ColumnOverflowException {
		grid.addToken(0, TokenType.RED);
		assertEquals(TokenType.RED, grid.getCell(0 ,0));
	}
	
	@Test
	public void test_OnEmptyGridAddTwoYellowTokensToColumnZeroAndShowStateOfCell0_1IsYellow() throws ColumnOverflowException {
		grid.addToken(0, TokenType.YELLOW);
		grid.addToken(0, TokenType.YELLOW);
		assertEquals(TokenType.YELLOW, grid.getCell(0, 1));
	}
	
	@Test
	public void test_OnEmptyGridAddYellowThenRedTokenToColumnZeroAndShowColumnZeroIsYellowRedEmpty() throws ColumnOverflowException {
		grid.addToken(0, TokenType.YELLOW);
		grid.addToken(0, TokenType.RED);
		assertEquals(TokenType.YELLOW, grid.getCell(0, 0));
		assertEquals(TokenType.RED, grid.getCell(0, 1));
		assertEquals(TokenType.EMPTY, grid.getCell(0, 2));
		assertEquals(TokenType.EMPTY, grid.getCell(0, 3));
		assertEquals(TokenType.EMPTY, grid.getCell(0, 4));
		assertEquals(TokenType.EMPTY, grid.getCell(0, 5));
	}
	
	@Test
	public void test_AddSixYellowTokensToColumnZeroAndExpectAllTokensInColumnAreYellow() throws ColumnOverflowException {
		for (int i=0; i < 6; i++) {
			grid.addToken(0, TokenType.YELLOW);
		}
		
		for (int i=0; i < 6; i++) {
			assertEquals(TokenType.YELLOW, grid.getCell(0, i));
		}
	}
	
	@Test(expected=ColumnOverflowException.class)
	public void test_AddSevenYellowTokensToColumnZeroAndExpectAnErrorIsRaised() throws ColumnOverflowException {
		for (int i=0; i <= 6; i++) {
			grid.addToken(0, TokenType.YELLOW);
		}
	}
	
	@Test
	public void test_AddYellowTokenToColumnOneAndShowStateOfCell1_0IsYellow() throws ColumnOverflowException {
		grid.addToken(1, TokenType.YELLOW);
		assertEquals(TokenType.EMPTY, grid.getCell(0, 0));
		assertEquals(TokenType.YELLOW, grid.getCell(1,  0));
	}
	
	
	@Test
	public void test_ShowEmptyGrid (){
		assertEquals(
				".......\n"+
				".......\n"+
				".......\n"+
				".......\n"+
				".......\n"+
				".......\n", grid.toString());
	}

	@Test
	public void test_addYellowTokeToColumnYeroAndShowGridWithOneToken() throws ColumnOverflowException{
		grid.addToken(0, TokenType.YELLOW);
		assertEquals(".......\n"+
				".......\n"+
				".......\n"+
				".......\n"+
				".......\n"+
				"Y......\n",grid.toString());
	}
}
