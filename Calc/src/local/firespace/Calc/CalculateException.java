package local.firespace.Calc;

public class CalculateException extends Exception {
	private String message;

	public CalculateException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
