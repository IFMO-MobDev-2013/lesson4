package ifmo.mobdev.Calc;

public class Minus extends BinaryOperation {
	public Minus(Function left, Function right) {
		super(left, right);
	}
	
	protected double doEvaluate(double left, double right) {
	    if (Double.isInfinite(left - right))
            throw new OverflowFunctionException();
        return left - right;
    }
}
