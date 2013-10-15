package com.example.lesson4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class MyActivity extends Activity {

    String s;

    public class Tests extends TestCase {
        private int testCount = 0;
        @Override
        protected void setUp() throws Exception {
            super.setUp();

        }
        private void checkTest(String countString, double result) {
            testCount++;
            String calc = "";
            try {
                calc = calculate(countString);

            } catch (Exception ex) {
                assertTrue(false);

            }

            double k = Math.abs(Double.parseDouble("" + calc) - result);
            assertTrue("Test #" + testCount + " failed", k < 1e-5);

        }
        public void runTest() {
            checkTest("1*2.0*1", 1 * 2.0 * 1);
            checkTest("-1/(-1)-1", -1 / (-1) - 1);
            checkTest("2*(2*2)*2", 2*(2*2.0)*2);
            checkTest("2*(2+2)*2", 2*(2+2)*2.0);
            checkTest("(15)/10+1.5", (15) / 10.0 + 1.5);
            checkTest("4+8-15", 4 + 8.0 - 15);
            checkTest("2*3+3/2", 2*3.0+3/2.0);
        }
    }

    private String calculate(String s){
        int bal = 0;
        int ind = -1;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == '(')
                bal++;
            if (s.charAt(i) == ')')
                bal--;
            if (bal<0){
                return "Error";
            }
            if (bal == 0 && (s.charAt(i) == '-' || s.charAt(i) == '+')){
                ind = i;
            }
        }
        if (ind>=0){
            return operation(calculate(s.substring(0,ind)), calculate(s.substring(ind+1,s.length())), s.charAt(ind));
        }
        bal = 0;
        ind = -1;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == '(')
                bal++;
            if (s.charAt(i) == ')')
                bal--;
            if (bal<0){
                return "Error";
            }
            if (bal == 0 && (s.charAt(i) == '*' || s.charAt(i) == '/')){
                ind = i;
            }
        }
        if (ind>=0){
            return operation(calculate(s.substring(0,ind)), calculate(s.substring(ind+1,s.length())), s.charAt(ind));
        }

        if (s.length()>0 && s.charAt(0) == '(' && s.charAt(s.length()-1) == ')')
            return calculate(s.substring(1,s.length()-1));
        return s;
    }

    private String operation(String s1, String s2, char oper){
        if ("Error".equals(s1) || "Error".equals(s2))
            return "Error";
        Double d1,d2;
        try{
            if ("".equals(s1) &&(oper == '+' || oper == '-'))
                d1 = 0.0;
            else
                d1 = Double.parseDouble(s1);
            d2 = Double.parseDouble(s2);

            if (oper == '+'){
                d1 = d1 + d2;
            }
            if (oper == '-'){
                d1 = d1 - d2;
            }
            if (oper == '*'){
                d1 = d1 * d2;
            }
            if (oper == '/'){
                if (d2 == 0)
                    return "Error";
                d1 = d1 / d2;
            }
            return  d1.toString();
        } catch (Exception e){
            return "Error";
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TextView textView = (TextView) findViewById(R.id.text);
        s = textView.getText().toString();

        /*TestCase test = new Tests();
        TestResult result = test.run();

        if (!result.wasSuccessful())
        {
            textView.setText("ТЕСТЫ НЕ ПРОЙДЕНЫ");
        }else
        {
            textView.setText("ТЕСТЫ ПРОЙДЕНЫ");
        }  */

        // Next init button and onClickListener

        final Button zero = (Button) findViewById(R.id.zero);
        final Button one = (Button) findViewById(R.id.one);
        final Button two = (Button) findViewById(R.id.two);
        final Button three = (Button) findViewById(R.id.three);
        final Button four = (Button) findViewById(R.id.four);
        final Button five = (Button) findViewById(R.id.five);
        final Button six = (Button) findViewById(R.id.six);
        final Button seven = (Button) findViewById(R.id.seven);
        final Button eight = (Button) findViewById(R.id.eight);
        final Button nine = (Button) findViewById(R.id.nine);

        final Button ravn = (Button) findViewById(R.id.ravn);
        final Button backspace = (Button) findViewById(R.id.backspace);
        final Button delete = (Button) findViewById(R.id.delete);

        final Button plus = (Button) findViewById(R.id.plus);
        final Button min = (Button) findViewById(R.id.min);
        final Button umn = (Button) findViewById(R.id.umn);
        final Button razdel = (Button) findViewById(R.id.razdel);
        final Button opensk = (Button) findViewById(R.id.opensk);
        final Button closesk = (Button) findViewById(R.id.closesk);
        final Button point = (Button) findViewById(R.id.point);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "0";
                textView.setText(s);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "1";
                textView.setText(s);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "2";
                textView.setText(s);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "3";
                textView.setText(s);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "4";
                textView.setText(s);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "5";
                textView.setText(s);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "6";
                textView.setText(s);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "7";
                textView.setText(s);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "8";
                textView.setText(s);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "9";
                textView.setText(s);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "";
                textView.setText(s);
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                if (!("".equals(s)))
                    s = s.substring(0,s.length()-1);
                textView.setText(s);
            }
        });
        ravn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s = calculate(s);
                textView.setText(s);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "+";
                textView.setText(s);
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "-";
                textView.setText(s);
            }
        });
        umn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "*";
                textView.setText(s);
            }
        });
        razdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "/";
                textView.setText(s);
            }
        });
        opensk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += "(";
                textView.setText(s);
            }
        });
        closesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s="";
                s += ")";
                textView.setText(s);
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Error".equals(s))
                    s = "";
                boolean flag = false;
                for (int i = s.length() - 1;i>=0;i--)
                    if (s.charAt(i) == '.')
                        flag = true;
                    else if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*')
                        break;
                if (!flag)
                    s += ".";
                textView.setText(s);
            }
        });
    }

}
