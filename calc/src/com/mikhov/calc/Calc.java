package com.mikhov.calc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;

public class Calc extends Activity implements View.OnClickListener {
    String text = "", resText = "", error = "";
    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btP, btM, btS, btD, btDel, btC, btMinus, btDot, btPM, btR;
    TextView t, resView;
    ScrollView myScroll;
    double result;
    boolean flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        t = (TextView)  findViewById(R.id.expr_view);
        resView = (TextView)  findViewById(R.id.res_view);
        myScroll = (ScrollView) findViewById(R.id.scr);

        bt0 = (Button) findViewById(R.id.bt_0);
        bt1 = (Button) findViewById(R.id.bt_1);
        bt2 = (Button) findViewById(R.id.bt_2);
        bt3 = (Button) findViewById(R.id.bt_3);
        bt4 = (Button) findViewById(R.id.bt_4);
        bt5 = (Button) findViewById(R.id.bt_5);
        bt6 = (Button) findViewById(R.id.bt_6);
        bt7 = (Button) findViewById(R.id.bt_7);
        bt8 = (Button) findViewById(R.id.bt_8);
        bt9 = (Button) findViewById(R.id.bt_9);
        btP = (Button) findViewById(R.id.bt_plus);
        btMinus = (Button) findViewById(R.id.bt_minus);
        btM = (Button) findViewById(R.id.bt_mult);
        btS = (Button) findViewById(R.id.bt_ls);
        btD = (Button) findViewById(R.id.bt_dev);
        btDel = (Button) findViewById(R.id.bt_del);
        btC = (Button) findViewById(R.id.bt_clear);
        btDot = (Button) findViewById(R.id.bt_dot);
        btPM = (Button) findViewById(R.id.bt_rs);
        btR = (Button) findViewById(R.id.bt_res);

        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        btP.setOnClickListener(this);
        btM.setOnClickListener(this);
        btS.setOnClickListener(this);
        btD.setOnClickListener(this);
        btDel.setOnClickListener(this);
        btC.setOnClickListener(this);
        btMinus.setOnClickListener(this);
        btDot.setOnClickListener(this);
        btPM.setOnClickListener(this);
        btR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_0:
                t.setTextColor(Color.DKGRAY);
                text += "0";
                t.setText(text);
                break;
            case R.id.bt_1:
                t.setTextColor(Color.DKGRAY);
                text += "1";
                t.setText(text);
                break;
            case R.id.bt_2:
                t.setTextColor(Color.DKGRAY);
                text += "2";
                t.setText(text);
                break;
            case R.id.bt_3:
                t.setTextColor(Color.DKGRAY);
                text += "3";
                t.setText(text);
                break;
            case R.id.bt_4:
                t.setTextColor(Color.DKGRAY);
                text += "4";
                t.setText(text);
                break;
            case R.id.bt_5:
                t.setTextColor(Color.DKGRAY);
                text += "5";
                t.setText(text);
                break;
            case R.id.bt_6:
                t.setTextColor(Color.DKGRAY);
                text += "6";
                t.setText(text);
                break;
            case R.id.bt_7:
                t.setTextColor(Color.DKGRAY);
                text += "7";
                t.setText(text);
                break;
            case R.id.bt_8:
                t.setTextColor(Color.DKGRAY);
                text += "8";
                t.setText(text);
                break;
            case R.id.bt_9:
                t.setTextColor(Color.DKGRAY);
                text += "9";
                t.setText(text);
                break;

