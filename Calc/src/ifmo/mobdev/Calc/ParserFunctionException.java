package ifmo.mobdev.Calc;

public class ParserFunctionException extends FunctionException {
    private String s;

    public ParserFunctionException(String msg) {
        this.s = msg;
    }

    @Override
    public String getMessage() {
        return s;
    }
}
