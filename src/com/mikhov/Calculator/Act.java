package com.mikhov.Calculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Stack;

public class Act extends Activity implements View.OnClickListener {

    Button sqrt_x,  sqrt,  pop_y,   pop,     more,
           sin,     cos,   tg,      ln,      log10,
           bt7,     bt8,   bt9,     Del,     C,
           bt4,     bt5,   bt6,     mul,     dev,
           bt1,     bt2,   bt3,     plus,    minus,
           bt0,     dot,   brace_l, brace_r, result;

    String resText = "", copyEx = "";
    ScrollView myScroll;

    class Dump {
        boolean functions, operations, minuses, digits, dots, dots0, l_braces, r_braces, answer, add_mul, add_mul_num, results;
        String sExpression;
        int braces_count, char_count;

        public Dump(boolean b1, boolean b2, boolean b3, boolean b4,
                    boolean b5, boolean b6, boolean b7, boolean b8,
                    boolean b9, boolean b10, boolean b11, boolean b12, String s, int c1, int c2) {
            functions = b1;
            operations = b2;
            minuses = b3;
            digits = b4;
            dots = b5;
            dots0 = b6;
            l_braces = b7;
            r_braces = b8;
            answer = b9;
            add_mul = b10;
            add_mul_num = b11;
            results = b12;
            sExpression = s;
            braces_count = c1;
            char_count = c2;
        }
    }

    Dump current = null;
    Stack< Dump > history = null;

    boolean functions, operations, minuses, digits, dots, dots0, l_braces, r_braces, answer, add_mul, add_mul_num, results;
    boolean rad = false;
    int braces_count = 0;
    int char_count = 0, max_char_count = Integer.MAX_VALUE;

    TextView expression, exprHistory;
    String sExpression = "";

    void disableAll() {
        setFunctions(false);
        setOperations(false);
        setMinuses(false);
        setDigits(false);
        setDots(false);
        setLBraces(false);
        setRBraces(false);
    }

    void store() {
        current =  new Dump(functions, operations, minuses, digits, dots, dots0, l_braces, r_braces, answer, add_mul, add_mul_num, results, sExpression, braces_count, char_count);
        history.push(current);
    }

    void restore() {
        current = history.pop();

        setFunctions(current.functions);
        setOperations(current.operations);
        setMinuses(current.minuses);
        setDigits(current.digits);
        setDots(current.dots);
        setDots0(current.dots0);
        setLBraces(current.l_braces);
        setRBraces(current.r_braces);
        setAnswer(current.answer);
        setAddMul(current.add_mul);
        setAddMulNum(current.add_mul_num);
        setResults(current.results);
        sExpression = current.sExpression;
        braces_count = current.braces_count;
        char_count = current.char_count;
    }

    void initCalc() {
        res = "";
        braces_count = 0;
        setAddMul(false);
        setAddMulNum(false);
        setDots0(true);
        setDigits(true);
        setResults(false);
        setMinuses(true);
        setDots(false);
        setFunctions(true);
        setOperations(false);
        setLBraces(true);
        setRBraces(false);
        history.clear();
        history.trimToSize();
        store();
    }

    void initButtons() {
        bt0 = (Button) findViewById(R.id.bt0);
        sqrt_x = (Button) findViewById(R.id.info);
        sqrt = (Button) findViewById(R.id.sqrt);
        pop_y = (Button) findViewById(R.id.pop_y);
        pop = (Button) findViewById(R.id.pop);
        more = (Button) findViewById(R.id.more);

        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tg = (Button) findViewById(R.id.tg);
        ln = (Button) findViewById(R.id.ln);
        log10 = (Button) findViewById(R.id.log10);

        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        Del = (Button) findViewById(R.id.Del);
        C = (Button) findViewById(R.id.C);

        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        mul = (Button) findViewById(R.id.mul);
        dev = (Button) findViewById(R.id.dev);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);

        bt0 = (Button) findViewById(R.id.bt0);
        dot = (Button) findViewById(R.id.dot);
        brace_l = (Button) findViewById(R.id.brace_l);
        brace_r = (Button) findViewById(R.id.brace_r);
        result = (Button) findViewById(R.id.result);


