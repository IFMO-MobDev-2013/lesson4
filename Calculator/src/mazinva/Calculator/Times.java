package mazinva.Calculator;

public class Times extends AbstractBinaryOperation {
    public Times(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() throws SyntaxException {
        return left.calculate() * right.calculate();
    }
}