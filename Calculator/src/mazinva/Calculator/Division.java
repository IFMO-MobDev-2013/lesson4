package mazinva.Calculator;

public class Division extends AbstractBinaryOperation {
    public Division(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() throws SyntaxException {
        double tmp = right.calculate();
        if (tmp == 0.0) {
            throw new SyntaxException("Division by zero");
        } else {
            return left.calculate() / tmp;
        }
    }
}