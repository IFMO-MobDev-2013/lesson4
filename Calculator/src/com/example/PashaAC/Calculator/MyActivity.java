package com.example.PashaAC.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
    public String normalString(String s) {
        while (s.charAt(s.length() - 1) == '0')
            s = s.substring(0, s.length() - 1);
        if (s.charAt(s.length() - 1) == '.')
            s = s.substring(0, s.length() - 1);
        if (s.equals("0E-3"))
            return "0";
        return s;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button point = (Button) findViewById(R.id.button_point);
        point.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + ".");
            }
        });

        Button one = (Button) findViewById(R.id.button_1);
        one.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "1");
            }
        });

        Button two = (Button) findViewById(R.id.button_2);
        two.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "2");
            }
        });

        Button three = (Button) findViewById(R.id.button_3);
        three.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "3");
            }
        });

        Button four = (Button) findViewById(R.id.button_4);
        four.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "4");
            }
        });

        Button five = (Button) findViewById(R.id.button_5);
        five.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "5");
            }
        });

        Button six = (Button) findViewById(R.id.button_6);
        six.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "6");
            }
        });

        Button seven = (Button) findViewById(R.id.button_7);
        seven.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "7");
            }
        });

        Button eight = (Button) findViewById(R.id.button_8);
        eight.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "8");
            }
        });

        Button nine = (Button) findViewById(R.id.button_9);
        nine.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "9");
            }
        });

        Button zero = (Button) findViewById(R.id.button_0);
        zero.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "0");
            }
        });

        Button clearAll = (Button) findViewById(R.id.button_clear_all);
        clearAll.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText("");
            }
        });

        Button delOneChar = (Button) findViewById(R.id.button_del);
        delOneChar.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                if (expr.getText().toString().length() > 1) {
                    expr.setText(expr.getText().toString().substring(0, expr.getText().toString().length() - 1));
                }
                else {
                    expr.setText("");
                }
            }
        });

        Button closeBracket = (Button) findViewById(R.id.button_close_bracket);
        closeBracket.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + ")");
            }
        });

        Button openBracket = (Button) findViewById(R.id.button_open_bracket);
        openBracket.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "(");
            }
        });

        Button multiply = (Button) findViewById(R.id.button_mul);
        multiply.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "*");
            }
        });

        Button division = (Button) findViewById(R.id.button_div);
        division.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "/");
            }
        });

        Button plus = (Button) findViewById(R.id.button_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "+");
            }
        });

        Button minus = (Button) findViewById(R.id.button_min);
        minus.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            @Override
            public void onClick(View v) {
                expr.setText(expr.getText().toString() + "-");
            }
        });

        Button go = (Button) findViewById(R.id.button_equals);
        go.setOnClickListener(new View.OnClickListener() {
            TextView expr = (TextView) findViewById(R.id.text);
            ExpressionMathParser myParser = new ExpressionMathParser();
            @Override
            public void onClick(View v) {
                try {
                   expr.setText(normalString(myParser.ExpressionMathParser(expr.getText().toString()).evaluate().toString()));
                } catch(Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage().toString(), 4000);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });
    }
}
