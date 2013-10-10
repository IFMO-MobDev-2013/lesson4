package mazinva.Calculator;

public class Division extends AbstractBinaryOperation /* throws Exception */ {
    public Division(Expression left, Expression right) {
        super(left, right);
    }

    public double calculate() {
        return left.calculate() / right.calculate();
    }
}