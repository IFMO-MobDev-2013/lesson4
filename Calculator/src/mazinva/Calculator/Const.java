package mazinva.Calculator;

public class Const implements Expression {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    public double calculate() throws SyntaxException{
        return value;
    }
}
