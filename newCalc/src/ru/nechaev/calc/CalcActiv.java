package ru.nechaev.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import junit.framework.Test;

import java.util.HashMap;


public class CalcActiv extends Activity
{
    TextView disp;
    Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, div, mul, leftbracked, rightbracket, cancel, delete, equal, point;
    String calcSting = "";

    int t = 0;
    int brkol = 0;
    class Result
    {

        public double acc;
        public String rest;

        public Result(double v, String r) {
            this.acc = v;
            this.rest = r;
        }
    }

    public class MathPars
    {
        private HashMap<String, Double> variables;

        public MathPars()
        {
            variables = new HashMap<String, Double>();
        }

        public void setVariable(String variableName, Double variableValue)
        {
            variables.put(variableName, variableValue);
        }

        public Double getVariable(String variableName)
        {
            if (!variables.containsKey(variableName)) {
                System.err.println( "Error: Try get unexists variable '"+variableName+"'" );
                return 0.0;
            }
            return variables.get(variableName);
        }

        public double Parse(String s) throws Exception
        {
            Result result = PlusMinus(s);
            if (!result.rest.isEmpty()) {
                System.err.println("Error: can't full parse");
                System.err.println("rest: " + result.rest);
            }
            return result.acc;
        }

        private Result PlusMinus(String s) throws Exception
        {
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

        private Result Bracket(String s) throws Exception
        {
            char zeroChar = s.charAt(0);
            if (zeroChar == '(') {
                Result r = PlusMinus(s.substring(1));
                if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                    r.rest = r.rest.substring(1);
                } else {
                    System.err.println("Error: not close bracket");
                }
                return r;
            }
            return FunctionVariable(s);
        }

        private Result FunctionVariable(String s) throws Exception
        {
            String f = "";
            int i = 0;
            while (i < s.length() && (Character.isLetter(s.charAt(i)) || ( Character.isDigit(s.charAt(i)) && i > 0 ) )) {
                f += s.charAt(i);
                i++;
            }
            if (!f.isEmpty()) { // если что-нибудь нашли
                return new Result(getVariable(f), s.substring(f.length()));
            }
            return Num(s);
        }

        private Result MulDiv(String s) throws Exception
        {
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

        private Result Num(String s) throws Exception
        {
            int i = 0;
            int dot_cnt = 0;
            boolean negative = false;
            if( s.charAt(0) == '-' ){
                negative = true;
                s = s.substring( 1 );
            }
            while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                    throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
                }
                i++;
            }
            if( i == 0 ){
                throw new Exception( "can't get valid number in '" + s + "'" );
            }

            double dPart = Double.parseDouble(s.substring(0, i));
            if( negative ) dPart = -dPart;
            String restPart = s.substring(i);

            return new Result(dPart, restPart);
        }


    }
    int next = 0;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);

        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        cancel = (Button) findViewById(R.id.cancel);

        equal = (Button) findViewById(R.id.equal);
        point = (Button) findViewById(R.id.point);
        delete = (Button) findViewById(R.id.delete);
        leftbracked = (Button) findViewById(R.id.leftbracket);
        rightbracket = (Button) findViewById(R.id.rightbracket);

        disp = (TextView) findViewById(R.id.display);

        one.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 1;
                disp.setText(calcSting);
            }
        });
        two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 2;
                disp.setText(calcSting);
            }
        });
        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 3;
                disp.setText(calcSting);
            }
        });
        four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 4;
                disp.setText(calcSting);
            }
        });
        five.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 5;
                disp.setText(calcSting);
            }
        });
        six.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 6;
                disp.setText(calcSting);
            }
        });
        seven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 7;
                disp.setText(calcSting);
            }
        });
        eight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 8;
                disp.setText(calcSting);
            }
        });
        nine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 9;
                disp.setText(calcSting);
            }
        });
        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting += 0;
                disp.setText(calcSting);
            }
        });
        mul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (calcSting.charAt(calcSting.length() - 1) != '-' && calcSting.charAt(calcSting.length() - 1) != '+' && calcSting.charAt(calcSting.length() - 1) != '*' && calcSting.charAt(calcSting.length() - 1) != '/')
                calcSting += "*";
                t = 0;
                disp.setText(calcSting);
            }
        });
        div.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (calcSting.charAt(calcSting.length() - 1) != '-' && calcSting.charAt(calcSting.length() - 1) != '+' && calcSting.charAt(calcSting.length() - 1) != '*' && calcSting.charAt(calcSting.length() - 1) != '/')
                calcSting += "/";
                t = 0;
                disp.setText(calcSting);
            }
        });
        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (calcSting.charAt(calcSting.length() - 1) != '-' && calcSting.charAt(calcSting.length() - 1) != '+' && calcSting.charAt(calcSting.length() - 1) != '*' && calcSting.charAt(calcSting.length() - 1) != '/')
                calcSting += "+";
                t = 0;
                disp.setText(calcSting);
            }
        });
        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (calcSting.charAt(calcSting.length() - 1) != '-' && calcSting.charAt(calcSting.length() - 1) != '+' && calcSting.charAt(calcSting.length() - 1) != '*' && calcSting.charAt(calcSting.length() - 1) != '/')
                calcSting += "-";
                t = 0;
                disp.setText(calcSting);
            }
        });
        point.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                if (t == 0) {
                    if (calcSting.length() == 0){
                        calcSting += 0; } else
                    if ((calcSting.charAt(calcSting.length() - 1) == '+') || (calcSting.charAt(calcSting.length() - 1) == '-') || (calcSting.charAt(calcSting.length() - 1) == '*') || (calcSting.charAt(calcSting.length() - 1) == '/')){
                         calcSting += 0;
                    }
                    calcSting += ".";
                    t = 1;
                }
                disp.setText(calcSting);
            }
        });
        leftbracked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                if (calcSting.length() == 0 || calcSting.charAt(calcSting.length() - 1) == '+' || calcSting.charAt(calcSting.length() - 1) == '-' || calcSting.charAt(calcSting.length() - 1) == '*' || calcSting.charAt(calcSting.length() - 1) == '/'){
                    calcSting += "(";
                    brkol ++;
                }
                disp.setText(calcSting);
            }
        });
        rightbracket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                if (brkol != 0){
                    calcSting += ")";
                    brkol--;
                }
                disp.setText(calcSting);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                calcSting = "";
                disp.setText(calcSting);
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (next == 0){
                    calcSting = "";
                    next = 1;
                }
                if (calcSting.length() != 0){
                    Character p = calcSting.charAt(calcSting.length() - 1);
                    if (calcSting.length() > 1)
                        calcSting = calcSting.substring(0, calcSting.length() - 1);
                    if (calcSting.length() == 1)
                        calcSting = "";
                    if (p == '('){
                        brkol--;
                    }
                    if (p == ')'){
                        brkol++;
                    }
                    if (p == '.'){
                        t = 0;
                    }
                }

                disp.setText(calcSting);
            }
        });
        equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MathPars p = new MathPars();
                try{
                    Double res = p.Parse(calcSting);
                    if (res % 1 != 0.0){
                        calcSting = Double.toString(res);
                    }
                    else {
                        calcSting = Long.toString(res.intValue());
                    }
                }catch (Exception e){
                    calcSting = e.getMessage();
                }

                disp.setText(calcSting);
                t = 0;
                brkol = 0;
                next = 0;
            }
        });

    }


}
