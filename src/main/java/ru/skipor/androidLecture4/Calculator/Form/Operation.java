package ru.skipor.androidLecture4.Calculator.Form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Operation {



    PLUS("+", 0) {
        public Double apply(Double leftArgument, Double rightArgument) {
            return leftArgument + rightArgument;
        }
    },

    MINUS("-", 0) {
        public Double apply(Double leftArgument, Double rightArgument) {
            return leftArgument - rightArgument;

        }
    },

    TIMES("*", 1) {
        public Double apply(Double leftArgument, Double rightArgument) {
            return   leftArgument * rightArgument;

        }
    },

    DIVIDE("/", 1) {
        public Double apply(Double leftArgument, Double rightArgument) throws FormDBZException {
            if (rightArgument == 0) {
                throw new FormDBZException("division by zero");
            }
            return leftArgument / rightArgument;
        }
    },


;


    class FormDBZException extends FormEvaluationException{

        public FormDBZException(String message) {
            super(message);
        }
    }
    public static final int MAX_PRIORITY = 1;
    public static final int MIN_PRIORITY = 0;
    public final String token;
    public final int priority;
    private static final Map<String, Operation> MAP;

    static {
        MAP = new HashMap<String, Operation>();
        for (Operation op : Operation.values()) {
            MAP.put(op.token, op);
        }
    }

    private Operation(String token, int priority) {
        this.token = token;
        this.priority = priority;
    }

    public static Operation getOperation(String token) {
        return MAP.get(token);
    }

    public static Iterator<String> getKeySetIterator() {
        return MAP.keySet().iterator();
    }
    //    public static boolean containsOperation(String token) {
//        return MAP.containsKey(token);
//    }
    public abstract Double apply(Double leftArgument, Double rightArgument) throws FormEvaluationException;
}
