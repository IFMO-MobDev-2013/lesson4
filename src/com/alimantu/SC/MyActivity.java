package com.alimantu.SC;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    TextView t;
    TextView t_answer;
    Button b_point;
    Button b_opening_bracket;
    Button b_closing_bracket;

    String input = new String("");
    String output = new String("");

    element[] elem = new element[10000];
    int pointer = 0;
    element curr_elem = new element();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SCalc();
        t = (TextView)findViewById(R.id.inputText);
        t_answer = (TextView)findViewById(R.id.answer);
        b_point = (Button)findViewById(R.id.b_point);
        b_opening_bracket= (Button)findViewById(R.id.b_opening_bracket);
        b_closing_bracket= (Button)findViewById(R.id.b_closing_bracket);
        curr_elem.create(false, false, false, false, false, false, true, 0);

    }

    private void SCalc(){

    }

    public void digits(String s, View view)
    {
        if(!curr_elem.closing_bracket)
        {
            if(curr_elem.first_zero)
                on_bs_Click(view);

            input = input + s;
            t.setText(input);

            b_opening_bracket.setEnabled(false);
            b_point.setEnabled(!curr_elem.point);
            b_closing_bracket.setEnabled(curr_elem.bracket_count != 0);

            element tmp = new element();
            tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

            tmp.first_zero = false;
            tmp.empty_str = false;
            tmp.operation = false;
            tmp.closing_bracket = false;
            tmp.numb = true;

            curr_elem = tmp;
            elem[pointer++] = tmp;
        }
    }

    public void operation(String s, View view, boolean type)
    {
        if(curr_elem.empty_str)
        {
            if(type) on_1_Click(view);
            else on_0_Click(view);
        }
        if(curr_elem.operation)
            on_bs_Click(view);

        input = input + s;
        t.setText(input);


        b_opening_bracket.setEnabled(true);
        b_point.setEnabled(true);
        b_closing_bracket.setEnabled(false);

        element tmp = new element();
        tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

        tmp.numb = false;
        tmp.point = false;
        tmp.operation = true;
        tmp.first_zero = false;
        tmp.closing_bracket = false;
        tmp.empty_str = false;

        curr_elem = tmp;
        elem[pointer++] = tmp;
    }

    public void on_Equally_Click (View view)
    {
        if(curr_elem.bracket_count != 0)
        {
            output = "Wrong brackets count!";
            t_answer.setText(output);
        }
        else if(curr_elem.empty_str)
        {
            output = "0";
            t_answer.setText(output);
        }
        else if(curr_elem.operation)
        {
            output = "Operation in the end!";
            t_answer.setText(output);
        }
        else
        {
            Parser parser = new Parser();
            try {

                double answer = parser.evaluate(input);
                t_answer.setText(Double.toString(answer));

            }
            catch (ParserException e)
            {
                t_answer.setText(e.toString());
            }
        }
    }

    public void on_0_Click (View view)
    {
        if(!curr_elem.first_zero && !curr_elem.closing_bracket)
        {
            input = input + "0";
            t.setText(input);

            b_opening_bracket.setEnabled(false);
            b_closing_bracket.setEnabled(curr_elem.bracket_count != 0);
            b_point.setEnabled(!curr_elem.point);

            element tmp = new element();
            tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

            tmp.first_zero = tmp.empty_str || tmp.operation;
            tmp.empty_str = false;
            tmp.operation = false;
            tmp.closing_bracket = false;
            tmp.numb = true;

            curr_elem = tmp;
            elem[pointer++] = tmp;
        }
    }

    public void on_1_Click (View view)
    {
        if(!curr_elem.closing_bracket)
        {
            if(curr_elem.first_zero)
                on_bs_Click(view);

            input = input + "1";
            t.setText(input);

            b_opening_bracket.setEnabled(false);
            b_point.setEnabled(!curr_elem.point);
            b_closing_bracket.setEnabled(curr_elem.bracket_count != 0);

            element tmp = new element();
            tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

            tmp.first_zero = false;
            tmp.empty_str = false;
            tmp.operation = false;
            tmp.closing_bracket = false;
            tmp.numb = true;

            curr_elem = tmp;
            elem[pointer++] = tmp;
        }
    }

    public void on_2_Click (View view)
    {
        if(!curr_elem.closing_bracket)
        {
            if(curr_elem.first_zero)
                on_bs_Click(view);

            input = input + "2";
            t.setText(input);

            b_opening_bracket.setEnabled(false);
            b_point.setEnabled(!curr_elem.point);
            b_closing_bracket.setEnabled(curr_elem.bracket_count != 0);

            element tmp = new element();
            tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

            tmp.first_zero = false;
            tmp.empty_str = false;
            tmp.operation = false;
            tmp.closing_bracket = false;
            tmp.numb = true;

            curr_elem = tmp;
            elem[pointer++] = tmp;
        }
    }

    public void on_3_Click (View view)
    {
        if(!curr_elem.closing_bracket)
        {
            if(curr_elem.first_zero)
                on_bs_Click(view);

            input = input + "3";
            t.setText(input);

            b_opening_bracket.setEnabled(false);
            b_point.setEnabled(!curr_elem.point);
            b_closing_bracket.setEnabled(curr_elem.bracket_count != 0);

            element tmp = new element();
            tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

            tmp.first_zero = false;
            tmp.empty_str = false;
            tmp.operation = false;
            tmp.closing_bracket = false;
            tmp.numb = true;

            curr_elem = tmp;
            elem[pointer++] = tmp;
        }
    }

    public void on_4_Click (View view)
    {
        digits("4",view);
    }

    public void on_5_Click (View view)
    {
        digits("5",view);
    }

    public void on_6_Click (View view)
    {
        digits("6",view);
    }

    public void on_7_Click (View view)
    {
        digits("7",view);
    }

    public void on_8_Click (View view)
    {
        digits("8",view);
    }

    public void on_9_Click (View view)
    {
        digits("9",view);
    }

    public void on_add_Click (View view)
    {
        if(curr_elem.empty_str)
            on_0_Click(view);
        if(curr_elem.operation)
            on_bs_Click(view);

        input = input + "+";
        t.setText(input);


        b_opening_bracket.setEnabled(true);
        b_point.setEnabled(true);
        b_closing_bracket.setEnabled(false);

        element tmp = new element();
        tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

        tmp.numb = false;
        tmp.point = false;
        tmp.operation = true;
        tmp.first_zero = false;
        tmp.closing_bracket = false;
        tmp.empty_str = false;

        curr_elem = tmp;
        elem[pointer++] = tmp;
    }

    public void on_sub_Click (View view)
    {
        operation("-", view, false);
    }

    public void on_div_Click (View view)
    {
        operation("/", view, true);
    }

    public void on_mult_Click (View view)
    {
        operation("*", view, true);
    }

    public void on_point_Click(View view)
    {
        if(curr_elem.empty_str || curr_elem.operation)
            on_0_Click(view);
        input = input + ".";
        t.setText(input);

        b_opening_bracket.setEnabled(false);
        b_point.setEnabled(false);

        element tmp = new element();
        tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

        if(tmp.operation || tmp.empty_str)
            on_0_Click(view);

        tmp.numb = true;
        tmp.point = true;
        tmp.operation = false;
        tmp.first_zero = false;
        tmp.closing_bracket = false;
        tmp.empty_str = false;

        curr_elem = tmp;
        elem[pointer++] = tmp;
    }

    public void on_opening_bracket_Click(View view)
    {
        input = input + "(";
        t.setText(input);

        b_closing_bracket.setEnabled(true);
        b_point.setEnabled(true);

        element tmp = new element();
        tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

        tmp.numb = false;
        tmp.point = false;
        tmp.operation = false;
        tmp.first_zero = false;
        tmp.opening_bracket = true;
        tmp.closing_bracket = false;
        tmp.empty_str = true;
        tmp.bracket_count++;

        curr_elem = tmp;
        elem[pointer++] = tmp;
    }

    public void on_closing_bracket_Click(View view)
    {
        if(curr_elem.empty_str)
            on_0_Click(view);

        input = input + ")";
        t.setText(input);

        b_opening_bracket.setEnabled(false);
        b_closing_bracket.setEnabled(curr_elem.bracket_count != 1);
        b_point.setEnabled(false);

        element tmp = new element();
        tmp.create(curr_elem.numb, curr_elem.point, curr_elem.operation, curr_elem.first_zero, curr_elem.opening_bracket, curr_elem.closing_bracket ,curr_elem.empty_str, curr_elem.bracket_count);

        tmp.numb = false;
        tmp.point = false;
        tmp.operation = false;
        tmp.first_zero = false;
        tmp.opening_bracket = tmp.bracket_count != 1;
        tmp.closing_bracket = true;
        tmp.empty_str = false;
        tmp.bracket_count--;

        curr_elem = tmp;
        elem[pointer++] = tmp;
    }

    public void on_bs_Click(View view)
    {
        if(pointer == 1)
            on_c_Click(view);
        else if(pointer != 0)
        {
            input = input.substring(0,input.length()-1);
            t.setText(input);

            pointer--;
            element tmp = elem[pointer - 1];

            b_point.setEnabled(!((tmp.point || (!tmp.numb  && !tmp.operation)) && !tmp.empty_str));
            b_opening_bracket.setEnabled(tmp.operation || tmp.empty_str);
            b_closing_bracket.setEnabled(tmp.opening_bracket);

            curr_elem = tmp;
            Log.d("pp", input);
        }
    }

    public void on_c_Click(View view)
    {
        input = "";
        t.setText(input);
        pointer = 0;
        curr_elem.create(false, false, false, false, false, false, true, 0);
        b_point.setEnabled(true);
        b_closing_bracket.setEnabled(false);
        b_opening_bracket.setEnabled(true);
    }

}
