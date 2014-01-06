package ru.skipor.androidLecture4.Calculator;

import ru.skipor.androidLecture4.Calculator.Form.FormEvaluationException;

/**
 * Created by vladimirskipor on 10/10/13.
 */
public class Calculator {
    private static final String OUTPUT_FORMAT = "%f";
    private static final String UNAR_OPERATION_REGEX = "[+-]";
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
                if(operationToken.matches(UNAR_OPERATION_REGEX)) {
                    stringBuilder.append(operationToken);
                } else {
                stringBuilder.append(last);
                }
            }
        } else if (!(empty() || lastInBuilder().equals("("))|| operationToken.matches(UNAR_OPERATION_REGEX)) {
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
            int lastDot = stringBuilder.lastIndexOf(".");
            if(lastDot < 0 || !stringBuilder.substring(lastDot + 1, stringBuilder.length()).matches("[0-9]+"))
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
            if (lastInBuilder().equals("(")) {
                bracketCounter--;
            } else if (lastInBuilder().equals(")")) {
                bracketCounter++;
            }

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

        if (!empty() && !isValidOperation(lastInBuilder()) && bracketCounter == 0) {
            for (; bracketCounter > 0; bracketCounter--) {
                stringBuilder.append(")");
            }


            try {
                double result = FormParser.formParse(stringBuilder.toString()).evaluate();
                stringBuilder = new StringBuilder(String.valueOf(result));
                return getFormat(result);
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

    private String getFormat(double result) {
        return String.format(OUTPUT_FORMAT, result);
    }
}
