package com.ifmomd.CalqLater;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    EditText edtExpression;
    Button   btnBrace, btnClear, btnErase, btnEquals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewsByIds();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btnBrace.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnErase.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
    }

    private void findViewsByIds() {
        edtExpression = (EditText) findViewById(R.id.edtExpression);
        btnBrace = (Button) findViewById(R.id.btnBrace);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnErase = (Button) findViewById(R.id.btnErase);
        btnEquals = (Button) findViewById(R.id.btnEquals);
    }

    public static String formatDouble(double d) {
        if (d == (int) d)
            return String.format("%d", (int) d);
        else
            return String.format("%s", d);
    }

    @Override
    public void onClick(View v) {
        if (v == btnClear)
            edtExpression.getText().clear();
        if (v == btnErase)
            if (edtExpression.getText().length() > 0 && !edtExpression.getText().toString().equals(getResources().getString(R.string.txtInvalidExpression)))
                edtExpression.getText().delete(edtExpression.getText().length() - 1, edtExpression.getText().length());
            else
                edtExpression.getText().clear();
        if (v == btnBrace)
            inputButtonClick(v);
        if (v == btnEquals) {
            String s = edtExpression.getText().toString();
            int unclosedBraces = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == '(') unclosedBraces++;
                else if (s.charAt(i) == ')') unclosedBraces--;
            Double d;
            if (unclosedBraces > 0) d = null; else
            d = ProcessExpression(edtExpression.getText().toString(),
                                         Operations.DoubleOperations.ADD,
                                         Operations.DoubleOperations.SUBTRACT,
                                         Operations.DoubleOperations.MULTIPLY,
                                         Operations.DoubleOperations.DIVIDE,
                                         Operations.DoubleOperations.MODULO, new DoubleNumberFactory());
            if (d != null)
                edtExpression.setText(formatDouble(d));
            else {
                findViewById(R.id.tvError).setAlpha(1);
                Animation a = AnimationUtils.loadAnimation(this, R.anim.disappear);
                a.setFillAfter(true);
                findViewById(R.id.tvError).startAnimation(a);
            }
            edtExpression.setSelection(edtExpression.getText().length());
        }
        UpdateBrace();
    }

    private void UpdateBrace() {
        btnBrace.setEnabled(true);
        String s = edtExpression.getText().toString();
        if (s.length() == 0) {
            btnBrace.setText("(");
            return;
        }
        int unclosedBraces = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '(') unclosedBraces++;
            else if (s.charAt(i) == ')') unclosedBraces--;
        char c = s.charAt(edtExpression.getSelectionStart() - 1);
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(') {
            btnBrace.setText("(");
            return;
        } else {
            if (unclosedBraces > 0)
                btnBrace.setText(")");
            else
                btnBrace.setEnabled(false);
        }
    }


    private <T> T ProcessExpression(String s, Operator<T> addOperator, Operator<T> subtractOperator,
                                    Operator<T> multiplyOperator, Operator<T> divideOperator,
                                    Operator<T> moduloOperator, NumberFactory<T> factory) {
        try {
            Evaluable<T> e = ExpressionsParser
                    .parseExpression(s.replaceAll("\\s+", ""),
                                     addOperator,
                                     subtractOperator,
                                     multiplyOperator,
                                     divideOperator,
                                     moduloOperator,
                                     factory);
            return e.evaluate(new HashMap<String, T>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void inputButtonClick(View v) {
        String s = ((Button) v).getText().toString();
        String x = edtExpression.getText().toString();
        if (s.equals("."))
            for (int i = x.length()-1; i>=0; i--)
                if (x.charAt(i) == '.') return; else
                if (!Character.isDigit(x.charAt(i))) break;
        if (s.length() > 0 && (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) && x.length() > 0 &&
            (x.charAt(x.length() - 1) == '+' || x.charAt(x.length() - 1) == '-' ||
             x.charAt(x.length() - 1) == '*' || x.charAt(x.length() - 1) == '/'))
            edtExpression.getText().delete(edtExpression.getText().length() - 1, edtExpression.getText().length());
        if (edtExpression.getText().toString().equals(getResources().getString(R.string.txtInvalidExpression)))
            edtExpression.getText().clear();
        if (v instanceof Button && ((Button) v).getText() != null && edtExpression.getText() != null)
            edtExpression.getText().append(((Button) v).getText());
        UpdateBrace();
    }
}
