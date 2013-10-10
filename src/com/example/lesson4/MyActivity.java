package com.example.lesson4;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import junit.framework.Assert;
import junit.framework.TestCase;

public class MyActivity extends Activity {
    private TextView expression;
    private boolean freeze;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        expression = (TextView) findViewById(R.id.expression);
        expression.setText("");
        expression.setMovementMethod(new ScrollingMovementMethod());
        freeze = false;
    }

    private static boolean isDigitOrDot(char c) {
        return (c == '.') || ((c <= '9') && (c >= '0'));
    }

    public void onButtonClick(View view) {
        if (freeze) {
            if (view.getId() == R.id.clear) {
                freeze = false;
                expression.setText("");
            }
        } else {
            String oldText = (expression.getText()).toString();
            switch (view.getId()) {
                case R.id.bracket:
                    if (oldText.length() == 0) {
                        expression.append("(");
                    } else {
                        switch (oldText.charAt(oldText.length() - 1)) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                            case '.':
                            case ')':
                                expression.append(")");
                                break;
                            default:
                                expression.append("(");
                                break;
                        }
                    }
                    break;
                case R.id.clear:
                    expression.setText("");
                    break;
                case R.id.d0:
                    expression.append("0");
                    break;
                case R.id.d1:
                    expression.append("1");
                    break;
                case R.id.d2:
                    expression.append("2");
                    break;
                case R.id.d3:
                    expression.append("3");
                    break;
                case R.id.d4:
                    expression.append("4");
                    break;
                case R.id.d5:
                    expression.append("5");
                    break;
                case R.id.d6:
                    expression.append("6");
                    break;
                case R.id.d7:
                    expression.append("7");
                    break;
                case R.id.d8:
                    expression.append("8");
                    break;
                case R.id.d9:
                    expression.append("9");
                    break;
                case R.id.del:
                    if (oldText.length() > 0) {
                        expression.setText(oldText.subSequence(0, oldText.length() - 1));
                    }
                    break;
                case R.id.div:
                    if (oldText.length() > 0) {
                        switch (oldText.charAt(oldText.length() - 1)) {
                            case '/':
                            case '*':
                                expression.setText(oldText.subSequence(0, oldText.length() - 1));
                                expression.append("/");
                                break;
                            case '+':
                            case '-':
                            case '(':
                                break;
                            default:
                                expression.append("/");
                                break;
                        }
                    }
                    break;
                case R.id.dot:
                    if (oldText.length() == 0) {
                        expression.setText("0.");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        int i = oldText.length() - 1;
                        while ((i >= 0) && (isDigitOrDot(oldText.charAt(i)))) i--;
                        expression.setText(oldText.subSequence(0, i + 1));
                        for (int j = i + 1; j < oldText.length(); j++) {
                            if (oldText.charAt(j) != '.') sb.append(oldText.charAt(j));
                        }
                        String digits = sb.toString();
                        if (!("").equals(digits)) {
                            expression.append(digits);
                            expression.append(".");
                        } else {
                            expression.append("0.");
                        }
                    }
                    break;
                case R.id.equals:
                    freeze = true;
                    if (oldText.length() == 0) {
                        expression.setText("0");
                    } else {
                        try {
                            expression.setText("" + ExpressionParser.parseExpression((expression.getText()).toString()));
                        } catch (Exception e) {
                            expression.setText("ERROR");
                        }
                    }
                    break;
                case R.id.minus:
                    if (oldText.length() > 0) {
                        switch (oldText.charAt(oldText.length() - 1)) {
                            case '+':
                            case '-':
                                expression.setText(oldText.subSequence(0, oldText.length() - 1));
                                expression.append("-");
                                break;
                            default:
                                expression.append("-");
                                break;
                        }
                    } else {
                        expression.setText("-");
                    }
                    break;
                case R.id.mul:
                    if (oldText.length() > 0) {
                        switch (oldText.charAt(oldText.length() - 1)) {
                            case '/':
                            case '*':
                                expression.setText(oldText.subSequence(0, oldText.length() - 1));
                                expression.append("*");
                                break;
                            case '+':
                            case '-':
                            case '(':
                                break;
                            default:
                                expression.append("*");
                                break;
                        }
                    }
                    break;
                case R.id.plmi:
                    expression.append("*(-1)");
                    break;
                case R.id.plus:
                    if (oldText.length() > 0) {
                        switch (oldText.charAt(oldText.length() - 1)) {
                            case '+':
                            case '-':
                                expression.setText(oldText.subSequence(0, oldText.length() - 1));
                                expression.append("+");
                                break;
                            case '(':
                                break;
                            default:
                                expression.append("+");
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
