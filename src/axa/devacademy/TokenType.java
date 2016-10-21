package axa.devacademy;

public enum TokenType {
	EMPTY("."),
	YELLOW("Y"),
	RED("R");
	
	private String displayValue;
	
	private TokenType(String displayValue) {
		this.displayValue = displayValue;
	}
	
	@Override
	public String toString() {
		return displayValue;
	}

}
