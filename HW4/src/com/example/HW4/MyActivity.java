package com.example.HW4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.Test;

public class MyActivity extends Activity {
    TextView exprestion;
    String temp_exprestion = new String();

    class Parser_exception extends Exception{
        public Parser_exception(){}
        String msg = "Parsing error!";
    }

    class Division_by_zero extends Exception{
        public Division_by_zero(){}
        String msg = "Division_by_zero!";
    }

    private int skip_brakets(String tmp, int start)throws Parser_exception, Division_by_zero{
        int sup = 1;
        start--;
        while(sup != 0){
            if(tmp.charAt(start) == ')')
                sup++;
            if(tmp.charAt(start) == '(')
                sup--;
            if(sup == 0)
                return start;
            if(start != 0)
                start--;
            else

                throw new Parser_exception();
        }
        return start;
    }

    private int find_token(int size, String tmp, char... a)throws Parser_exception, Division_by_zero{
        int sup = tmp.length() - 1;
        while (sup >= 0){
            for(int i = 0; i < size; i++){
                if (tmp.charAt(sup) == a[i])
                    return sup;
            }
            if(tmp.charAt(sup) == ')')
                sup = skip_brakets(tmp, sup);
            sup--;
        }
        return sup;
    }

    private double const_priority(String temp)throws Parser_exception, Division_by_zero{
        double sup = Double.parseDouble(temp);
        return sup;
    }

    private double brakets_pliority (String temp)throws Parser_exception, Division_by_zero{
        if(temp.equals(""))
            return 0;
        if(temp.charAt(0) == '('){
            if(temp.charAt(temp.length() - 1) == ')')
                return plus_priority(temp.substring(1, temp.length() - 1));
            else
                throw new Parser_exception();
        }
        else
            return const_priority(temp);
    }

    private double mul_priority(String temp)throws Parser_exception, Division_by_zero{
        int k = find_token(2, temp, '*', '/');
        if(temp.equals(""))
            return 0;
        if(k != -1){
            if(temp.charAt(k) == '*'){
                return mul_priority(temp.substring(0, k)) * mul_priority(temp.substring(k + 1, temp.length()));
            }
            else{
                double sup = mul_priority(temp.substring(k + 1, temp.length()));
                if(sup != 0)
                    return mul_priority(temp.substring(0, k)) / sup;
                else
                    throw new Division_by_zero();
            }
        }
        else
            return brakets_pliority(temp);
    }

    public double plus_priority(String temp)throws Parser_exception, Division_by_zero{
        int k = find_token(2, temp, '+', '-');
        if(temp.equals(""))
            return 0;
        if (k != -1){
            if(temp.charAt(k) == '+')
                return plus_priority(temp.substring(0, k)) + plus_priority(temp.substring(k + 1, temp.length()));
            else
                return plus_priority(temp.substring(0, k)) - plus_priority(temp.substring(k + 1, temp.length()));
        }
        else
            return mul_priority(temp);
    }

    private void draw_clicking(String a){
        temp_exprestion += a;
        exprestion.setText(temp_exprestion);
    }

    public void click_0(View set){draw_clicking("0");};
    public void click_1(View set){draw_clicking("1");};
    public void click_2(View set){draw_clicking("2");};
    public void click_3(View set){draw_clicking("3");};
    public void click_4(View set){draw_clicking("4");};
    public void click_5(View set){draw_clicking("5");};
    public void click_6(View set){draw_clicking("6");};
    public void click_7(View set){draw_clicking("7");};
    public void click_8(View set){draw_clicking("8");};
    public void click_9(View set){draw_clicking("9");};
    public void click_plus(View set){draw_clicking("+");};
    public void click_minus(View set){draw_clicking("-");};
    public void click_div(View set){draw_clicking("/");};
    public void click_mul(View set){draw_clicking("*");};
    public void click_left_bracket(View set){draw_clicking("(");};
    public void click_right_bracket(View set){draw_clicking(")");};
    public void click_point(View set){draw_clicking(".");};
    public void click_CE(View set){
        temp_exprestion = "";
        exprestion.setText(temp_exprestion);
    };
    public void click_C(View set){
        if (temp_exprestion.equals(""))
            return;
        temp_exprestion = temp_exprestion.substring(0, temp_exprestion.length() - 1);
        exprestion.setText(temp_exprestion);
    };

    public void click_evaluate(View set){
        try{
            Double answer = (double) (Math.round(plus_priority(temp_exprestion) * 10000000));
            answer /= 10000000;
            temp_exprestion = answer.toString();
            exprestion.setText(answer.toString());
        }catch (Parser_exception e){
            Toast aiaiaia = Toast.makeText(getApplicationContext(), e.msg, Toast.LENGTH_LONG);
            aiaiaia.setGravity(Gravity.CENTER, 0, 0);
            aiaiaia.show();
            return;
        }catch (Division_by_zero e){
            Toast aiaiaia = Toast.makeText(getApplicationContext(), e.msg, Toast.LENGTH_LONG);
            aiaiaia.setGravity(Gravity.CENTER, 0, 0);
            aiaiaia.show();
            return;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        exprestion  = (TextView) findViewById(R.id.textView);
    }




}
