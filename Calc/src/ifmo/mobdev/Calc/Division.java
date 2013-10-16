package ifmo.mobdev.Calc;

public class Division extends BinaryOperation {
	public Division(Function left, Function right) {
		super(left, right);
	}
	
	protected double doEvaluate(double left, double right) {
        if (right == 0)
            throw new DBZFunctionException();
        if (Double.isInfinite(left / right))
            throw new OverflowFunctionException();
        return left / right;
	}

}