            case R.id.bt_plus:
                t.setTextColor(Color.DKGRAY);
                text += "+";
                t.setText(text);
                break;
            case R.id.bt_minus:
                t.setTextColor(Color.DKGRAY);
                text += "-";
                t.setText(text);
                break;
            case R.id.bt_mult:
                t.setTextColor(Color.DKGRAY);
                text += "*";
                t.setText(text);
                break;
            case R.id.bt_dev:
                t.setTextColor(Color.DKGRAY);
                text += "/";
                t.setText(text);
                break;
            case R.id.bt_dot:
                t.setTextColor(Color.DKGRAY);
                text += ".";
                t.setText(text);
                break;
            case R.id.bt_ls:
                t.setTextColor(Color.DKGRAY);
                text += "(";
                t.setText(text);
                break;
            case R.id.bt_rs:
                t.setTextColor(Color.DKGRAY);
                text += ")";
                t.setText(text);
                break;
            case R.id.bt_del:
                t.setTextColor(Color.DKGRAY);
                if (!text.equals("")) {
                    text = text.substring(0, text.length() - 1);
                }
                t.setText(text);
                break;
            case R.id.bt_clear:
                t.setTextColor(Color.DKGRAY);
                text = "";
                t.setText(text);
                break;
            case R.id.bt_res:
                MatchParser mp = new MatchParser();
                mp.Parse(t.getText().toString());
                if (error != "") {
                    resText += error;
                    resView.setText(resText);
                    t.setTextColor(Color.RED);
                    error = "";
                } else {
                    resText += t.getText().toString() + " = " + mp.Parse(t.getText().toString()) + "\n\n";
                    resView.setText(resText);
                    myScroll.scrollBy(0, +200);
                    text = "";
                    t.setText(text);
                }
                break;
            default:
                break;
        }
    }

    class Result {
        public double acc;
        public String rest;

        public Result(double v, String r) {
            this.acc = v;
            this.rest = r;
        }
    }

    class MatchParser {
        private HashMap<String, Double> variables;

        public MatchParser() {
            variables = new HashMap<String, Double>();
        }

        public void setVariable(String variableName, Double variableValue) {
            variables.put(variableName, variableValue);
        }

        public Double getVariable(String variableName) {
            if (!variables.containsKey(variableName)) {
                error += "Error: Try get unexists variable '"+variableName+"'\n";
                return 0.0;
            }
            return variables.get(variableName);
        }

        public double Parse(String s) {
            if ((s.length() == 0) || (s.charAt(s.length()-1) == '+') || (s.charAt(s.length()-1) == '-') || (s.charAt(s.length()-1) == '/') || (s.charAt(s.length()-1) == '*') || (s.charAt(s.length()-1) == '.')) {
                error += "Error: can't full parse\n";
                return 0.0;
            }
            for (int p = 1; p < s.length() - 1; p++) {
                if (s.charAt(p) == '.') {
                    if (!Character.isDigit(s.charAt(p - 1)) || (!Character.isDigit(s.charAt(p + 1)))) {
                        error += "Error: can't full parse\n";
                        return 0.0;
                    }
                }
            }
            Result result = PlusMinus(s);
            if (!result.rest.isEmpty()) {
                error += "Error: can't full parse\n";
                error += "rest: " + result.rest + "\n";
            }
            return result.acc;
        }

        private Result PlusMinus(String s) {
            Result current = MulDiv(s);
            double acc = current.acc;

            while (current.rest.length() > 0) {
                if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

                char sign = current.rest.charAt(0);
                String next = current.rest.substring(1);

                current = MulDiv(next);
                if (sign == '+') {
                    acc += current.acc;
                } else {
                    acc -= current.acc;
                }
            }
            return new Result(acc, current.rest);
        }

        private Result Bracket(String s) {
            char zeroChar = s.charAt(0);
            if (zeroChar == '(') {
                Result r = PlusMinus(s.substring(1));
                if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                    r.rest = r.rest.substring(1);
                } else {
                    error += "Error: not close bracket\n";
                }
                return r;
            }
            return FunctionVariable(s);
        }

        private Result FunctionVariable(String s) {
            String f = "";
            int i = 0;
            while (i < s.length() && (Character.isLetter(s.charAt(i)) || ( Character.isDigit(s.charAt(i)) && i > 0 ) )) {
                f += s.charAt(i);
                i++;
            }
            if (!f.isEmpty()) {
                if ( s.length() > i && s.charAt( i ) == '(') {
                    error += "error\n";
                    return new Result(0, "error");
                } else {
                    return new Result(getVariable(f), s.substring(f.length()));
                }
            }
            return Num(s);
        }

        private Result MulDiv(String s) {
            Result current = Bracket(s);

            double acc = current.acc;
            while (true) {
                if (current.rest.length() == 0) {
                    return current;
                }
                char sign = current.rest.charAt(0);
                if ((sign != '*' && sign != '/')) return current;

                String next = current.rest.substring(1);
                Result right = Bracket(next);

                if (sign == '*') {
                    acc *= right.acc;
                } else {
                    acc /= right.acc;
                }

                current = new Result(acc, right.rest);
            }
        }

        private Result Num(String s) {
            int i = 0;
            int dot_cnt = 0;
            boolean negative = false;
            if( s.charAt(0) == '-' ){
                negative = true;
                s = s.substring( 1 );
            }
            while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                    flag = true;
                }
                i++;
            }
            if( i == 0 ) {
                error += "can't get valid number in '" + s + "'";
                return new Result(0.0, "error");
            }

            double dPart = Double.parseDouble(s.substring(0, i));
            if( negative ) dPart = -dPart;
            String restPart = s.substring(i);

            return new Result(dPart, restPart);
        }
    }
}