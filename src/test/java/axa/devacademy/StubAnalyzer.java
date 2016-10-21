package axa.devacademy;

public class StubAnalyzer implements AnalyzerInterface {

	private boolean hasPlayerWon;
	public void setHasPlayerWon(boolean hasPlayerWon) {
		this.hasPlayerWon = hasPlayerWon;
	}

	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}

	private boolean isDraw;
	
	@Override
	public boolean hasPlayerWon() {
		return this.hasPlayerWon;
	}

	@Override
	public boolean isDraw() {
		return this.isDraw;
	}

}
