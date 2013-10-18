package com.example.h4_calculator;
import java.util.Stack;

public class PollandParcer {

    public static boolean isNum(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static boolean isOp(char c)
    {
        return ((c == '+')||(c == '-')||(c == '*')||(c == '/'));
    }

    public static int priority(char c)
    {
        if ((c == '+')||(c == '-'))
            return 1;
        else if (c == '^')
            return 3;
        return 2;
    }

    public static String parse(String s)
    {
        String[] str = s.split(" ");
        String result = "";
        Stack<Character> stack = new Stack();
        char c = str[0].charAt(0);
        for (int i = 0; i < str.length; i++)
        {
            if (isNum(str[i]))
                result = result + ' ' + str[i];
            else
            {
                c = str[i].charAt(0);
                switch(c){
                    case '(':
                    {
                        stack.push(c);
                        break;
                    }
                    case ')':
                    {
                        while (stack.peek() != '(')
                            result = result + ' ' + stack.pop();
                        stack.pop();
                        break;
                    }
                    default:
                    {
                        if (stack.empty())
                            stack.push(c);
                        else
                        {
                            char d =stack.peek();
                            if (d == '(')
                            {
                                stack.push(c);
                                continue;
                            }
                            if (priority(c) <= priority(d))
                            {
                                result = result + ' ' + stack.peek();
                                stack.pop();
                                if (stack.empty())
                                {
                                    stack.push(c);
                                    continue;
                                }
                                d = stack.peek();
                                if (d == '(')
                                {
                                    stack.push(c);
                                    continue;
                                }
                            }
                            stack.push(c);
                        }
                        break;
                    }
                }
            }
        }
        while (!stack.empty())
        {
            result = result + ' ' + stack.peek();
            stack.pop();
        }
        return result;
    }

    public static String evaluate(String s)
    {
        Stack<Double> stack = new Stack();
        String[] str = s.split(" ");
        String result = "";
        char c;
        //System.out.println(str[1]);
        for (int i = 1; i < str.length; i++)
        {
            if (isNum(str[i]))
                stack.push(Double.parseDouble(str[i]));
            else
            {
                double a,b;
                a = stack.peek();
                stack.pop();
                b = stack.peek();
                stack.pop();
                c = str[i].charAt(0);
                switch(c)
                {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(b - a);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(b / a);
                        break;
                    case '^':
                        stack.push(Math.pow(b,a));
                        break;
                }
            }
        }
        result = stack.peek().toString();
        return result;
    }

    public static String makeSpaces(String s)
    {
        String buff1,buff2 = "";
        for (int i = 0; i < s.length(); i++)
        {
            if ((s.charAt(i) == '(')||(s.charAt(i) == ')'))
            {
                if ((i != 0)&&(s.charAt(i-1) != ' '))
                {
                    buff1 = s.substring(0, i);
                    buff2 = s.substring(i + 1);
                    s = buff1 + " " + s.substring(i, i + 1) + buff2;
                    i++;
                }
                if ((i != s.length() - 1)&&(s.charAt(i+1) != ' '))
                {
                    buff1 = s.substring(0, i);
                    buff2 = s.substring(i + 1);
                    s = buff1 + s.substring(i, i + 1) + " " + buff2;
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "45 + (322.15 + 34)";
        s = makeSpaces(s);
        System.out.println(s);
        String result = parse(s);
        System.out.println(result);
        String otvet = evaluate(result);
        System.out.println(otvet);

    }

}

