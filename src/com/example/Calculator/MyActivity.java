package com.example.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {

    EditText field;
    int brackets = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        field = (EditText) findViewById(R.id.editText);
    }
    public void writeDigit(char c) {
        field.setText(field.getText().toString() + c);
    }
    public void one(View v) {writeDigit('1');}
    public void two(View v) {writeDigit('2');}
    public void three(View v) {writeDigit('3');}
    public void four(View v) {writeDigit('4');}
    public void five(View v) {writeDigit('5');}
    public void six(View v) {writeDigit('6');}
    public void seven(View v) {writeDigit('7');}
    public void eight(View v) {writeDigit('8');}
    public void nine(View v) {writeDigit('9');}
    public void zero(View v) {writeDigit('0');}

    public void dot(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            field.setText(s.toString() + "0.");
            return;
        }
        char c = s.charAt(s.length() - 1);
        int i = s.length() - 1;
        while (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')  {
            if (s.charAt(i) == '.') return;
            i--;
            if (i < 0) break;
        }
        if (Character.isDigit(c)) field.setText(s.toString() + '.');
        else field.setText(s.toString() + "0.");
    }

    public void plus(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            return;
        }
        char c = s.charAt(s.length() - 1);
        if (Character.isDigit(c) || c == ')') field.setText(s.toString() + '+');
        if (c == '.') field.setText(s.substring(0, s.length() - 1) + '+');
    }

    public void minus(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            return;
        }
        char c = s.charAt(s.length() - 1);
        if (Character.isDigit(c) || c == ')') field.setText(s.toString() + '-');
        if (c == '.') field.setText(s.substring(0, s.length() - 1) + '-');
    }

    public void sub(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            return;
        }
        char c = s.charAt(s.length() - 1);
        if (Character.isDigit(c) || c == ')') field.setText(s.toString() + '*');
        if (c == '.') field.setText(s.substring(0, s.length() - 1) + '*');
    }

    public void div(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            return;
        }
        char c = s.charAt(s.length() - 1);
        if (Character.isDigit(c) || c == ')') field.setText(s.toString() + '/');
        if (c == '.') field.setText(s.substring(0, s.length() - 1) + '/');
    }

    public void brakets(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            brackets++;
            field.setText("(");
            return;
        }
        char c = s.charAt(s.length() - 1);
        if (brackets > 0) {
            if (c == '.') {
                field.setText(s.substring(0, s.length() - 1) + ')');
                brackets--;
            }
            if (Character.isDigit(c) || c == ')') {
                field.setText(s.toString() + ')');
                brackets--;
            }

        }
        if (c == '(' || c == '+' || c == '-' || c == '/' || c == '*') {
            field.setText(s.toString() + '(');
            brackets++;
            return;
        }
    }

    public void equal(View v) {
        if (field.getText().toString().length() == 0) return;
        for (int i = 0; i < brackets; i++) {
            field.setText(field.getText().toString() + ')');
        }
        brackets = 0;
        Calculator calc = new Calculator(field.getText().toString());
        double x = calc.evaluate();
        String s;
        s = Double.toString(x);
        field.setText(s);
    }

    public void sign(View v) {
        StringBuilder s = new StringBuilder(field.getText().toString());
        if (s.length() == 0) {
            return;
        }
        char c = s.charAt(s.length() - 1);
        if (c == ')') {
            int i = s.length()- 1;
            while (s.charAt(i) != '(') i--;
            i++;
            if (s.charAt(i) == '-') {
                s.replace(i,i+1,"+");
                field.setText(s);
                return;
            }
            if (s.charAt(i) == '+') {
                s.replace(i,i+1,"-");
                field.setText(s);
                return;
            }
        }
        if (!Character.isDigit(c) && c != '.') return;
        int i = s.length() - 1;
        while (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.') {
              i--;
            if (i < 0) {
                i = 0;
                break;
            }
        }
        if (i == 0) {
            field.setText("(-" + s + ")");
        } else
        field.setText(s.substring(0,i+1)+ "(-" + s.substring(i+1, s.length()) + ")");
    }

    public void clear(View v) {
        field.setText("");
    }

    public void erase(View v) {
        if (field.getText().toString().length() == 0) return;
        field.setText(field.getText().toString().substring(0, field.getText().toString().length() - 1) );
    }
}
