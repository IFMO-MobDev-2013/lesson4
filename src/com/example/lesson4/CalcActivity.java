package com.example.lesson4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;


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
    private Button dotButton;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        gridView = (GridView)findViewById(R.id.buttonsGrid);
        clear = (Button)findViewById(R.id.clear);
        dotButton = (Button)findViewById(R.id.dotButton);
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
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.append(".");
            }
        });
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = inputField.getText().toString();
                try {
                inputField.setText(Double.toString(com.example.lesson4.Parser.parse(s)));
                }
                catch (Exception e) {
                    inputField.setText("invalid input");
                }
            }
        });
        //gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                int x = position / 4;
//                int y = position % 4;
//                inputField.append(ButtonAdapter.labels[x][y]);
//                Toast.makeText(context, "label" + ButtonAdapter.labels[x][y], 10);
//            }
//        });
        int t_height = metrics.heightPixels;
        adapter = new ButtonAdapter(this, inputField, t_height / 6);
        gridView.setAdapter(adapter);

    }
}
