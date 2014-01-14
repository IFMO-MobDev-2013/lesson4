package com.mobdev.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	 String toCalculate="";
	 Boolean resetAfterEqual = false;
	 Boolean checkDot = true;
	 int countBrackets = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView scr_string = (TextView)findViewById(R.id.txt1);
		final Button btn1 = (Button)findViewById(R.id.btn1);
		final Button btn2 = (Button)findViewById(R.id.btn2);
		final Button btn3 = (Button)findViewById(R.id.btn3);
		final Button btn4 = (Button)findViewById(R.id.btn4);
		final Button btn5 = (Button)findViewById(R.id.btn5);
		final Button btn6 = (Button)findViewById(R.id.btn6);
		final Button btn7 = (Button)findViewById(R.id.btn7);
		final Button btn8 = (Button)findViewById(R.id.btn8);
		final Button btn9 = (Button)findViewById(R.id.btn9);
		final Button btn0 = (Button)findViewById(R.id.btn0);
		final Button btn_l = (Button)findViewById(R.id.btn_l);
		final Button btn_r = (Button)findViewById(R.id.btn_r);
		final Button btndot = (Button)findViewById(R.id.btndot);
		final Button btneq = (Button)findViewById(R.id.btneq);
		final Button btnsum = (Button)findViewById(R.id.btnsum);
		final Button btnsub = (Button)findViewById(R.id.btnsub);
		final Button btnmul = (Button)findViewById(R.id.btnmul);
		final Button btndiv = (Button)findViewById(R.id.btndiv);
		final Button btnc = (Button)findViewById(R.id.btnc);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=1;
				scr_string.setText(toCalculate);
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=2;
				scr_string.setText(toCalculate);
			}
		});
		
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=3;
				scr_string.setText(toCalculate);
			}
		});
		
		btn4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=4;
				scr_string.setText(toCalculate);
			}
		});
		
		btn5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=5;
				scr_string.setText(toCalculate);
			}
		});

		btn6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=6;
				scr_string.setText(toCalculate);
			}
		});
		
		btn7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=7;
				scr_string.setText(toCalculate);
			}
		});
		
		btn8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=8;
				scr_string.setText(toCalculate);
			}
		});
		
		btn9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=9;
				scr_string.setText(toCalculate);
			}
		});
		
		btn0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				toCalculate+=0;
				scr_string.setText(toCalculate);
			}
		});
		
		btn_l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					toCalculate = "";
					scr_string.setText(toCalculate);
					resetAfterEqual = false;
				}

				if (!toCalculate.isEmpty()) {
					if (toCalculate.charAt(toCalculate.length() - 1) == ')'
							|| (toCalculate.charAt(toCalculate
									.length() - 1) - '0' >= 0 && toCalculate
									.charAt(toCalculate.length() - 1) - '0' <= 9)) {
						toCalculate += '\u00D7' + "(";
					} else {
						toCalculate += "(";
					}
				} else {
					toCalculate += "(";
				}
				scr_string.setText(toCalculate);
				checkDot = true;
				countBrackets++;
			}
		});
		
		btn_r.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					toCalculate = "";
					scr_string.setText(toCalculate);
					resetAfterEqual = false;
				}
				if (countBrackets != 0) {
					toCalculate += ')';
					scr_string.setText(toCalculate);
					checkDot = true;
					countBrackets--;
				}
			}
		});
		
		btndot.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					toCalculate = "";
					scr_string.setText(toCalculate);
					resetAfterEqual = false;
				}
				if (checkDot) {
					if (!toCalculate.isEmpty()) {
						if ((toCalculate.charAt(toCalculate
								.length() - 1) == '+'
								|| toCalculate.charAt(toCalculate
										.length() - 1) == '\u2212'
								|| toCalculate.charAt(toCalculate
										.length() - 1) == '\u00D7'
								|| toCalculate.charAt(toCalculate
										.length() - 1) == '\u00F7' || toCalculate
								.charAt(toCalculate.length() - 1) == '(')) {
							toCalculate += "0.";
							scr_string.setText(toCalculate);
						} else {
							toCalculate += '.';
							scr_string.setText(toCalculate);
						}
					} else {
						toCalculate += '.';
						scr_string.setText(toCalculate);
					}
				}
				checkDot = false;
			}
		});
		
		btnsum.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!toCalculate.isEmpty()) {
					if (toCalculate.charAt(toCalculate.length() - 1) == '\u2212') {
						if (toCalculate.length() > 1
								&& (toCalculate.charAt(toCalculate
										.length() - 2) == '+'
										|| toCalculate
												.charAt(toCalculate
														.length() - 2) == '\u2212'
										|| toCalculate
												.charAt(toCalculate
														.length() - 2) == '\u00D7' || toCalculate
										.charAt(toCalculate.length() - 2) == '\u00F7')) {
							toCalculate = toCalculate.substring(0,
									toCalculate.length() - 2) + '+';
						}
					}
					if (!(toCalculate.charAt(toCalculate.length() - 1) == '+'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u2212'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u00D7'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u00F7' || toCalculate
							.charAt(toCalculate.length() - 1) == '(')) {
						toCalculate += '+';
					} else {
						if (!(toCalculate.charAt(toCalculate
								.length() - 1) == '(')) {
							toCalculate = toCalculate.substring(0,
									toCalculate.length() - 1) + '+';
						}
					}
				}
				scr_string.setText(toCalculate);
				checkDot = true;
				resetAfterEqual = false;
			}
		});
		
		btnsub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!(toCalculate.isEmpty() || toCalculate
						.charAt(toCalculate.length() - 1) == '(')) {
					if (!(toCalculate.charAt(toCalculate.length() - 1) == '\u2212')) {
						toCalculate += "\u2212";
					} else {
						toCalculate = toCalculate.substring(0,
								toCalculate.length() - 1) + "\u2212";
					}

				} else {
					toCalculate += "0\u2212";
				}
				scr_string.setText(toCalculate);
				checkDot = true;
				resetAfterEqual = false;
			}
		});
		
		btnmul.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!toCalculate.isEmpty()) {
					if (toCalculate.charAt(toCalculate.length() - 1) == '\u2212') {
						if (toCalculate.length() > 1
								&& (toCalculate.charAt(toCalculate
										.length() - 2) == '+'
										|| toCalculate
												.charAt(toCalculate
														.length() - 2) == '\u2212'
										|| toCalculate
												.charAt(toCalculate
														.length() - 2) == '\u00D7' || toCalculate
										.charAt(toCalculate.length() - 2) == '\u00F7')) {
							toCalculate = toCalculate.substring(0,
									toCalculate.length() - 2) + '\u00D7';
						}
					}
					if (!(toCalculate.charAt(toCalculate.length() - 1) == '+'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u2212'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u00D7'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u00F7' || toCalculate
							.charAt(toCalculate.length() - 1) == '(')) {
						toCalculate += '\u00D7';
					} else {
						if (!(toCalculate.charAt(toCalculate
								.length() - 1) == '(')) {
							toCalculate = toCalculate.substring(0,
									toCalculate.length() - 1) + '\u00D7';
						}
					}
				}
				scr_string.setText(toCalculate);
				checkDot = true;
				resetAfterEqual = false;
			}
		});

		btndiv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!toCalculate.isEmpty()) {
					if (toCalculate.charAt(toCalculate.length() - 1) == '\u2212') {
						if (toCalculate.length() > 1
								&& (toCalculate.charAt(toCalculate
										.length() - 2) == '+'
										|| toCalculate
												.charAt(toCalculate
														.length() - 2) == '\u2212'
										|| toCalculate
												.charAt(toCalculate
														.length() - 2) == '\u00D7' || toCalculate
										.charAt(toCalculate.length() - 2) == '\u00F7')) {
							toCalculate = toCalculate.substring(0,
									toCalculate.length() - 2) + '\u00F7';
						}
					}
					if (!(toCalculate.charAt(toCalculate.length() - 1) == '+'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u2212'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u00D7'
							|| toCalculate.charAt(toCalculate
									.length() - 1) == '\u00F7' || toCalculate
							.charAt(toCalculate.length() - 1) == '(')) {
						toCalculate += '\u00F7';
					} else {
						if (!(toCalculate.charAt(toCalculate
								.length() - 1) == '(')) {
							toCalculate = toCalculate.substring(0,
									toCalculate.length() - 1) + '\u00F7';
						}
					}
				}
				scr_string.setText(toCalculate);
				checkDot = true;
				resetAfterEqual = false;
			}
		});
		
		btnc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (resetAfterEqual) {
					 toCalculate = "";
					 resetAfterEqual = false;
				}
				if (!toCalculate.isEmpty()) {
					if (toCalculate.charAt(toCalculate.length() - 1) == '.') checkDot = true;
					if (toCalculate.charAt(toCalculate.length() - 1) == ')') countBrackets++;
					if (toCalculate.charAt(toCalculate.length() - 1) == '(') countBrackets--;
					toCalculate = toCalculate.substring(0, toCalculate.length() - 1);
				}
				scr_string.setText(toCalculate);
			}
		});
		
		btnc.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				toCalculate = "";
				scr_string.setText(toCalculate);
				resetAfterEqual = false;
				return true;
			}
		});
		
		btneq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (MathParserRun.evaluate(toCalculate) != null)
					toCalculate = Double.toString(MathParserRun.evaluate(toCalculate));
				else
					toCalculate = "Error";
				scr_string.setText(toCalculate);
				resetAfterEqual = true;
				checkDot = true;
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
