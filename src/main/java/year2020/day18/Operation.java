package year2020.day18;

public class Operation {
	
	public static final Operation ADD = new Operation("+");
	public static final Operation MULTIPLY = new Operation("*");
	
	private String sign;

	public Operation(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
