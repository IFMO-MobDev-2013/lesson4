package ifmo.mobdev.Calc;

public abstract class BinaryOperation implements Function {
	protected Function left, right;
	
	public BinaryOperation(Function left, Function right) {
		this.left = left;
		this.right = right;
	}
	
	public double evaluate() {
		return doEvaluate(left.evaluate(), right.evaluate());
	}
	
	protected abstract double doEvaluate(double l, double r);
}