        bt0.setOnClickListener(this);
        sqrt_x.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        pop_y.setOnClickListener(this);
        pop.setOnClickListener(this);
        more.setOnClickListener(this);

        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tg.setOnClickListener(this);
        ln.setOnClickListener(this);
        log10.setOnClickListener(this);

        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        Del.setOnClickListener(this);
        C.setOnClickListener(this);

        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        mul.setOnClickListener(this);
        dev.setOnClickListener(this);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);

        bt0.setOnClickListener(this);
        dot.setOnClickListener(this);
        brace_l.setOnClickListener(this);
        brace_r.setOnClickListener(this);
        result.setOnClickListener(this);
    }

    void setAddMulNum(boolean flag) {
        add_mul_num = flag; 
    }

    void setAnswer(boolean flag) {
        answer = flag;
    }

    void setAddMul(boolean flag) {
        add_mul = flag;
    }

    void setDots(boolean flag) {
        dots = flag;
        if (dots) {
            dot.setTextColor(Color.WHITE);
        } else {
            dot.setTextColor(Color.GRAY);
        }
    }

    void setResults(boolean flag) {
        results = flag;
        if (braces_count == 0) {
            if (results) {
                result.setTextColor(Color.WHITE);
            } else {
                result.setTextColor(Color.GRAY);
            }
        } else {
            results = false;
            result.setTextColor(Color.GRAY);
        }
    }

    void setDots0(boolean flag) {
        dots0 = flag;
    }

    void setDigits(boolean flag) {
        digits = flag;
        if (digits) {
            bt0.setTextColor(Color.WHITE);
            bt1.setTextColor(Color.WHITE);
            bt2.setTextColor(Color.WHITE);
            bt3.setTextColor(Color.WHITE);
            bt4.setTextColor(Color.WHITE);
            bt5.setTextColor(Color.WHITE);
            bt6.setTextColor(Color.WHITE);
            bt7.setTextColor(Color.WHITE);
            bt8.setTextColor(Color.WHITE);
            bt9.setTextColor(Color.WHITE);
        } else {
            bt0.setTextColor(Color.GRAY);
            bt1.setTextColor(Color.GRAY);
            bt2.setTextColor(Color.GRAY);
            bt3.setTextColor(Color.GRAY);
            bt4.setTextColor(Color.GRAY);
            bt5.setTextColor(Color.GRAY);
            bt6.setTextColor(Color.GRAY);
            bt7.setTextColor(Color.GRAY);
            bt8.setTextColor(Color.GRAY);
            bt9.setTextColor(Color.GRAY);
        }
    }

    void setOperations(boolean flag) {
        operations = flag;
        if (operations) {
            plus.setTextColor(Color.WHITE);
            mul.setTextColor(Color.WHITE);
            dev.setTextColor(Color.WHITE);
            pop_y.setTextColor(Color.WHITE);
            pop.setTextColor(Color.WHITE);
        } else {
            plus.setTextColor(Color.GRAY);
            mul.setTextColor(Color.GRAY);
            dev.setTextColor(Color.GRAY);
            pop_y.setTextColor(Color.GRAY);
            pop.setTextColor(Color.GRAY);
        }
    }

    void setMinuses(boolean flag) {
        minuses = flag;
        if (minuses) {
            minus.setTextColor(Color.WHITE);
        } else {
            minus.setTextColor(Color.GRAY);
        }
    }

    void setFunctions(boolean flag) {
        functions = flag;
        if (functions) {
            sqrt_x.setTextColor(Color.WHITE);
            sqrt.setTextColor(Color.WHITE);
            sin.setTextColor(Color.WHITE);
            cos.setTextColor(Color.WHITE);
            tg.setTextColor(Color.WHITE);
            ln.setTextColor(Color.WHITE);
            log10.setTextColor(Color.WHITE);
        } else {
            sqrt_x.setTextColor(Color.WHITE);
            sqrt.setTextColor(Color.GRAY);
            sin.setTextColor(Color.GRAY);
            cos.setTextColor(Color.GRAY);
            tg.setTextColor(Color.GRAY);
            ln.setTextColor(Color.GRAY);
            log10.setTextColor(Color.GRAY);
        }
    }

    void setLBraces(boolean flag) {
        l_braces = flag;
        if (l_braces) {
            brace_l.setTextColor(Color.WHITE);
        } else {
            brace_l.setTextColor(Color.GRAY);
        }
    }

    void setRBraces(boolean flag) {
        r_braces = flag;
        if (r_braces) {
            brace_r.setTextColor(Color.WHITE);
        } else {
            brace_r.setTextColor(Color.GRAY);
        }
    }

    void digitPressed() {
        setAddMulNum(false);
        setAddMul(true);
        setResults(true);
        setOperations(true);
        setMinuses(true);
        setFunctions(true);
        setLBraces(true);
        if (dots0) {
            setDots(true);
        }
        if (braces_count > 0) {
            setRBraces(true);
        }
    }

    void operationPressed() {
        setAddMul(false);
        setAddMulNum(false);
        setFunctions(true);
        setResults(false);
        setOperations(false);
        setMinuses(false);
        setDigits(true);
        setDots(false);
        setDots0(true);
        setLBraces(true);
        setRBraces(false);
    }

    void functionPressed() {
        setAddMul(false);
        setAddMulNum(false);
        setFunctions(true);
        setOperations(false);
        setMinuses(true);
        setDigits(true);
        setResults(false);
        setDots(false);
        setDots0(true);
        setLBraces(true);
        setRBraces(false);
        braces_count++;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        history = new Stack< Dump >();
        initButtons();
        expression = (TextView)  findViewById(R.id.expression);
        exprHistory = (TextView)  findViewById(R.id.History);
        myScroll = (ScrollView) findViewById(R.id.scr);
        initCalc();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt0:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "0";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "0";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt1:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "1";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "1";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt2:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "2";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "2";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt3:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "3";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "3";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt4:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "4";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "4";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt5:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "5";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "5";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt6:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "6";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "6";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt7:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "7";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "7";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt8:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "8";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "8";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.bt9:
                if (digits) {
                    if (add_mul_num) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "9";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "9";
                        char_count++;
                        digitPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.plus:
                if (operations) {
                    sExpression += "+";
                    char_count++;
                    operationPressed();
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.minus:
                if (minuses) {
                    sExpression += "-";
                    char_count++;
                    operationPressed();
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.mul:
                if (operations) {
                    sExpression += "×";
                    char_count++;
                    operationPressed();
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.dev:
                if (operations) {
                    sExpression += "÷";
                    char_count++;
                    operationPressed();
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.pop:
                if (functions && operations) {
                    sExpression += "^(2)";
                    char_count++;
                    setAddMul(true);
                    setAddMulNum(true);
                    setFunctions(true);
                    setOperations(true);
                    setResults(true);
                    setMinuses(true);
                    setDigits(true);
                    setDots(false);
                    setDots0(true);
                    setLBraces(true);
                    if (braces_count > 0) {
                        setRBraces(true);
                    } else {
                        setRBraces(false);
                    }
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.pop_y:
                if (functions && operations) {
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                    if (functions) {
                        sExpression += "^(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.sin:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (functions) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "sin(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "sin(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.cos:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (functions) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "cos(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "cos(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.tg:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (functions) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "tg(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "tg(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.ln:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (functions) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "ln(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "ln(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.log10:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (functions) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "log(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "log(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.sqrt:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (functions) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "√(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "√(";
                        char_count++;
                        functionPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.dot:
                if (char_count == max_char_count) {
                    disableAll();
                }
                if (dots) {
                    sExpression += ".";
                    char_count++;
                    setAddMul(false);
                    setDots(false);
                    setDots0(false);
                    setResults(false);
                    setOperations(false);
                    setMinuses(false);
                    setFunctions(false);
                    setLBraces(false);
                    setRBraces(false);
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.brace_l:
                if (l_braces) {
                    if (add_mul) {
                        sExpression += "×";
                        char_count++;
                        operationPressed();
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }

                        sExpression += "(";
                        char_count++;
                        setAddMul(false);
                        setAddMulNum(false);
                        setFunctions(true);
                        setOperations(false);
                        setMinuses(true);
                        setDigits(true);
                        setDots(false);
                        setDots0(true);
                        setLBraces(true);
                        setRBraces(false);
                        braces_count++;
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    } else {
                        sExpression += "(";
                        char_count++;
                        setAddMul(false);
                        setAddMulNum(false);
                        setFunctions(true);
                        setOperations(false);
                        setMinuses(true);
                        setDigits(true);
                        setDots(false);
                        setDots0(true);
                        setLBraces(true);
                        setRBraces(false);
                        braces_count++;
                        store();
                        if (char_count == max_char_count) {
                            disableAll();
                        }
                    }
                }
                break;
            case R.id.brace_r:
                if (r_braces) {
                    sExpression += ")";
                    char_count++;
                    setAddMul(true);
                    setAddMulNum(true);
                    setFunctions(true);
                    setOperations(true);
                    setMinuses(true);
                    setDigits(true);
                    setDots(false);
                    setDots0(true);
                    setLBraces(true);
                    braces_count--;
                    setResults(true);
                    if (braces_count > 0) {
                        setRBraces(true);
                    } else {
                        setRBraces(false);
                    }
                    store();
                    if (char_count == max_char_count) {
                        disableAll();
                    }
                }
                break;
            case R.id.Del:
                if (!sExpression.equals("")) {
                    restore();
                } else {
                    sExpression = "";
                    char_count = 0;
                    expression.setText(sExpression);
                    initCalc();
                }
                expression.setText(sExpression);
                break;
            case R.id.C:
                sExpression = "";
                char_count = 0;
                expression.setText(sExpression);
                initCalc();
                break;
            case R.id.more:
                break;
            case R.id.result:
                if (results) {
                    copyEx = sExpression + "";
                    sExpression = prepareEx(sExpression);
                    String final_result = braceParse(sExpression, 0, sExpression.length() - 1) + "";
                    if (final_result.indexOf("NaN") < 0) {
                        initCalc();
                        resText += copyEx + " = " + braceParse(sExpression, 0, sExpression.length() - 1) + "\n\n";
                        sExpression = "";
                        expression.setText(sExpression);
                        myScroll.scrollBy(0, +500);
                    } else {
                        resText += copyEx + " = " + "Error while parsing...\n\n";
                        sExpression = copyEx;
                        expression.setText(sExpression);
                        myScroll.scrollBy(0, +500);
                    }
                }
                break;
            default:
                break;
        }
        expression.setText(sExpression);
        exprHistory.setText(resText);
    }

    String prepareEx(String sExpression) {
        if (sExpression.charAt(0) == '-') {
            sExpression = "0" + sExpression;
        }
        for (int i = 0; i < sExpression.length(); ++i) {
            if (sExpression.charAt(i) == '(' && sExpression.charAt(i + 1) == '-') {
                sExpression = sExpression.substring(0, i + 1) + "0" + sExpression.substring(i + 1, sExpression.length());
            }
        }
        int top = sExpression.length();
        for (int i = 0; i < top; ++i) {
            if (sExpression.charAt(i) == 's' && sExpression.charAt(i + 1) == 'i' && sExpression.charAt(i + 2) == 'n') {
                sExpression = sExpression.substring(0, i) + "a " + sExpression.substring(i + 3, sExpression.length());
                top -= 1;
                i += 2;
            }
            if (sExpression.charAt(i) == 'c' && sExpression.charAt(i + 1) == 'o' && sExpression.charAt(i + 2) == 's') {
                sExpression = sExpression.substring(0, i) + "b " + sExpression.substring(i + 3, sExpression.length());
                top -= 1;
                i += 2;
            }
            if (sExpression.charAt(i) == 't' && sExpression.charAt(i + 1) == 'g') {
                sExpression = sExpression.substring(0, i) + "c " + sExpression.substring(i + 2, sExpression.length());
                i += 1;
            }
            if (sExpression.charAt(i) == 'l' && sExpression.charAt(i + 1) == 'n') {
                sExpression = sExpression.substring(0, i) + "d " + sExpression.substring(i + 2, sExpression.length());
                i += 1;
            }
            if (sExpression.charAt(i) == 'l' && sExpression.charAt(i + 1) == 'o' && sExpression.charAt(i + 2) == 'g') {
                sExpression = sExpression.substring(0, i) + " f " + sExpression.substring(i + 3, sExpression.length());
                top -= 1;
                i += 2;
            }
            if (sExpression.charAt(i) == '√') {
                sExpression = sExpression.substring(0, i) + "√ " + sExpression.substring(i + 1, sExpression.length());
                top += 1;
                i += 2;
            }
            if (sExpression.charAt(i) == '+') {
                sExpression = sExpression.substring(0, i) + " + " + sExpression.substring(i + 1, sExpression.length());
                top += 2;
                i += 2;
            }
            if (sExpression.charAt(i) == '-') {
                sExpression = sExpression.substring(0, i) + " - " + sExpression.substring(i + 1, sExpression.length());
                top += 2;
                i += 2;
            }
            if (sExpression.charAt(i) == '×') {
                sExpression = sExpression.substring(0, i) + " × " + sExpression.substring(i + 1, sExpression.length());
                top += 2;
                i += 2;
            }
            if (sExpression.charAt(i) == '÷') {
                sExpression = sExpression.substring(0, i) + " ÷ " + sExpression.substring(i + 1, sExpression.length());
                top += 2;
                i += 2;
            }
            if (sExpression.charAt(i) == '^') {
                sExpression = sExpression.substring(0, i) + " ^ " + sExpression.substring(i + 1, sExpression.length());
                top += 2;
                i += 2;
            }
        }
        return sExpression;
    }

    String res = "";
    int pre_minus1 = 1, pre_minus2 = 1;
    String simpleParse(String s) {
        res += s + ";\n";
        if (s.indexOf(' ') < 0) {
            return Double.parseDouble(s) + "";
        }
        String[] ex = s.split(" ");

        for (int i = 0; i < ex.length; ++i) {
            res += "[" + ex[i] + "]; ";
        }
        res += "\n\n";

        for (int i = 0; i < ex.length; ++i) {
            if (ex[i].equals("√") || ex[i].equals("a") || ex[i].equals("b") || ex[i].equals("c") || ex[i].equals("d") || ex[i].equals("f")) {
                if (ex[i].equals("√")) {
                    String new_ex = "";
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    ex[i] = (Math.sqrt(pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                } else if (ex[i].equals("a")) {
                    String new_ex = "";
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    if (rad) {
                        ex[i] = (pre_minus2 * Math.sin(Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    } else {
                        ex[i] = (pre_minus2 * Math.sin(Math.abs(Double.parseDouble(ex[i + 1]) / 180 * Math.PI))) + "";
                    }
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                } else if (ex[i].equals("b")) {
                    String new_ex = "";
                    if (rad) {
                        ex[i] = (Math.cos(Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    } else {
                        ex[i] = (Math.cos(Math.abs(Double.parseDouble(ex[i + 1]) / 180 * Math.PI))) + "";
                    }
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                } else if (ex[i].equals("c")) {
                    String new_ex = "";
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    if (rad) {
                        ex[i] = (pre_minus2 * Math.tan(Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    } else {
                        ex[i] = (pre_minus2 * Math.tan(Math.abs(Double.parseDouble(ex[i + 1]) / 180 * Math.PI))) + "";
                    }
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                } else if (ex[i].equals("d")) {
                    String new_ex = "";
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    ex[i] = (Math.log(pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                } else  if (ex[i].equals("f")) {
                    String new_ex = "";
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    ex[i] = (Math.log10(pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                }
            }
        }
        for (int i = 0; i < ex.length; ++i) {
            if (ex[i].equals("^")) {
                String new_ex = "";
                if (ex[i - 1].charAt(0) == '-') {
                    pre_minus1 = -1;
                } else {
                    pre_minus1 = 1;
                }
                if (ex[i + 1].charAt(0) == '-') {
                    pre_minus2 = -1;
                } else {
                    pre_minus2 = 1;
                }
                ex[i] = Math.pow(pre_minus1 * Math.abs(Double.parseDouble(ex[i - 1])), pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1]))) + "";
                ex[i - 1] = "";
                ex[i + 1] = "";
                for (int j = 0; j < ex.length; ++j) {
                    if (ex[j].equals("√")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("a")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("b")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("c")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("d")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("f")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("+")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("-")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("×")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("÷")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("^")) {
                        new_ex += " " + ex[j] + " ";
                    } else {
                        new_ex += ex[j] + "";
                    }
                }
                return simpleParse(new_ex);
            }
        }
        for (int i = 0; i < ex.length; ++i) {
            if (ex[i].equals("×") || ex[i].equals("÷")) {
                if (ex[i].equals("×")) {
                    String new_ex = "";
                    if (ex[i - 1].charAt(0) == '-') {
                        pre_minus1 = -1;
                    } else {
                        pre_minus1 = 1;
                    }
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    ex[i] = (pre_minus1 * Math.abs(Double.parseDouble(ex[i - 1])) * pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1]))) + "";
                    ex[i - 1] = "";
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                } else if (ex[i].equals("÷")) {
                    String new_ex = "";
                    if (ex[i - 1].charAt(0) == '-') {
                        pre_minus1 = -1;
                    } else {
                        pre_minus1 = 1;
                    }
                    if (ex[i + 1].charAt(0) == '-') {
                        pre_minus2 = -1;
                    } else {
                        pre_minus2 = 1;
                    }
                    ex[i] = ((pre_minus1 * Math.abs(Double.parseDouble(ex[i - 1]))) / (pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1])))) + "";
                    ex[i - 1] = "";
                    ex[i + 1] = "";
                    for (int j = 0; j < ex.length; ++j) {
                        if (ex[j].equals("√")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("a")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("b")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("c")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("d")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("f")) {
                            new_ex += ex[j] + " ";
                        } else if (ex[j].equals("+")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("-")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("×")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("÷")) {
                            new_ex += " " + ex[j] + " ";
                        } else if (ex[j].equals("^")) {
                            new_ex += " " + ex[j] + " ";
                        } else {
                            new_ex += ex[j] + "";
                        }
                    }
                    return simpleParse(new_ex);
                }
            }
        }
        for (int i = 0; i < ex.length; ++i) {
            if (ex[i].equals("-")) {
                String new_ex = "";
                if (ex[i - 1].charAt(0) == '-') {
                    pre_minus1 = -1;
                } else {
                    pre_minus1 = 1;
                }
                if (ex[i + 1].charAt(0) == '-') {
                    pre_minus2 = -1;
                } else {
                    pre_minus2 = 1;
                }
                ex[i] = (pre_minus1 * Math.abs(Double.parseDouble(ex[i - 1])) - pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1]))) + "";
                ex[i - 1] = "";
                ex[i + 1] = "";
                for (int j = 0; j < ex.length; ++j) {
                    if (ex[j].equals("?")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("a")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("b")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("c")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("d")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("f")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("+")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("-")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("×")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("÷")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("^")) {
                        new_ex += " " + ex[j] + " ";
                    } else {
                        new_ex += ex[j] + "";
                    }
                }
                return simpleParse(new_ex);
            }
        }
        for (int i = 0; i < ex.length; ++i) {
            if (ex[i].equals("+")) {
                String new_ex = "";
                if (ex[i - 1].charAt(0) == '-') {
                    pre_minus1 = -1;
                } else {
                    pre_minus1 = 1;
                }
                if (ex[i + 1].charAt(0) == '-') {
                    pre_minus2 = -1;
                } else {
                    pre_minus2 = 1;
                }
                ex[i] = (pre_minus1 * Math.abs(Double.parseDouble(ex[i - 1])) + pre_minus2 * Math.abs(Double.parseDouble(ex[i + 1]))) + "";
                ex[i - 1] = "";
                ex[i + 1] = "";
                for (int j = 0; j < ex.length; ++j) {
                    if (ex[j].equals("√")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("a")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("b")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("c")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("d")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("f")) {
                        new_ex += ex[j] + " ";
                    } else if (ex[j].equals("+")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("-")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("×")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("÷")) {
                        new_ex += " " + ex[j] + " ";
                    } else if (ex[j].equals("^")) {
                        new_ex += " " + ex[j] + " ";
                    } else {
                        new_ex += ex[j] + "";
                    }
                }
                return simpleParse(new_ex);
            }
        }
        return "Error...\n";
    }

    String braceParse(String s, int start, int stop) {
        int b = start, e = stop;
        boolean flag = false;
        for (int i = start; i < stop; i++) {
            if (s.charAt(i) == ')') {
                e = i;
                flag = true;
                break;
            }
        }
        for (int i = e; i >= start; i--) {
            if (s.charAt(i) == '(') {
                b = i;
                flag = true;
                break;
            }
        }
        if (!flag) {
            return simpleParse(s);
        } else {
            String s1 = s.substring(start, b);
            String s2 = simpleParse(s.substring(b + 1, e));
            String s3 = s.substring(e + 1, stop + 1);
            s = s1 + s2 + s3;
            return braceParse(s, 0, s.length() - 1);
        }
    }
}