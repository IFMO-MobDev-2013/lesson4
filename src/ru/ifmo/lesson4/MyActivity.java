package ru.ifmo.lesson4;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    final static int ENTERING = 0;
    final static int CALCULATED = 1;
    final static int ERROR = -1;

    int statue = 0;
    TextView expText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        expText = (TextView) findViewById(R.id.expText);

        TestCase test = new CalcTest();
        TestResult result = test.run();

        if (!result.wasSuccessful()){
            expText.setText(
                    result.failures().nextElement().exceptionMessage());
            expText.setTextColor(0xFFBF00FF);
            expText.setActivated(false);
            String s = "";
            expText.setText(s.charAt(-1));
        }



        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonMul = (Button) findViewById(R.id.buttonMul);
        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        Button buttonEqu = (Button) findViewById(R.id.buttonEqu);
        Button buttonDel = (Button) findViewById(R.id.buttonDel);
        Button buttonErase = (Button) findViewById(R.id.buttonErase);
        Button buttonBr = (Button) findViewById(R.id.buttonBr);


        buttonDel.setText("<-");

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("9");
            }
        });


        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd(".");
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("+");
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("-");
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("x");
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSymbolAtEnd("/");
            }
        });
        buttonEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    enterText(Calculator.evaluate(expText.getText().toString())+"");
                    setStatue(CALCULATED);
                    int a = 5;
                } catch (Exception ex){
                    setStatue(ERROR);
                }

            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSymbolAtEnd();
            }
        });
        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllSymbols();
            }
        });
        buttonBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterBracketAtEnd();
            }
        });



    }

    void enterSymbolAtEnd(String s){
        String text = expText.getText().toString();
        if (statue == CALCULATED && (s.charAt(0) <= '9' && s.charAt(0) >= '0' || s == "(" || s == ")" || s == ".")){
            text = "";
        }
        if (s == "+" || s == "-" || s == "x" || s == "/"){
            if (text.isEmpty()){

            } else {
                char c = text.charAt(text.length() - 1);

                if (c >= '0' && c <= '9' || c == ')'){
                    s = " " + s + " ";
                } else {

                }
            }
        } else if (s == "."){
            if (text.isEmpty() || text.charAt(text.length() - 1) > '9' || text.charAt(text.length() - 1) < '0'){
                s = '0' + s;
            }
        }

        expText.setText(text + s);
        setStatue(ENTERING);
    }
    void deleteSymbolAtEnd(){
        String text = expText.getText().toString();
        if (text.isEmpty()) return;
        char c0 = text.charAt(text.length() - 1);
        String s = expText.getText().toString().substring(0, text.length() - 1);
        if (!s.isEmpty() && c0 == ' '){
            char c = s.charAt(s.length() - 1);
            if ((c == '+' || c == '-' || c == 'x' || c == '/') && s.length() > 1){
                if (s.charAt(s.length() - 2) == ' '){
                    s = s.substring(0, s.length() - 2);
                } else {
                    s = s.substring(0, s.length() - 1);
                }
            }
        }
        expText.setText(s);
        setStatue(ENTERING);
    }
    void deleteAllSymbols(){
        expText.setText("");
        setStatue(ENTERING);
    }
    void enterBracketAtEnd(){
        String s = expText.getText().toString();
        if (statue == CALCULATED){
            s = "";
        }

        if (s.isEmpty()){
            s += '(';
        } else {
            char last = s.charAt(s.length() - 1);
            if (last == '+' || last == '-' || last == 'x' || last == '/' || last == '(' || last == ' '){
                s += '(';
            } else {
                s += ')';
            }
        }
        expText.setText(s);
        setStatue(ENTERING);
    }
    void enterText(String s){
        expText.setText(s);
    }

    void setStatue(int a){
        if (a == 0){
            expText.setTextColor(0xFFA0B0B0);
        } else if (a == 1){
            expText.setTextColor(0xFF0000FF);
        } else if (a == -1){
            expText.setTextColor(0xFFFF0000);
        }
        statue = a;
    }
}
