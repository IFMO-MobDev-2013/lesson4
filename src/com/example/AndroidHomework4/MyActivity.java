package com.example.AndroidHomework4;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import ru.ifmo.ctd.isaev.CalculatingException;
import ru.ifmo.ctd.isaev.Evaluable;
import ru.ifmo.ctd.isaev.Parser;
import ru.ifmo.ctd.isaev.ParsingException;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    TextView field;
    Parser parser = new Parser();


    Button.OnClickListener addSymbol= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            CharSequence symbol = ((Button) view).getText();
            field.setText(field.getText().toString() + symbol);
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        field = (TextView) findViewById(R.id.textView);
        findViewById(R.id.lbr).setOnClickListener(addSymbol);
        findViewById(R.id.minus).setOnClickListener(addSymbol);
        findViewById(R.id.rbr).setOnClickListener(addSymbol);
        findViewById(R.id.pow).setOnClickListener(addSymbol);
        findViewById(R.id.plus).setOnClickListener(addSymbol);
        findViewById(R.id.ndot).setOnClickListener(addSymbol);
        findViewById(R.id.ndiv).setOnClickListener(addSymbol);
        findViewById(R.id.n9).setOnClickListener(addSymbol);
        findViewById(R.id.n8).setOnClickListener(addSymbol);
        findViewById(R.id.n7).setOnClickListener(addSymbol);
        findViewById(R.id.n6).setOnClickListener(addSymbol);
        findViewById(R.id.n5).setOnClickListener(addSymbol);
        findViewById(R.id.n4).setOnClickListener(addSymbol);
        findViewById(R.id.n3).setOnClickListener(addSymbol);
        findViewById(R.id.n2).setOnClickListener(addSymbol);
        findViewById(R.id.n1).setOnClickListener(addSymbol);
        findViewById(R.id.n0).setOnClickListener(addSymbol);
        findViewById(R.id.mul).setOnClickListener(addSymbol);

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence exp = field.getText();
                field.setText(exp.subSequence(0,exp.length()-1));
            }
        });
        findViewById(R.id.clearall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               field.setText("");
            }
        });
        findViewById(R.id.eval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Evaluable e = parser.parse(String.valueOf(field.getText()));
                    Double result = e.evaluate(null);
                    field.setText(Double.toString(result));
                } catch (Exception e) {
                      field.setText("incorrect input");
                    Log.e("olol","olol",e);
                }
            }
        });
    }
}
