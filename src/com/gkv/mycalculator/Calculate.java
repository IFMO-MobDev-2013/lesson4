package com.gkv.mycalculator;

import android.util.Log;

public class Calculate {
	CharSequence res;
	private static final String TAG = "myLogs";
	

	public CharSequence solve(CharSequence cs) {
		double q = rec(cs.toString());
		if (q == 1e300)
			return "Ошибка";
		else
			return Double.toString(q);
	}

	double rec(String ss) {
		Log.d(TAG, ss);
		int balance = 0, error = 0;
		for (int i = 0; i < ss.length(); i++)
			if (ss.charAt(i) == '(') {
				balance++;
			} else if (ss.charAt(i) == ')') {
				if (balance == 0) {
					error = 1;
					break;
				}
				balance--;
			}
		if (balance > 0)
			error = 1;
		if (error == 1)
			return 1e300;
		if (ss.charAt(0) == '(' && ss.charAt(ss.length() - 1) == ')') {
			if (ss.length() == 2 && ss.charAt(0) == '(' && ss.charAt(1) == ')') return 1e300;
			int balance1 = 0, error1 = 0;
			for (int i = 1; i < ss.length() - 1; i++)
				if (ss.charAt(i) == '(') {
					balance1++;
				} else if (ss.charAt(i) == ')') {
					if (balance1 == 0) {
						error1 = 1;
						break;
					}
					balance1--;
				}
			if (balance1 > 0)
				error1 = 1;
			if (error1 == 0)
				return rec(ss.substring(1, ss.length() - 1));
		}
		int bal = 0, position = -1;
		for (int i = 0; i < ss.length(); i++) {
			if (ss.charAt(i) == '(')
				bal++;
			if (ss.charAt(i) == ')')
				bal--;
			if (bal == 0 && (ss.charAt(i) == '*' || ss.charAt(i) == '/')) {
				position = i;
			}

		}

		for (int i = 0; i < ss.length(); i++) {
			if (ss.charAt(i) == '(')
				bal++;
			if (ss.charAt(i) == ')')
				bal--;
			if (bal == 0 && (ss.charAt(i) == '+' || ss.charAt(i) == '-')) {
				position = i;
			}

		}
		Log.d(TAG, ss);
		if (position == -1) {
			
			if (ss.length() == 2 && ss.charAt(0) == '(' && ss.charAt(1) == ')') return 1e300; 
			double x = 0;
			double ff = 0;
			for (int i = 0; i < ss.length(); i++)
				if ((ss.charAt(i) < '0' || ss.charAt(i) > '9')
						&& !(ss.charAt(i) == '.')) {
					return 1e300;
				} else {
					if (ss.charAt(i) == '.') {
						if (ff != 0)
							return 1e300;
						else
							ff = 1;
					} else {
						if (ff == 0)
							x = x * 10 + ss.charAt(i) - '0';
						else {
							ff = ff * 10;
							x = x + (ss.charAt(i) - '0') / ff;

						}
					}
				}
			return x;
		}
		if (position == 0 && (ss.charAt(0) == '*' || ss.charAt(0) == '/'))
			return 1e300;
		double cur1, cur2;
		if (position == 0) {
			cur1 = 0;

		} else {
			Log.d(TAG, "J");
			Log.d(TAG, ss);
			cur1 = rec(ss.substring(0, position));
		}
		if(position == ss. length() - 1) return 1e300;
		cur2 = rec(ss.substring(position + 1, ss.length()));
		if (cur1 > 1e299 || cur2 > 1e299)
			return 1e300;
		if (ss.charAt(position) == '/' && Math.abs(cur2) < 1e-15){
			return 1e300;
        }
		if (ss.charAt(position) == '+')
			return cur1 + cur2;
		if (ss.charAt(position) == '*')
			return cur1 * cur2;
		if (ss.charAt(position) == '/')
			return cur1 / cur2;
		if (ss.charAt(position) == '-')
			return cur1 - cur2;
		return 1e300;
	}

}
