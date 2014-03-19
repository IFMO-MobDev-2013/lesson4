package com.example.Calculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity {

    private String expr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        expr = "";

        final TextView textView = (TextView) findViewById(R.id.textView);

        Button button0 = (Button) findViewById(R.id.Button_0);
        Button button1 = (Button) findViewById(R.id.Button_1);
        Button button2 = (Button) findViewById(R.id.Button_2);
        Button button3 = (Button) findViewById(R.id.Button_3);
        Button button4 = (Button) findViewById(R.id.Button_4);
        Button button5 = (Button) findViewById(R.id.Button_5);
        Button button6 = (Button) findViewById(R.id.Button_6);
        Button button7 = (Button) findViewById(R.id.Button_7);
        Button button8 = (Button) findViewById(R.id.Button_8);
        Button button9 = (Button) findViewById(R.id.Button_9);
        Button buttonSum = (Button) findViewById(R.id.Button_sum);
        Button buttonMul = (Button) findViewById(R.id.Button_mul);
        Button buttonDiv = (Button) findViewById(R.id.Button_div);
        Button buttonSub = (Button) findViewById(R.id.Button_sub);
        Button buttonC = (Button) findViewById(R.id.Button_C);
        Button buttonRight = (Button) findViewById(R.id.Button_right);
        Button buttonLeft = (Button) findViewById(R.id.Button_left);
        Button buttonEqually = (Button) findViewById(R.id.Button_equally);
        Button buttonPoint = (Button) findViewById(R.id.Button_point);
        Button buttonDel = (Button) findViewById(R.id.Button_del);

        button0.setTextSize(50);
        button1.setTextSize(50);
        button2.setTextSize(50);
        button3.setTextSize(50);
        button4.setTextSize(50);
        button5.setTextSize(50);
        button6.setTextSize(50);
        button7.setTextSize(50);
        button8.setTextSize(50);
        button9.setTextSize(50);
        buttonSum.setTextSize(50);
        buttonMul.setTextSize(50);
        buttonDiv.setTextSize(50);
        buttonSub.setTextSize(50);
        buttonPoint.setTextSize(50);
        buttonEqually.setTextSize(50);
        buttonDel.setTextSize(50);
        buttonC.setTextSize(50);
        buttonLeft.setTextSize(50);
        buttonRight.setTextSize(50);





        textView.setTextSize(60);

        button0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '0';
                textView.setText(expr);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '1';
                textView.setText(expr);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '2';
                textView.setText(expr);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '3';
                textView.setText(expr);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '4';
                textView.setText(expr);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '5';
                textView.setText(expr);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '6';
                textView.setText(expr);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '7';
                textView.setText(expr);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '8';
                textView.setText(expr);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '9';
                textView.setText(expr);
            }
        });

        buttonSum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '+';
                textView.setText(expr);
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '*';
                textView.setText(expr);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '-';
                textView.setText(expr);
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '/';
                textView.setText(expr);
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '(';
                textView.setText(expr);
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += ')';
                textView.setText(expr);
            }
        });
        buttonPoint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr += '.';
                textView.setText(expr);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                expr = "";
                textView.setText(expr);
            }
        });
        buttonEqually.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    String res = new Calculator().getValue(expr);
                    textView.setText(res);
                } catch (Exception e) {
                    textView.setText(e.getMessage());
                }
                expr = "";
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (expr.length() > 0) {
                    expr = expr.substring(0, expr.length() - 1);
                }
                textView.setText(expr);
            }
        });


    }
}
