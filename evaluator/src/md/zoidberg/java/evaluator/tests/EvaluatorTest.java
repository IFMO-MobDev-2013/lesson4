/*
 * Copyright 2002-2007 Robert Breidecker.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package md.zoidberg.java.evaluator.tests;

import junit.framework.TestCase;
import md.zoidberg.java.evaluator.EvaluationException;
import md.zoidberg.java.evaluator.Evaluator;

public class EvaluatorTest extends TestCase {
    public EvaluatorTest(String arg0) {
        super(arg0);
    }

    public String evaluate(String expression) throws EvaluationException {
        Evaluator evaluator = new Evaluator();
        return evaluator.evaluate(expression);
    }

    public void testEvaluateMathematicalOperations() {
        try {
            assertException("-");

            assertException("()");
            assertException("((");
            assertException("(+)");
            assertException("(-3)(-3)");
            assertException("(--3)");
            assertException("-(3)-");
            assertException("*7*(3+3)");
            assertException(") + (3)");
            assertException("(7 + (7 + 3)");
            assertException("9-");
            assertException("1-1(4)");
            assertException("3)");
            assertException("(3");
            assertException("7.0.0");
            assertException("(");
            assertException("(-.-)");
            assertException("");

            assertEquals("-6.0", evaluate("-3-3.0"));
            assertEquals("7.0", evaluate("-(+(-(+7)))"));
            assertEquals("-0.2", evaluate("1/-5"));
            assertEquals("-236.46607048346377", evaluate("72.0/532*729*177/699-267.0*471/481"));
            assertEquals("-789.9887152777778", evaluate("-(+(-18.0/36*(10.0+13.0/36/16))+(-21.0/7*24+21.0*41+6.0))"));
            assertException("-25/(21-7*3)");
            assertEquals("6.9", evaluate("7-.1"));
            assertEquals("-8.0", evaluate("-(-(-8))"));
            assertEquals("9.0", evaluate("9."));
            assertEquals("3.0", evaluate("+3"));


        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Convienence method for assert that an evaluation exception has occurred.
     *
     * @param expression
     */
    private void assertException(String expression) {
        try {
            evaluate(expression);
            throw new AssertionError(
                    "The expression should have thrown an exception.");
        } catch (EvaluationException ee) {
            // If an exception is thrown, then we have been successful.
        } catch (ArithmeticException ee) {

        }
    }
}