package com.example.h4_calculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.h4_calculator.PollandParcer.evaluate;
import static com.example.h4_calculator.PollandParcer.makeSpaces;
import static com.example.h4_calculator.PollandParcer.parse;

public class MyActivity extends Activity implements OnClickListener {
    /**
     * Called when the activity is first created.
     */

    TextView textView;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn000;
    Button btnOpen,btnClear,btnClose,btnResult,btnEraseSymbol;
    Button btnPow,btnDiv,btnMul,btnSub,btnSum;
    Button btnPoint;

    public void initializeViews(){
        textView = (TextView) findViewById(R.id.textView);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn000 = (Button) findViewById(R.id.btn000);
        btnOpen = (Button) findViewById(R.id.btnOpen);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnEraseSymbol = (Button) findViewById(R.id.btnEraseSymbol);
        btnPow = (Button) findViewById(R.id.btnPow);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn000.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        btnSum.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnPow.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnEraseSymbol.setOnClickListener(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeViews();
    }
    public void onClick(View v) {
         // TODO Auto-generated method stub
        String current = textView.getText().toString();
        String buttonText = ((Button)v).getText().toString();
        switch (v.getId()){

            case (R.id.btnPoint):
                if (current.charAt(current.length() - 1) != '.')
                    textView.setText(current + buttonText);
                break;
            case (R.id.btnClear) :
                textView.setText("");
                break;
            case (R.id.btnEraseSymbol) :
                current.equals("");
                while (!(current.equals(""))&&(current.charAt(current.length() - 1) == ' '))
                    current = current.substring(0,current.length() - 1);
                if (!current.equals(""))
                    textView.setText(current.substring(0,current.length() - 1));
                while (!(current.equals(""))&&(current.charAt(current.length() - 1) == ' '))
                    current = current.substring(0,current.length() - 1);
                break;
            case (R.id.btnResult) :
                try
                {
                    String res = evaluate(parse(makeSpaces(current)));
                    if ((res.length() > 2)&&(res.charAt(res.length() - 2) == '.')&&(res.charAt(res.length() - 1) == '0'))
                        textView.setText(res.substring(0,res.length() - 2));
                    else
                        textView.setText(res);
                }
                catch(Exception e)
                {
                    textView.setText("");
                    Toast.makeText(this, "Введите, пожалуйста, корректное выражение", Toast.LENGTH_LONG).show();
                }
                break;
            case (R.id.btnSum):
                textView.setText(current + " " + buttonText + " ");
                break;
            case (R.id.btnSub):
                textView.setText(current + " " + buttonText + " ");
                break;
            case (R.id.btnPow):
                textView.setText(current + " " + buttonText + " ");
                break;
            case (R.id.btnMul):
                textView.setText(current + " " + buttonText + " ");
                break;
            case (R.id.btnDiv):
                textView.setText(current + " " + buttonText + " ");
                break;
            case (R.id.btnClose):
                int n = 0;
                for (int i = 0; i < current.length(); i++)
                {
                    if (current.charAt(i) == '(')
                        n++;
                    if (current.charAt(i) == ')')
                        n--;
                }
                if (n > 0)
                    textView.setText(current + buttonText);
                break;
            default:
                 textView.setText(current + buttonText);
                 break;
        }
    }
}
