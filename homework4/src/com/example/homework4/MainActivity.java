package com.example.homework4;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import junit.framework.*;

public class MainActivity extends Activity {
    String s;
    String forTest;
    Button zero; 
	Button one; 
	Button two;
	Button three;
	Button four;
	Button five;
	Button six; 
	Button seven;
	Button eight; 
	Button nine; 
	Button clear;
	Button clear_all;
	Button division;
	Button equal;
	Button minus;
	Button plus;
	Button mul;
	EditText text;	
	Button open_bracket;
	Button close_bracket;
	Button point;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		all_button();
		all_onClickListener();
		TestCase MyTest = new CalcTest();
		TestResult res = MyTest.run();
		
		
	}
	public double plus(int l, int r) {
        int ans = 0;
              
        boolean check = true;
        for (int i = l; i <= r; i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '(') {
                check = false;
                break;
            }
        }
        if (check) {
            String s1 = "";
            for (int i = l; i <= r; i++) {
                s1 = s1 + s.charAt(i);
            }
            return Double.parseDouble(s1);
        }
        for (int i = r; l <= i; i--) {
            if (s.charAt(i) == '(') {
                ans++;
            } else {
                if (s.charAt(i) == ')') {
                    ans--;
                } else {
                    if (s.charAt(i) == '-' && ans == 0) {
                        return plus(l, i - 1) - plus(i + 1, r);
                    } else {
                        if (s.charAt(i) == '+' && ans == 0) {
                            return plus(l, i - 1) + plus(i + 1, r);
                        }
                    }
                }
            }
        }
        return mul(l, r);
    }

    public double mul(int l, int r) {
        int ans = 0;
        boolean check = true;

        for (int i = r; l <= i; i--) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '(') {
                check = false;
                break;
            }
        }
        if (check) {
            String s1 = "";
            for (int i = l; i <= r; i++) {
                s1 = s1 + s.charAt(i);
            }
            return Double.parseDouble(s1);

        }
        for (int i = r; l <= i; i--) {
            if (s.charAt(i) == ')') {
                ans--;
            } else {
                if (s.charAt(i) == '(') {
                    ans++;
                } else {
                    if (s.charAt(i) == '/' && ans == 0) {
                        return plus(l, i - 1) / plus(i + 1, r);
                    } else {
                        if (s.charAt(i) == '*' && ans == 0) {
                            return plus(l, i - 1) * plus(i + 1, r);
                        }
                    }
                }
            }


        }
        return plus(l + 1, r - 1);

    }

    public String change() {
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '-' || s.charAt(i) == '+') && (i == 0 || s.charAt(i - 1) == '(')) {
                s1 = s1 + '0' + s.charAt(i);
            } else {
                s1 = s1 + s.charAt(i);
            }
        }
        return s1;
    }
public void all_button(){
	 zero = (Button)findViewById(R.id.button0); 
	 one = (Button)findViewById(R.id.button1); 
	 two = (Button)findViewById(R.id.button2);
	 three = (Button)findViewById(R.id.button3);
	 four = (Button)findViewById(R.id.button4);
	 five = (Button)findViewById(R.id.button5);
	 six = (Button)findViewById(R.id.button6); 
	 seven = (Button)findViewById(R.id.button7);
	 eight = (Button)findViewById(R.id.button8); 
	 nine = (Button)findViewById(R.id.button9); 
	 clear = (Button)findViewById(R.id.Clear);
	 clear_all = (Button)findViewById(R.id.Clear_All);
	 division = (Button)findViewById(R.id.division);
	 equal = (Button)findViewById(R.id.equal);
	 minus = (Button)findViewById(R.id.minus);
	 plus = (Button)findViewById(R.id.plus);
	 mul = (Button)findViewById(R.id.mul);
	 text = (EditText)findViewById(R.id.editText1);	
	 close_bracket = (Button)findViewById(R.id.close_bracket);
	 open_bracket = (Button)findViewById(R.id.open_bracket);
	 point = (Button)findViewById(R.id.point);
	 
}
public void all_onClickListener(){
	zero.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '0';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	one.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '1';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	two.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '2';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	three.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '3';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	four.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '4';
			text.setText(s);
			text.setSelection(s.length());
			
		}
	});
	five.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '5';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	six.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '6';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	seven.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '7';
			text.setText(s);
			text.setSelection(s.length());			
		}
	});
	eight.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '8';
			text.setText(s);
			text.setSelection(s.length());
			
		}
	});
	nine.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '9';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	open_bracket.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '(';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	close_bracket.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + ')';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	mul.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '*';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	division.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '/';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	plus.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '+';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	minus.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '-';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	point.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			s = s + '.';
			text.setText(s);
			text.setSelection(s.length());
		}
	});
	clear.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			String s = text.getText().toString();
			String s1 = "";
			for (int i = 0; i < s.length() - 1; i++){
				s1 = s1 + s.charAt(i);
			}
			text.setText(s1);
			if (s.length() > 0){
				text.setSelection(s.length() - 1);
			} else {
				text.setSelection(0);
			}
		}
	});
	clear_all.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){			
			text.setText("");
		    text.setSelection(0);
		}
	});
	equal.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			s = text.getText().toString();
			if ((s.length() == 0)|| (s.equals("ok"))){
				s = "0";
			} else{
			   s = change();
			   try{
			   double res = plus(0,s.length() - 1);
			   s = Double.toString(res);
			   text.setText(s);
			    text.setSelection(s.length());
			   } catch(Exception E){
				   text.setText("Error");
				   text.setSelection(5);
				   
			   }
			   
			}
			
		}
	});
	
	
}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class CalcTest extends TestCase{
		public void checkSomeTest(String t, double res){
			s = "";
			for (int i = 0; i < t.length();i++){
				s = s + t.charAt(i);
			}
			s = change();
			assertTrue((Math.abs(plus(0, s.length() - 1) - res) < 1e-8));
		}
		public void runTest(){
			checkSomeTest("2+2", 4.0);			
			checkSomeTest("-(-1)",1.0);
			checkSomeTest("(-2+1)*(3*2+1)-(-1+1)+2",-5.0);
			checkSomeTest("4/2/2+1",2.0);
			checkSomeTest("(-1+(2*3+1)*10/5)/2+1",7.5);
			checkSomeTest("((-(+(2/2+3*(-3))))+(1-2))*2/(10-4+2*5)",0.875);
			checkSomeTest("2.1*3/2-2+2*3/(1+1)",4.15);
			checkSomeTest("0.0001+1/90000",0.00011111111);
			checkSomeTest("(1*2+3/4-1)-(1+2)",-1.25);
			
		}
		
	}

}

