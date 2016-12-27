package com.java.android.dronov.calculator;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayDeque;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String BAD_FORMAT = "=Неверный формат";
    protected EditText exprText;
    protected TextView textView;
    protected ScrollView scroll;
    protected ParseExpression parser;
    private int bracketBalance = 0;
    private Character[] operators = {'+', '-', '/', '*'};
    private ArrayDeque<Boolean> isUnary;
    private Boolean isBadFormat = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        isUnary = new ArrayDeque<Boolean>();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Initialization();
        exprText = (EditText) findViewById(R.id.EditText);
        exprText.setText("0");
        View.OnTouchListener otl = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Layout layout = ((EditText) v).getLayout();
                        float x = event.getX() + exprText.getScrollX();
                        int offset = layout.getOffsetForHorizontal(0, x);
                        if (offset > 0)
                            if (x > layout.getLineMax(0))
                                exprText.setSelection(offset);     // touch was at end of text
                            else
                                exprText.setSelection(offset - 1);
                        break;
                }
                return true;
            }
        };
        exprText.setOnTouchListener(otl);

        scroll.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
    }

    private void Initialization() {
        textView = (TextView) findViewById(R.id.textView1);
        exprText = (EditText) findViewById(R.id.EditText);
        scroll = (ScrollView) findViewById(R.id.ScrolView1);
        findViewById(R.id.allClear).setOnClickListener(this);
        findViewById(R.id.brackets).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.plusminus).setOnClickListener(this);
        findViewById(R.id.point).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Character lastChar;
        updateUnary(exprText);
        switch (view.getId()) {

            case R.id.zero:
                if (bannedLeadingZero(exprText)) { // banned leading zero
                    pushNumber(exprText);
                    exprText.append("0");
                }
                break;

            case R.id.one:
                pushNumber(exprText);
                exprText.append("1");
                break;

            case R.id.two:
                pushNumber(exprText);
                exprText.append("2");
                break;

            case R.id.three:
                pushNumber(exprText);
                exprText.append("3");
                break;

            case R.id.four:
                pushNumber(exprText);
                exprText.append("4");
                break;

            case R.id.five:
                pushNumber(exprText);
                exprText.append("5");
                break;

            case R.id.six:
                pushNumber(exprText);
                exprText.append("6");
                break;

            case R.id.seven:
                pushNumber(exprText);
                exprText.append("7");
                break;

            case R.id.eight:
                pushNumber(exprText);
                exprText.append("8");
                break;

            case R.id.nine:
                pushNumber(exprText);
                exprText.append("9");
                break;

            case R.id.allClear:             //clear all charachters in expression
                exprText.setText("0");
                bracketBalance = 0;
                break;

            case R.id.clear:                //delete last charachter
                if (isBadFormat) {
                    isBadFormat = false;
                    break;
                }
                lastChar = lastCharacter(exprText);
                if (lastChar != null) {
                    if (lastChar == ')') {
                        bracketBalance++;
                    } else if (lastChar == '(') {
                        bracketBalance--;
                    }
                }
                String curEditText = exprText.getText().toString().trim();
                if (curEditText.length() != 0) {
                    curEditText = curEditText.substring(0, curEditText.length() - 1);
                }
                exprText.setText(curEditText);
                if (exprText.length() == 0)
                    exprText.setText("0");
                break;

            case R.id.plus:
                lastChar = lastCharacter(exprText);
                if (!isUnary.isEmpty() && isUnary.getLast()) { // if last char is unary minus, we don't change it
                    break;
                }
                if (lastChar == null)
                    break;
                if (!isUnary.isEmpty() && !isUnary.getLast() && findOperator(lastChar)) //if last char is operator, we replace it
                    exprText = deleteLastCharacter(exprText);
                if (isUnaryOperationsInBrackets(exprText)) {
                    exprText.append(")");
                    bracketBalance--;
                }
                if (lastChar == '0' && exprText.getText().length() == 1)
                    exprText.setText("");
                exprText.append("+");
                break;

            case R.id.minus:
                lastChar = lastCharacter(exprText);
                if (!isUnary.isEmpty() && isUnary.getLast()) {// if last char is unary minus, we don't change it
                    break;
                }
                if (lastChar == null)
                    break;
                if (!isUnary.isEmpty() && !isUnary.getLast() && findOperator(lastChar))
                    exprText = deleteLastCharacter(exprText);          //if last char is operator, we replace it
                if (isUnaryOperationsInBrackets(exprText)) {
                    exprText.append(")");
                    bracketBalance--;
                }
                if (lastChar == '0' && exprText.getText().length() == 1)
                    break;
                exprText.append("-");
                break;

            case R.id.divide:
                lastChar = lastCharacter(exprText);
                if (!isUnary.isEmpty() && isUnary.getLast()) {                // if last char is unary minus, we don't change it
                    break;
                }
                if (lastChar == null)
                    break;
                if (!isUnary.isEmpty() && !isUnary.getLast() && findOperator(lastChar))
                    exprText = deleteLastCharacter(exprText);           //if last char is operator, we replace it
                if (isUnaryOperationsInBrackets(exprText)) {
                    exprText.append(")");
                    bracketBalance--;
                }
                if (lastChar == '0' && exprText.getText().length() == 1)
                    break;
                if (lastChar != '(')                              //there is no situation like "(/"
                    exprText.append("/");
                break;

            case R.id.multiply:
                lastChar = lastCharacter(exprText);
                if (!isUnary.isEmpty() && isUnary.getLast()) {  // if last char is unary minus, we don't change it
                    break;
                }
                if (lastChar == null)
                    break;
                if (!isUnary.isEmpty() && !isUnary.getLast() && findOperator(lastChar))
                    exprText = deleteLastCharacter(exprText);        //if last char is operator, we replace it
                if (isUnaryOperationsInBrackets(exprText)) {
                    exprText.append(")");
                    bracketBalance--;
                }
                if (lastChar == '0' && exprText.getText().length() == 1)
                    break;
                if (lastChar != '(')
                    exprText.append("*");                          //there is no situation like "(*"
                break;

            case R.id.brackets:
                lastChar = lastCharacter(exprText);
                if (!checkBracketSequence(exprText)) {
                    if (lastChar != null) {
                        if ((exprText.getText().length() == 1 && lastChar != '0' && lastChar != '(' && lastChar != ')') ||
                                (exprText.getText().length() > 1 && (lastChar >= '0' && lastChar <= '9' || lastChar == '.'))
                                || (lastChar == ')' && bracketBalance == 0)) {
                            exprText.append("*");      //append "*" between close and new open bracket
                        }
                    }
                    if (exprText.getText().length() == 1 && lastChar == '0')
                        exprText.setText("");
                    exprText.append("(");
                    bracketBalance++;
                } else {
                    exprText.append(")");
                    bracketBalance--;
                }
                break;

            case R.id.plusminus:
                lastChar = lastCharacter(exprText); //create unary minus, or delete, if last operation was unary minus
                if (lastChar != null) {
                    if (lastChar == ')') {
                        textView.setText(BAD_FORMAT);
                        isBadFormat = true;
                        break;
                    }
                }
                if (lastChar == '0' && exprText.getText().length() == 1)
                    exprText.setText("");

                doTheUnaryOperation(exprText);
                break;

            case R.id.equal:
                if (exprText.getText().toString().isEmpty())
                    break;
                while (bracketBalance > 0) {     //append close brackets, if balance is positive
                    exprText.append(")");
                    bracketBalance--;
                }
                parser = new ParseExpression(exprText.getText().toString());
                textView.setText("" + parser.getResult());
                break;

            case R.id.point:
                pointAdd(exprText);
                break;

        }
    }

    private void pointAdd(EditText exprText) {
        String curString = exprText.getText().toString();
        Character lastCharacter = lastCharacter(exprText);
        if (lastCharacter == null) {
            exprText.append("0.");
            return;
        }
        if (findOperator(lastCharacter)) {
            exprText.append("0.");
            return;
        }
        boolean needPoint = true;     // prohibits duplicate dot's
        for (int i = curString.length() - 1; i >= 0; i--) {
            if (curString.charAt(i) == '.') {
                needPoint = false;
                break;
            }
            if (findOperator(curString.charAt(i)) || curString.charAt(i) == '(' || curString.charAt(i) == ')')
                break;
        }
        if (needPoint) {
            lastCharacter = lastCharacter(exprText);
            if (lastCharacter == '(')
                exprText.append("0");
            if (lastCharacter == ')')
                exprText.append("*0");
            exprText.append(".");
        }
    }

    private boolean bannedLeadingZero(EditText exprText) {
        String curString = exprText.getText().toString();
        int index = -1;
        for (int i = curString.length() - 1; i >= 0; i--) {
            if (curString.charAt(i) == '.')
                return true;
            if (curString.charAt(i) == '(' || curString.charAt(i) == ')' || findOperator(curString.charAt(i))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return curString.charAt(0) != '0';
        }
        if (index == curString.length() - 1)
            return true;
        if (curString.charAt(index + 1) == '0') {
            return false;
        }
        return true;
    }

    private void pushNumber(EditText exprText) {
        String curString = exprText.getText().toString();
        Character lastChar = lastCharacter(exprText);
        if (lastChar != null) {
            if (lastChar == ')')          //append "*" in situation like "(expression)number"
                exprText.append("*");
            if (lastChar == '0') {
                if (curString.length() == 1 || (curString.length() >= 2 &&    //replace 0 to another number, if 0 is in first position
                        curString.toCharArray()[curString.length() - 2] == '(' ||
                        findOperator(curString.toCharArray()[curString.length() - 2]))) {
                    if (curString.length() == 1)
                        curString = "";
                    else curString = curString.substring(0, curString.length() - 1);
                    exprText.setText(curString);
                }
            }
        }
    }

    private void updateUnary(EditText exprText) {
        textView.setText("");
        isUnary.clear();
        String curString = exprText.getText().toString();
        for (int i = 0; i < curString.length(); i++)
            if (curString.charAt(i) == '-') {
                if (i >= 1 && curString.charAt(i - 1) == '(')
                    isUnary.add(true);
                else
                    isUnary.add(false);
            } else
                isUnary.add(false);
    }

    private void doTheUnaryOperation(EditText exprText) {
        String curString = exprText.getText().toString();
        if (curString.length() == 0) {
            exprText.append("(-");
            bracketBalance++;
            return;
        }
        for (int i = curString.length() - 1; i >= 0; i--) {
            while (i >= 0 && (curString.charAt(i) >= '0' && curString.charAt(i) <= '9' || curString.charAt(i) == '.'))
                i--;
            if (i == -1) {
                exprText.setText("(-" + curString);
                bracketBalance++;
                return;
            }
            if (findOperator(curString.charAt((i))) || curString.charAt(i) == '(') {
                if (curString.charAt(i) == '-') {
                    if (curString.charAt(i - 1) == '(') {
                        String addString = "";
                        for (int j = curString.length() - 1; j > i; j--)
                            addString = curString.charAt(j) + addString;
                        exprText.setText(curString.substring(0, i - 1) + addString);
                        bracketBalance--;
                        return;
                    }           // change unary minus
                }

                exprText.setText(curString.substring(0, i + 1) + "(-" + (curString.substring(i + 1, curString.length())));
                bracketBalance++;
                return;
            }
        }

    }

    private boolean isUnaryOperationsInBrackets(EditText exprText) {
        String curString = exprText.getText().toString();
        boolean isTrue = false;
        for (int i = curString.length() - 1; i >= 0; i--) {
            if ((curString.charAt(i) == ')' || findOperator(curString.charAt(i))) && curString.charAt(i) != '-') {
                isTrue = false;
                break;
            }
            if (curString.charAt(i) == '(') {
                if (i == curString.length() - 1) {
                    isTrue = false;
                    break;
                }
                if (curString.charAt(i + 1) == '-') {
                    isTrue = true;
                    break;
                }
            }
        }
        return isTrue;
    }

    private boolean findOperator(Character operator) {
        for (Character operator1 : operators) {
            if (operator1.equals(operator))
                return true;
        }
        return false;
    }

    private boolean checkBracketSequence(EditText exprText) {
        if (bracketBalance == 0) {
            return false;
        }
        String curString = exprText.getText().toString();
        boolean checkNumbers = false;
        int curBalance = bracketBalance;
        for (int i = curString.length() - 1; i >= 0; i--) {
            if (curString.charAt(i) == ')') {
                curBalance++;
                continue;
            }
            if (curString.charAt(i) == '(') {
                curBalance--;
                continue;
            }
            if (curBalance <= 0) {
                break;
            }
            return !findOperator(curString.charAt(i));
        }
        return checkNumbers;
    }

    private EditText deleteLastCharacter(EditText exprText) {
        String curString = exprText.getText().toString();
        if (lastCharacter(exprText) == ')')
            bracketBalance++;
        if (lastCharacter(exprText) == '(')
            bracketBalance--;
        curString = curString.substring(0, curString.length() - 1);
        exprText.setText(curString);
        return exprText;
    }

    private Character lastCharacter(EditText exprText) {
        String editTextString = exprText.getText().toString();
        if (editTextString.length() == 0) {
            return null;
        }
        return editTextString.toCharArray()[editTextString.length() - 1];
    }

}
