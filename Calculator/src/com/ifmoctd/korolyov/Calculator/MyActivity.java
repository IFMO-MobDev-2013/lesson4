package com.ifmoctd.korolyov.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyActivity extends Activity {
    TextView tvInput;
    TextView tvResult;

    public void btClrListener(View view) {
        tvResult.setText("");
        tvInput.setText("");
    }

    public void btBckListener(View view) {
        tvResult.setText("");
        CharSequence tmp = tvInput.getText();

        if (tmp.length() > 0) {
            tvInput.setText(tmp.subSequence(0, tmp.length() - 1));
        }

    }

    public void btBrOpenListener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "(");
    }

    public void btBrCloseListener(View view) {
        tvResult.setText("");
        String s = tvInput.getText().toString();
        if (s.length() > 0) {
            s = s.substring(s.length() - 1);
            String example = "+-*/(";
            if (!example.contains(s))
                tvInput.setText(tvInput.getText() + ")");
        }
    }

    public void btDotListener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + ".");
    }

    public void bt0Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "0");
    }

    public void bt1Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "1");
    }

    public void bt2Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "2");
    }

    public void bt3Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "3");
    }

    public void bt4Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "4");
    }

    public void bt5Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "5");
    }

    public void bt6Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "6");
    }

    public void bt7Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "7");
    }

    public void bt8Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "8");
    }

    public void bt9Listener(View view) {
        tvResult.setText("");
        tvInput.setText(tvInput.getText() + "9");
    }

    public void btSumListener(View view) {
        tvResult.setText("");
        String s = tvInput.getText().toString();
        if (s.length() > 0) {
            s = s.substring(s.length() - 1);
            String example = "+-*/";
            if (!example.contains(s))
                tvInput.setText(tvInput.getText() + "+");
        } else
            tvInput.setText(tvInput.getText() + "+");
    }

    public void btSubListener(View view) {
        tvResult.setText("");
        String s = tvInput.getText().toString();
        if (s.length() > 0) {
            s = s.substring(s.length() - 1);
            String example = "+-*/";
            if (!example.contains(s))

                tvInput.setText(tvInput.getText() + "-");
        } else
            tvInput.setText(tvInput.getText() + "-");
    }

    public void btMultListener(View view) {
        tvResult.setText("");
        String s = tvInput.getText().toString();
        if (s.length() > 0) {
            s = s.substring(s.length() - 1);
            String example = "+-*/";
            if (!example.contains(s))
                tvInput.setText(tvInput.getText() + "*");
        } else
            tvInput.setText(tvInput.getText() + "*");
    }

    public void btDivListener(View view) {
        tvResult.setText("");
        String s = tvInput.getText().toString();
        if (s.length() > 0) {
            s = s.substring(s.length() - 1);
            String example = "+-*/";
            if (!example.contains(s))
                tvInput.setText(tvInput.getText() + "/");
        } else
            tvInput.setText(tvInput.getText() + "/");
    }

    public void btResListener(View view) {
        tvResult.setText("");
        String s = tvInput.getText().toString();
        flag = false;
        double result = 0;
        if (s.length() == 0 || (s.charAt(s.length() - 1) != '+' && s.charAt(s.length() - 1) != '-' && s.charAt(s.length() - 1) != '*' && s.charAt(s.length() - 1) != '/')) {
            result = evaluate(s);
        } else {
            flag = true;
        }
        if (flag)
            tvResult.setText("ERROR");
        else {
            if (Math.abs(result - Math.round(result)) < 1e-8) {
                tvResult.setText(Integer.toString((int) result));
            } else {
                tvResult.setText(Double.toString(result));
            }

        }
    }


    static boolean isNumber(String source) {
        boolean t = true;
        if ("-.".equals(source) || "+.".equals(source))
            t = false;
        int count = 0;
        if (!((source.length() != 1 && (source.charAt(0) == '.' || source.charAt(0) == '+' || source.charAt(0) == '-')) || Character.isDigit(source.charAt(0))))
            t = false;

        for (int i = 1; i < source.length() && t; i++)
            if (source.charAt(i) == '.') {
                count++;
            } else {
                t = Character.isDigit(source.charAt(i));
            }
        if (count > 1) t = false;
        return t;
    }

    static boolean flag = false;

    public static double evaluate(String expression) {
        if (flag) return 0;
        if (expression.length() == 0) return 0;
        if (isNumber(expression)) {
            return Double.parseDouble(expression);
        } else {
            int balance = 0;
            for (int i = expression.length() - 1; i >= 0; i--) {
                if (expression.charAt(i) == '(') balance++;
                if (expression.charAt(i) == ')') balance--;
                if (expression.charAt(i) == '+' && balance == 0) {
                    return evaluate(expression.substring(0, i)) + evaluate(expression.substring(i + 1, expression.length()));
                } else if (expression.charAt(i) == '-' && balance == 0) {
                    return evaluate(expression.substring(0, i)) - evaluate(expression.substring(i + 1, expression.length()));
                }
            }

            balance = 0;
            for (int i = expression.length() - 1; i > 0; i--) {
                if (expression.charAt(i) == ')') balance++;
                if (expression.charAt(i) == '(') balance--;
                if (expression.charAt(i) == '*' && balance == 0) {
                    return evaluate(expression.substring(0, i)) * evaluate(expression.substring(i + 1, expression.length()));
                } else if (expression.charAt(i) == '/' && balance == 0) {
                    return evaluate(expression.substring(0, i)) / evaluate(expression.substring(i + 1, expression.length()));
                }
            }
            if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')')
                return evaluate(expression.substring(1, expression.length() - 1));
        }
        flag = true;
        return 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tvInput = (TextView) findViewById(R.id.tvInput);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }


}
