package com.example.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;


public class Calc extends Activity {
    private static String expression = "";
    private static boolean needToBeCleared = false;
    private static int brackets = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button b0 = (Button) findViewById(R.id.button7);
        final Button b1 = (Button) findViewById(R.id.button);
        final Button b2 = (Button) findViewById(R.id.button4);
        final Button b3 = (Button) findViewById(R.id.button8);
        final Button b4 = (Button) findViewById(R.id.button1);
        final Button b5 = (Button) findViewById(R.id.button5);
        final Button b6 = (Button) findViewById(R.id.button9);
        final Button b7 = (Button) findViewById(R.id.button2);
        final Button b8 = (Button) findViewById(R.id.button6);
        final Button b9 = (Button) findViewById(R.id.button10);
        final Button bpoint = (Button) findViewById(R.id.button3);
        final Button bequal = (Button) findViewById(R.id.button11);
        final Button bclear = (Button) findViewById(R.id.button12);
        final Button badd = (Button) findViewById(R.id.button13);
        final Button bsub = (Button) findViewById(R.id.button14);
        final Button bmul = (Button) findViewById(R.id.button15);
        final Button bdiv = (Button) findViewById(R.id.button16);
        final Button bopbr = (Button) findViewById(R.id.button17);
        final Button bclbr = (Button) findViewById(R.id.button18);
        final EditText edit = (EditText) findViewById(R.id.editText);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "0");
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                edit.setText(edit.getEditableText() + "9");
            }
        });

        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                String temp = edit.getEditableText().toString();
                if (!"".equals(temp)) {
                    if (temp.charAt(temp.length() - 1) == '(') {
                        brackets--;
                    } else if (temp.charAt(temp.length() - 1) == ')') {
                        brackets++;
                    }
                    temp = temp.substring(0, temp.length() - 1);
                    edit.setText(temp);
                }
            }
        });

        bclear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                edit.setText("");
                brackets = 0;
                return false;
            }
        });

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                String temp = edit.getEditableText().toString();
                if (!temp.isEmpty()) {
                    if (temp.charAt(temp.length() - 1) == '-') {
                        if (temp.length() > 1) {
                            char t = temp.charAt(temp.length() - 2);
                            if (t == '-' || t == '+' || t == '*' || t == '/') {
                                temp = temp.substring(0, temp.length() - 2) + '+';
                            }
                        }
                    }
                    char t = temp.charAt(temp.length() - 1);
                    if (!(t == '+' || t == '-' || t =='*' || t == '/' || t == '(')) {
                        temp += '+';
                    } else {
                        if (t != '(') {
                            temp = temp.substring(0, temp.length() - 1) + '+';
                        }
                    }
                }
                edit.setText(temp);
            }
        });

        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                String temp = edit.getEditableText().toString();
                if (!(temp.isEmpty() || temp.charAt(temp.length() - 1) == '(')) {
                    if (!(temp.charAt(temp.length() - 1) == '-')) {
                        temp += '-';
                    } else {
                        temp = temp.substring(0, temp.length() - 1) + '-';
                    }
                } else {
                    temp += "0-";
                }
                edit.setText(temp);
            }
        });

        bmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                String temp = edit.getEditableText().toString();
                if (!temp.isEmpty()) {
                    if (temp.charAt(temp.length() - 1) == '-') {
                        if (temp.length() > 1) {
                            char t = temp.charAt(temp.length() - 2);
                            if (t == '-' || t == '+' || t == '*' || t == '/') {
                                temp = temp.substring(0, temp.length() - 2) + '*';
                            }
                        }
                    }
                    char t = temp.charAt(temp.length() - 1);
                    if (!(t == '+' || t == '-' || t =='*' || t == '/' || t == '(')) {
                        temp += '*';
                    } else {
                        if (t != '(') {
                            temp = temp.substring(0, temp.length() - 1) + '*';
                        }
                    }
                }
                edit.setText(temp);
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                String temp = edit.getEditableText().toString();
                if (!temp.isEmpty()) {
                    if (temp.charAt(temp.length() - 1) == '-') {
                        if (temp.length() > 1) {
                            char t = temp.charAt(temp.length() - 2);
                            if (t == '-' || t == '+' || t == '*' || t == '/') {
                                temp = temp.substring(0, temp.length() - 2) + '/';
                            }
                        }
                    }
                    char t = temp.charAt(temp.length() - 1);
                    if (!(t == '+' || t == '-' || t =='*' || t == '/' || t == '(')) {
                        temp += '/';
                    } else {
                        if (t != '(') {
                            temp = temp.substring(0, temp.length() - 1) + '/';
                        }
                    }
                }
                edit.setText(temp);
            }
        });

        bopbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    needToBeCleared = false;
                    brackets = 0;
                }
                String temp = edit.getEditableText().toString();
                if (!temp.isEmpty()) {
                    if (temp.charAt(temp.length() - 1) == ')' || Character.isDigit(temp.charAt(temp.length() - 1))) {
                        temp += "*(";
                    } else {
                        temp += '(';
                    }
                } else {
                    temp += "(";
                }
                brackets++;
                edit.setText(temp);
            }
        });

        bclbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                edit.setText(edit.getEditableText() + ")");
                brackets--;
            }
        });

        bpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                edit.setText(edit.getEditableText() + ".");
            }
        });

        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needToBeCleared) {
                    edit.setText("");
                    brackets = 0;
                    needToBeCleared = false;
                }
                expression = edit.getEditableText().toString();
                if (brackets != 0) {
                    edit.setText("Error");
                    needToBeCleared = true;
                    return;
                }
                try {
                    Double res = MatchParser.parse(expression);
                    NumberFormat a = NumberFormat.getInstance();
                    a.setMaximumFractionDigits(100);
                    a.setMinimumFractionDigits(0);
                    a.setGroupingUsed(false);
                    edit.setText(a.format(res).toString());
                } catch (Exception e) {
                    edit.setText("Error");
                }
                needToBeCleared = true;
            }
        });
    }
}
