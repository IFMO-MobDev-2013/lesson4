package com.example.Les4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.Les4.ParserExpression.Parser;

public class MyActivity extends Activity {

	TextView textExpression,textAnswer;
	boolean flag;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textExpression = (TextView) findViewById(R.id.textView);
		textAnswer = (TextView) findViewById(R.id.textView2);

	}

	public void onClick(View v){

		if (flag) {
			textExpression.setText(null);
			textAnswer.setText(null);
			flag = false;
		}

		switch (v.getId()) {
			case R.id.buttonOne:
				textExpression.append("1");
				break;
			case R.id.buttonTwo:
				textExpression.append("2");
				break;
			case R.id.buttonThree:
				textExpression.append("3");
				break;
			case R.id.buttonFour:
				textExpression.append("4");
				break;
			case R.id.buttonFive:
				textExpression.append("5");
				break;
			case R.id.buttonSix:
				textExpression.append("6");
				break;
			case R.id.buttonSeven:
				textExpression.append("7");
				break;
			case R.id.buttonEight:
				textExpression.append("8");
				break;
			case R.id.buttonNine:
				textExpression.append("9");
				break;
			case R.id.buttonZero:
				textExpression.append("0");
				break;
			case R.id.buttonPlus:
				textExpression.append("+");
				break;
			case R.id.buttonMinus:
				textExpression.append("-");
				break;
			case R.id.buttonTimes:
				textExpression.append("*");
				break;
			case R.id.buttonDivision:
				textExpression.append("/");
				break;
			case R.id.buttonOpen:
				textExpression.append("(");
				break;
			case R.id.buttonClose:
				textExpression.append(")");
				break;
			case R.id.buttonPoint:
				textExpression.append(".");
				break;
			case R.id.buttonClearAll:
				textExpression.setText(null);
				textAnswer.setText(null);
				break;
			case R.id.buttonClear:
				String s = textExpression.getText().toString();
				textExpression.setText((s.length() == 0) ? null : s.substring(0, s.length() - 1));
				break;
			case R.id.buttonEqually:
				try {
					textAnswer.append("= " +  Parser(textExpression.getText().toString()).evaluate());
				} catch (ExceptionDivisionByZero exceptionDivisionByZero) {
					textAnswer.setText("Division by Zero");
				} catch (ExceptionWrongExpression exceptionWrongExpression) {
					textAnswer.setText("Wrong Data");
				}
				flag = true;
				break;
		}

	}
}
