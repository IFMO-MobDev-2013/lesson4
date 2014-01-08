package local.firespace.Calc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {
	private EditText field;
	private Vibrator inputError;

	private boolean isSetDot = false;
	private int countOpeningBrackets = 0;
	private int countClosingBrackets = 0;
	private boolean isLock = false;
	
	public static final long ERROR_VIBRATION_TIME = 500;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		field = (EditText)findViewById(R.id.editText);
		setDefaultField();
		inputError = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	}

	@SuppressWarnings("ConstantConditions")
	private void writeChar(char c) { field.setText(field.getText().toString() + c); }
	private void setChar(Character c) { field.setText(c.toString()); }
	private void setDefaultField() {
		field.setText("0");
		isSetDot = false;
		isLock = false;
		countClosingBrackets = 0;
		countOpeningBrackets = 0;
	}
	@SuppressWarnings("ConstantConditions")
	private boolean isDefaultField() { return field.getText().toString().equals("0"); }
	private boolean isCharOperator(char c) { return c == '+' || c == '-' || c == '*' || c == '/'; }
	private boolean isNextBracketCanBeClosing() { return countOpeningBrackets > countClosingBrackets; }

	@SuppressWarnings("UnusedParameters") public void one(View view)   { if (isLock) return; if (isDefaultField()) setChar('1'); else writeChar('1'); }
	@SuppressWarnings("UnusedParameters") public void two(View view)   { if (isLock) return; if (isDefaultField()) setChar('2'); else writeChar('2'); }
	@SuppressWarnings("UnusedParameters") public void three(View view) { if (isLock) return; if (isDefaultField()) setChar('3'); else writeChar('3'); }
	@SuppressWarnings("UnusedParameters") public void four(View view)  { if (isLock) return; if (isDefaultField()) setChar('4'); else writeChar('4'); }
	@SuppressWarnings("UnusedParameters") public void five(View view)  { if (isLock) return; if (isDefaultField()) setChar('5'); else writeChar('5'); }
	@SuppressWarnings("UnusedParameters") public void six(View view)   { if (isLock) return; if (isDefaultField()) setChar('6'); else writeChar('6'); }
	@SuppressWarnings("UnusedParameters") public void seven(View view) { if (isLock) return; if (isDefaultField()) setChar('7'); else writeChar('7'); }
	@SuppressWarnings("UnusedParameters") public void eight(View view) { if (isLock) return; if (isDefaultField()) setChar('8'); else writeChar('8'); }
	@SuppressWarnings("UnusedParameters") public void nine(View view)  { if (isLock) return; if (isDefaultField()) setChar('9'); else writeChar('9'); }
	@SuppressWarnings("UnusedParameters") public void zero(View view)  { if (isLock) return; if (isDefaultField()) setChar('0'); else writeChar('0'); }

	@SuppressWarnings("UnusedParameters")
	public void dot(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		if (!isSetDot && Character.isDigit(temp.charAt(temp.length()-1))) {
			isSetDot = true;
			writeChar('.');
		} else { inputError.vibrate(ERROR_VIBRATION_TIME); }
	}

	@SuppressWarnings("UnusedParameters")
	public void plus(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (Character.isDigit(lastChar) || lastChar == ')') { writeChar('+'); }
		else if (lastChar == '.') { field.setText(temp.substring(0, temp.length()-1) + '+'); }
		else { inputError.vibrate(ERROR_VIBRATION_TIME); }
	}

	@SuppressWarnings("UnusedParameters")
	public void minus(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (Character.isDigit(lastChar) || lastChar == ')') { writeChar('-'); }
		else if (lastChar == '.') { field.setText(temp.substring(0, temp.length()-1) + '-'); }
		else { inputError.vibrate(ERROR_VIBRATION_TIME); }
	}

	@SuppressWarnings("UnusedParameters")
	public void multiply(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (Character.isDigit(lastChar) || lastChar == ')') { writeChar('*'); }
		else if (lastChar == '.') { field.setText(temp.substring(0, temp.length()-1) + '*'); }
		else { inputError.vibrate(ERROR_VIBRATION_TIME); }
	}

	@SuppressWarnings("UnusedParameters")
	public void div(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (Character.isDigit(lastChar) || lastChar == ')') { writeChar('/'); }
		else if (lastChar == '.') { field.setText(temp.substring(0, temp.length()-1) + '/'); }
		else { inputError.vibrate(ERROR_VIBRATION_TIME); }
	}

	@SuppressWarnings("UnusedParameters")
	public void brackets(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (isCharOperator(lastChar) || lastChar == '(') {
			countOpeningBrackets++;
			writeChar('(');
		} else if (isDefaultField()) {
			countOpeningBrackets++;
			setChar('(');
		} else if (isNextBracketCanBeClosing()) {
			if (Character.isDigit(lastChar) || lastChar == '.' || lastChar == ')') {
				countClosingBrackets++;
				writeChar(')');
			} else {
				inputError.vibrate(ERROR_VIBRATION_TIME);
			}
		}
	}

	@SuppressWarnings("UnusedParameters")
	public void clear(View view) {
		setDefaultField();
	}

	@SuppressWarnings("UnusedParameters")
	public void erase(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (!isDefaultField()) {
			if (lastChar == '.') { isSetDot = false; }
			else if (lastChar == '(') { countOpeningBrackets--; }
			else if (lastChar == ')') { countClosingBrackets--; }
			field.setText(temp.substring(0, temp.length()-1));
		}
	}

	@SuppressWarnings("UnusedParameters")
	public void sign(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (lastChar == ')') {
			int i = temp.length()-2;
			int countPairBrackets = 1;
			 do {
				if (temp.charAt(i) == '(')
					countPairBrackets--;
				if (temp.charAt(i) == ')')
					countPairBrackets++;
				i--;
			} while (countPairBrackets != 0);
			i++;

			field.setText(temp.substring(0, i) + "(-" + temp.substring(i, temp.length()) + ')');
			countClosingBrackets++;
			countOpeningBrackets++;
		} else if (Character.isDigit(lastChar) || lastChar == '.') {
			int i = temp.length()-1;
			while ((i >= 0) && (Character.isDigit(temp.charAt(i)) || temp.charAt(i) == '.')) { i--; }
			i++;

			field.setText(temp.substring(0, i) + "(-" + temp.substring(i, temp.length()) + ')');
			countClosingBrackets++;
			countOpeningBrackets++;
		}
	}

	@SuppressWarnings("UnusedParameters")
	public void equal(View view) {
		if (isLock) return;
		@SuppressWarnings("ConstantConditions") String temp = field.getText().toString();
		char lastChar = temp.charAt(temp.length()-1);
		if (countOpeningBrackets == countClosingBrackets && (Character.isDigit(lastChar) || lastChar == ')' || lastChar == '.')) {
			try {
				String result = (lastChar == '.') ? Calculator.calculate(temp.substring(0, temp.length()-1)) : Calculator.calculate(temp);
				setDefaultField();
				field.setText(result);
			} catch (CalculateException e) {
				setDefaultField();
				field.setText(e.getMessage());
				isLock = true;
			}
		} else { inputError.vibrate(ERROR_VIBRATION_TIME); }
	}
}
