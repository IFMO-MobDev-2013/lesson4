package ru.skipor.androidLecture4.Calculator;

import ru.skipor.androidLecture4.Calculator.Form.FormEvaluationException;

/**
 * Created by vladimirskipor on 10/10/13.
 */
public class Calculator {
    private StringBuilder stringBuilder;
    public static String errorMessage = "Error";
    private int bracketCounter;


    public Calculator() {
        stringBuilder = new StringBuilder();
        bracketCounter = 0;

    }

    private static boolean isValidOperation(String operationToken) {
        return operationToken.matches("[*+/-]");
    }

    private String lastInBuilder() {
        if (!empty()) {
            return String.valueOf(stringBuilder.charAt(stringBuilder.length() - 1));

        }
        return "";
    }

    private boolean empty() {
        return stringBuilder.length() == 0;
    }


    public String addBracket() {
        if (!lastInBuilder().equals(")") && !lastInBuilder().matches("[0-9]")) {
            bracketCounter++;
            stringBuilder.append('(');
        } else {
            if (bracketCounter > 0 && !lastInBuilder().equals("(") ){
                bracketCounter--;
                stringBuilder.append(')');
            }
        }


        return stringBuilder.toString();
    }

    public String addOperation(String operationToken) {
        if (isValidOperation(lastInBuilder())) {
            String last = lastInBuilder();
            deleteLastToken();
            if (!lastInBuilder().equals("(")){
            stringBuilder.append(operationToken);
            } else {
                stringBuilder.append(last);
            }
        } else if (!lastInBuilder().equals("(") || operationToken.equals("-")) {
            stringBuilder.append(operationToken);
        }





        return stringBuilder.toString();
    }

    public String addDigit(String digitToken) {

        if (!lastInBuilder().equals(")")) {
            stringBuilder.append(digitToken);
        }

        return stringBuilder.toString();
    }

    public String addDot() {


        if (lastInBuilder().matches("[0-9]")) {
            stringBuilder.append('.');

        }

        return stringBuilder.toString();
    }

    public String clear() {


        deleteLastToken();

        return stringBuilder.toString();

    }

    private void deleteLastToken() {
        if (!empty()) {

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    public String clearAll() {
        bracketCounter = 0;

        if (!empty()) {
            stringBuilder = new StringBuilder();
        }


        return stringBuilder.toString();
    }


    public String setValidText(String text) { //// only for tests
        stringBuilder = new StringBuilder(text);
        return stringBuilder.toString();

    }


    public String evaluate() {

        if (!empty() && !isValidOperation(lastInBuilder())) {
            for (; bracketCounter > 0; bracketCounter--) {
                stringBuilder.append(")");
            }


            try {
                String result = String.valueOf(FormParser.formParse(stringBuilder.toString()).evaluate());
                stringBuilder = new StringBuilder(result);
                return String.valueOf(result);
            } catch (ParserException e) {
                clearAll();
                e.printStackTrace();
                return errorMessage;
            } catch (FormEvaluationException e) {

                e.printStackTrace();
                clearAll();
                return errorMessage;
            }
        }
        return stringBuilder.toString();
    }
}
