package mazinva.Calculator;

public class Minus extends AbstractBinaryOperation {
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() {
        return left.calculate() - right.calculate();
    }
}