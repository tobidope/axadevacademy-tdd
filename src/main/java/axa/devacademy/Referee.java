package axa.devacademy;

public class Referee {

	private Grid grid;
	private Player nextPlayer = Player.YELLOW;
	private AnalyzerInterface analyzer;

	
	public Referee() {}
	
	public Referee(AnalyzerInterface analyzer){
		this.analyzer = analyzer;
	}
	
	public Player getNextPlayer() {
		return this.nextPlayer;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
		this.analyzer = new Analyzer(grid);
	}

	public void insertToken(int column) throws ColumnOverflowException {
		TokenType tokenType = getTokenTypeForPlayer(this.nextPlayer);
		this.grid.addToken(column, tokenType);
		switchToNextPlayer();
	}

	private void switchToNextPlayer() {
		this.nextPlayer = (nextPlayer == Player.YELLOW) ? Player.RED : Player.YELLOW;
	}

	private TokenType getTokenTypeForPlayer(Player player) {
		TokenType tokenType = player == Player.YELLOW ? TokenType.YELLOW : TokenType.RED;
		return tokenType;
	}

	public Player someoneHasWon() {
		if (analyzer.hasPlayerWon())
			return getCurrentPlayer();
		return Player.NONE;
	}

	private Player getCurrentPlayer() {
		return getNextPlayer() == Player.YELLOW ? Player.RED : Player.YELLOW;
	}

	public boolean hasGameFinished() {
		return analyzer.isDraw() || someoneHasWon() != Player.NONE;
	}

}
