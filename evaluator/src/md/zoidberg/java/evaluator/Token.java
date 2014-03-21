package md.zoidberg.java.evaluator;

/**
 * Created by gfv on 3/21/14.
 */
public class Token {
    enum Type {
        PLUS(1),
        MINUS(1),
        DIVIDE(2),
        MULTIPLY(2),
        LBRACK,
        RBRACK,
        NUMBER;
        private final int precedence;

        Type() {
            this(0);
        }

        Type(int precedence) {
            this.precedence = precedence;
        }

        public int getPrecedence() {
            return precedence;
        }
    }

    Type type;
    double value;

    public double getValue() {
            return value;
        }

    Token(Type type) {
        this(type, 0.0);
    }

    Token(Type type, double value) {
        this.type = type;
        this.value = value;
    }

    public boolean isOperator() {
        return (this.type == Type.PLUS || this.type == Type.MINUS || this.type == Type.MULTIPLY || this.type == Type.DIVIDE);
    }


}
