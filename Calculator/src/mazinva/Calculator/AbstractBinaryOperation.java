package mazinva.Calculator;

public abstract  class AbstractBinaryOperation implements Expression {
    protected Expression left;
    protected Expression right;

    public AbstractBinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

}

