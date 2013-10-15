package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import junit.framework.TestCase;

import java.text.ParseException;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private static EditText et;
    private static TextView tw;
//    private boolean error;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText) findViewById(R.id.edittext);
        tw = (TextView) findViewById(R.id.textview);
    }

    public static double calculate(String expr) {
        return Parser.getValue(expr);
    }

    public void printOne(View v) {
        et.setText(et.getText() + "1");
    }

    public void printTwo(View v) {
        et.setText(et.getText() + "2");
    }

    public void printTree(View v) {
        et.setText(et.getText() + "3");
    }

    public void printFour(View v) {
        et.setText(et.getText() + "4");
    }

    public void printFive(View v) {
        et.setText(et.getText() + "5");
    }

    public void printSix(View v) {
        et.setText(et.getText() + "6");
    }

    public void printSeven(View v) {
        et.setText(et.getText() + "7");
    }

    public void printEight(View v) {
        et.setText(et.getText() + "8");
    }

    public void printNine(View v) {
        et.setText(et.getText() + "9");
    }

    public void printZero(View v) {
        et.setText(et.getText() + "0");
    }

    public void printPlus(View v) {
        et.setText(et.getText() + "+");
    }

    public void printMinus(View v) {
        et.setText(et.getText() + "-");
    }

    public void printMul(View v) {
        et.setText(et.getText() + "*");
    }

    public void printDiv(View v) {
        et.setText(et.getText() + "/");
    }

    public void printDot(View v) {
        et.setText(et.getText() + ".");
    }

    public void printEqual(View v) {
        double tmp = calculate(et.getText().toString());
        if (Parser.parseError) {
            tw.setText("error");
        } else {
            if (Parser.divisionByZero) {
                tw.setText("division by zero");
            } else tw.setText(tmp + "");
        }


    }

    public void printOpenBracket(View v) {
        et.setText(et.getText() + "(");
    }

    public void printCloseBracket(View v) {
        et.setText(et.getText() + ")");
    }

    public void del(View v) {
        String s = et.getText().toString();
        if (s.length() > 0) et.setText(s.substring(0, s.length() - 1));
    }

    public void clear(View v) {
        et.setText("");
        tw.setText("");
    }
}
