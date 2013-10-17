package mazinva.Calculator;

public class Plus extends AbstractBinaryOperation {
    public Plus(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() throws SyntaxException {
        return left.calculate() + right.calculate();
    }
}