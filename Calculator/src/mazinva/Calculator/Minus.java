package mazinva.Calculator;

public class Minus extends AbstractBinaryOperation {
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() throws SyntaxException {
        return left.calculate() - right.calculate();
    }
}