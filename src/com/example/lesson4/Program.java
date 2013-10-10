package com.example.lesson4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Program extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setText("");
        assertTrue();
    }

    public void setText(String string)
    {
        TextView tv = (TextView)findViewById(R.id.text_field);
        tv.setText(string);
    }

    public String getText()
    {
        TextView tv = (TextView)findViewById(R.id.text_field);
        return String.valueOf(tv.getText());
    }

    public void appendText(String string)
    {
        setText(getText()+string);
    }

    public void calculate()
    {
        String query = getText();
        ExpressionsParser ep = new ExpressionsParser();
        try {
            Evaluator exp = ep.parse(query);
            Console.print(exp.toString());
            HashMap<String, Double> table = new HashMap<String, Double>();
            table.put("PI", Math.PI);
            table.put("E", Math.E);
            double result = exp.evaluate(table);
            Console.print(result+"");
            setText(result+"");
            ((TextView)findViewById(R.id.text_info)).setText("");
        } catch (Exception e) {
            Console.print(e.toString());
            ((TextView)findViewById(R.id.text_info)).setText("Error");
        }
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_calculate:
            {
                calculate();
            } break;
            case R.id.button_undo:
            {
                String t = getText();
                if (t.length() > 0)
                {
                    t = t.substring(0, t.length() - 1);
                    setText(t);
                }
            } break;
            case R.id.button0:
            {
                appendText("0");
            } break;
            case R.id.button1:
            {
                appendText("1");
            } break;
            case R.id.button2:
            {
                appendText("2");
            } break;
            case R.id.button3:
            {
                appendText("3");
            } break;
            case R.id.button4:
            {
                appendText("4");
            } break;
            case R.id.button5:
            {
                appendText("5");
            } break;
            case R.id.button6:
            {
                appendText("6");
            } break;
            case R.id.button7:
            {
                appendText("7");
            } break;
            case R.id.button8:
            {
                appendText("8");
            } break;
            case R.id.button9:
            {
                appendText("9");
            } break;
            case R.id.button_plus:
            {
                appendText("+");
            } break;
            case R.id.button_minus:
            {
                appendText("-");
            } break;
            case R.id.button_multiply:
            {
                appendText("*");
            } break;
            case R.id.button_divide:
            {
                appendText("/");
            } break;
            case R.id.button_open:
            {
                appendText("(");
            } break;
            case R.id.button_close:
            {
                appendText(")");
            } break;
            case R.id.button_dot:
            {
                appendText(".");
            } break;
            case R.id.button_comma:
            {
                appendText(",");
            } break;
            case R.id.button_clear:
            {
                setText("");
            } break;
            case R.id.button_sin:
            {
                appendText("sin(");
            } break;
            case R.id.button_cos:
            {
                appendText("cos(");
            } break;
            case R.id.button_tg:
            {
                appendText("tg(");
            } break;
            case R.id.button_ln:
            {
                appendText("ln(");
            } break;
            case R.id.button_log:
            {
                appendText("log(");
            } break;
            case R.id.button_sqrt:
            {
                appendText("sqrt(");
            } break;
            case R.id.button_pow:
            {
                appendText("^");
            } break;
            case R.id.button_pi:
            {
                appendText("PI");
            } break;
            case R.id.button_e:
            {
                appendText("E");
            } break;
            case R.id.button_help:
            {
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
            } break;
        }
    }
}
