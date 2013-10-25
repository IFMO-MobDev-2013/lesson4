package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MyActivity extends Activity implements View.OnClickListener {


    Button buttons[] = new Button[10];
    int indexButtons[] = new int[10];
    TextView inputtext,outputtext;
    Button plus,minus,div,mult,openBracket,closeBracket,equal,deleteChar,clear,point;


    void init() {
        buttons[0] = (Button) findViewById(R.id.zero);
        buttons[1] = (Button) findViewById(R.id.one);
        buttons[2] = (Button) findViewById(R.id.two);
        buttons[3] = (Button) findViewById(R.id.three);
        buttons[4] = (Button) findViewById(R.id.four);
        buttons[5] = (Button) findViewById(R.id.five);
        buttons[6] = (Button) findViewById(R.id.six);
        buttons[7] = (Button) findViewById(R.id.seven);
        buttons[8] = (Button) findViewById(R.id.eight);
        buttons[9] = (Button) findViewById(R.id.nine);
        inputtext = (TextView)findViewById(R.id.inputtext);
        outputtext = (TextView)findViewById(R.id.outputtext);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        div = (Button)findViewById(R.id.div);
        mult = (Button)findViewById(R.id.mult);
        openBracket = (Button)findViewById(R.id.openBracket);
        closeBracket = (Button)findViewById(R.id.closeBracket);
        equal = (Button)findViewById(R.id.equal);
        deleteChar = (Button)findViewById(R.id.deleteChar);
        clear = (Button)findViewById(R.id.clear);
        point = (Button)findViewById(R.id.point);
        for(int i = 0; i < 10; i++) {
            buttons[i].setOnClickListener(MyActivity.this);
            indexButtons[i] = buttons[i].getId();
        }
        plus.setOnClickListener(MyActivity.this);
        minus.setOnClickListener(MyActivity.this);
        div.setOnClickListener(MyActivity.this);
        mult.setOnClickListener(MyActivity.this);
        openBracket.setOnClickListener(MyActivity.this);
        closeBracket.setOnClickListener(MyActivity.this);
        equal.setOnClickListener(MyActivity.this);
        deleteChar.setOnClickListener(MyActivity.this);
        clear.setOnClickListener(MyActivity.this);
        point.setOnClickListener(MyActivity.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    @Override
    public void onClick(View v) {
        String c = "";
        for(int i = 0; i < 10; i++) {
            if (indexButtons[i] == v.getId()) {
                c = String.valueOf(i);
            }
        }
        if (plus.getId() == v.getId())
            c = "+";
        else if (minus.getId() == v.getId())
            c = "-";
        else if (div.getId() == v.getId())
            c = "/";
        else if (mult.getId() == v.getId())
            c = "*";
        else if (openBracket.getId() == v.getId())
            c = "(";
        else if (closeBracket.getId() == v.getId())
            c = ")";
        else if (point.getId() == v.getId())
            c = ".";
        inputtext.setText(inputtext.getText() + c);
        if (equal.getId() == v.getId()) {
            try {
                double ans = Computation.getRes((String) inputtext.getText());
                String answer = new DecimalFormat("#.########").format(ans);
                outputtext.setText(answer);
            } catch (Exception e) {
                outputtext.setText("ERROR");
            }

        }
        if (deleteChar.getId() == v.getId()) {
            String s = inputtext.getText().toString();
            String newStr = "";
            for(int i = 0; i < s.length() - 1; i++)
                newStr = newStr + s.charAt(i);
            inputtext.setText(newStr);
        }
        if (clear.getId() == v.getId()) {
            inputtext.setText("");
            outputtext.setText("");
        }
    }
}
