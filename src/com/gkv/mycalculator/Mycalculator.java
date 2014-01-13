package com.gkv.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Mycalculator extends Activity implements OnClickListener {

	/**
	 * Called when the activity is first created.
	 */
	private static final String TAG = "myLogs";
	TextView textView1, textView0;
	Button zero, one, two, three, four, five, six, seven, eight, nine, add,
			subtract, multiply, division, allClear, equals, point, delete,
			minus, leftBracket, rightBracket;
	String acceptedChar = "+-*/";
	CharSequence ViewChar;
	Character c;
	int bracketCnt = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycalculator);
		init();
	}

	public void onClick(View view) {
		Button b = (Button) view;
        ViewChar = textView1.getText();

		c = (ViewChar.length() > 0) ? ViewChar.charAt(ViewChar.length() - 1)
				: null;
        if(ViewChar.length() > 1){
            if(ViewChar.charAt(1) == 'ш'){
                Log.d(TAG, "after ILL");
                textView1.setText("");
                ViewChar = "";
                c = null;
            }
        }

        if (R.id.delete == view.getId()) {
			if (!(c == null)) textView1.setText(ViewChar.subSequence(0, ViewChar.length() - 1));
		} else if (R.id.allClear == view.getId()) {
			textView1.setText("");
		} else if (R.id.leftBracket == view.getId()) {
			bracketCnt++;
			textView1.append(b.getText());
		} else if (R.id.rightBracket == view.getId()) {
			if (bracketCnt > 0) {
				bracketCnt--;
				textView1.append(b.getText());
			}
            /*else{
                Toast.makeText(this, "Скобка не открыта!", Toast.LENGTH_LONG).show();
            } */
		} else if (R.id.multiply == view.getId()) {
			if (!(c == null)) {
				if (acceptedChar.contains(c.toString())) {
					textView1.setText(ViewChar.subSequence(0, ViewChar.length() - 1));
					textView1.append(b.getText());
				} else
					textView1.append(b.getText());
			} else
				textView1.append(b.getText());
		} else if (R.id.division == view.getId()) {
			if (!(c == null)) {
				if (acceptedChar.contains(c.toString())) {
					textView1.setText(ViewChar.subSequence(0,
							ViewChar.length() - 1));
					textView1.append(b.getText());
				} else
					textView1.append(b.getText());
			} else
				textView1.append(b.getText());
		} else if (R.id.add == view.getId()) {
			if (!(c == null)) {
				if (acceptedChar.contains(c.toString())) {
					textView1.setText(ViewChar.subSequence(0,
							ViewChar.length() - 1));
					textView1.append(b.getText());
				} else
					textView1.append(b.getText());
			} else
				textView1.append(b.getText());
		} else if (R.id.subtract == view.getId()) {
			if (!(c == null)) {
				if (acceptedChar.contains(c.toString())) {
					textView1.setText(ViewChar.subSequence(0,
							ViewChar.length() - 1));
					textView1.append(b.getText());
				} else
					textView1.append(b.getText());
			} else
				textView1.append(b.getText());
		} else if (R.id.equal == view.getId()) {
			/*if(bracketCnt != 0){
                Toast.makeText(this, "Скобка не закрыто!", Toast.LENGTH_LONG).show();
                return;
            } */
            if(ViewChar.length() != 0){
                bracketCnt = 0;
                CharSequence cs = new Calculate().solve(textView1.getText());
                textView1.setText(cs);
            }
		} else
			textView1.append(b.getText());

	}

	void init() {
		textView0 = (TextView) findViewById(R.id.textView0);
		textView1 = (TextView) findViewById(R.id.textView1);
		allClear = (Button) findViewById(R.id.allClear);
		allClear.setOnClickListener(this);
		leftBracket = (Button) findViewById(R.id.leftBracket);
		leftBracket.setOnClickListener(this);
		rightBracket = (Button) findViewById(R.id.rightBracket);
		rightBracket.setOnClickListener(this);
		delete = (Button) findViewById(R.id.delete);
		delete.setOnClickListener(this);
		seven = (Button) findViewById(R.id.seven);
		seven.setOnClickListener(this);
		eight = (Button) findViewById(R.id.eight);
		eight.setOnClickListener(this);
		nine = (Button) findViewById(R.id.nine);
		nine.setOnClickListener(this);
		division = (Button) findViewById(R.id.division);
		division.setOnClickListener(this);
		four = (Button) findViewById(R.id.four);
		four.setOnClickListener(this);
		five = (Button) findViewById(R.id.five);
		five.setOnClickListener(this);
		six = (Button) findViewById(R.id.six);
		six.setOnClickListener(this);
		multiply = (Button) findViewById(R.id.multiply);
		multiply.setOnClickListener(this);
		one = (Button) findViewById(R.id.one);
		one.setOnClickListener(this);
		two = (Button) findViewById(R.id.two);
		two.setOnClickListener(this);
		three = (Button) findViewById(R.id.three);
		three.setOnClickListener(this);
		subtract = (Button) findViewById(R.id.subtract);
		subtract.setOnClickListener(this);
		point = (Button) findViewById(R.id.point);
		point.setOnClickListener(this);
		zero = (Button) findViewById(R.id.zero);
		zero.setOnClickListener(this);
		equals = (Button) findViewById(R.id.equal);
		equals.setOnClickListener(this);
		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(this);
	}

}