package com.example.lesson4;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class CalcActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private GridView gridView;
    private ButtonAdapter adapter;
    private DisplayMetrics metrics;
    private Button clear;
    private Button clearAll;
    private TextView inputField;
    private Button equalButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gridView = (GridView)findViewById(R.id.buttonsGrid);
        clear = (Button)findViewById(R.id.clear);
        inputField = (TextView)findViewById(R.id.inputField);
        equalButton = (Button)findViewById(R.id.equals);
        clearAll = (Button)findViewById(R.id.clearAll);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = inputField.getText().toString();
                if (s.length() > 0) {
                    inputField.setText(s.substring(0, s.length() - 1));
                }
            }
        });
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText("");
            }
        });
        int t_height = metrics.heightPixels;
        adapter = new ButtonAdapter(this, inputField, t_height / 6);
        gridView.setAdapter(adapter);

    }
}
