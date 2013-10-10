package mazinva.Calculator;

public class Plus extends AbstractBinaryOperation {
    public Plus(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() {
        return left.calculate() + right.calculate();
    }
}