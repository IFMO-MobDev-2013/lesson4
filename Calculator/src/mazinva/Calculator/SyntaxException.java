package mazinva.Calculator;

public class SyntaxException extends Exception {
    private String message;

    public SyntaxException(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
