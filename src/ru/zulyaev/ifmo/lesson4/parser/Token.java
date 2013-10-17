package ru.zulyaev.ifmo.lesson4.parser;

class Token {
    final TokenType type;
    final String token;

    private Token(TokenType type, String token) {
        this.type = type;
        this.token = token;
    }

    private Token(TokenType type) {
        this(type, null);
    }

    static final Token OPEN = new Token(TokenType.OPEN);
    static final Token CLOSE = new Token(TokenType.CLOSE);
    static final Token DELIMITER = new Token(TokenType.DELIMITER);
    static final Token END = new Token(TokenType.END);

    static Token newVariable(String token) {
        return new Token(TokenType.VARIABLE, token);
    }

    static Token newOperator(String token) {
        return new Token(TokenType.OPERATOR, token);
    }

    static Token newFunction(String token) {
        return new Token(TokenType.FUNCTION, token);
    }

    static Token newValue(String token) {
        return new Token(TokenType.VALUE, token);
    }

    @Override
    public String toString() {
        return type + (token == null ? "" : " '" + token + "'");
    }
}
