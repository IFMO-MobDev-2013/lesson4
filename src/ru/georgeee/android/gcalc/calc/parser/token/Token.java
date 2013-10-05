package ru.georgeee.android.gcalc.calc.parser.token;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 8:51
 * To change this template use File | Settings | File Templates.
 */
public class Token{
        public Token(int start, int end,TokenType type) {
            this.type = type;
            this.start = start;
            this.end = end;
        }

        public Token(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public TokenType type = null;
        public int start, end;
}
