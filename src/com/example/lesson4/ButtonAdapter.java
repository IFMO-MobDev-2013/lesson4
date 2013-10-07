package com.example.lesson4;

import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: satori
 * Date: 10/7/13
 * Time: 7:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ButtonAdapter extends BaseAdapter {
    private Context mc;
    private int buttonHeight;
    private TextView inputField;
    public ButtonAdapter(Context c, TextView field, int height) {
        buttonHeight = height;
        inputField = field;
        mc = c;
    }
    @Override
    public long getItemId (int position) {
        return 0;
    }
    @Override
    public int getCount() {
        return labels.length * 4;

    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public Button getView(int position, View convertView,ViewGroup parent) {
        Button b;
        int x = position / 4;
        int y = position % 4;
        if (convertView == null) {
            b = new Button(mc);
            b.setText(labels[x][y]);
            //
            //b.setMinHeight(buttonHeight);
            setListener(b, labels[x][y]);

        }
        else {
            b = (Button)convertView;
        }
        b.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.FILL_PARENT, buttonHeight ));
        return b;
    }
    private void setListener(Button b, final String label) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.append(label);
            }
        });

    }
    private final String[][] labels = {
            {"1", "2", "3", "*"},
            {"4", "5", "6", "/"},
            {"7", "8", "9", "+"},
            {"(", "0", ")", "-"}

    };
}
