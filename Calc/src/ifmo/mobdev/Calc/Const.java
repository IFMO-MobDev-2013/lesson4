package ifmo.mobdev.Calc;

public class Const implements Function {
	private double c;
	
	public Const(double c) {
		this.c = c;
	}
	
	public double evaluate() {
		return c;
	}
}
