package com.example.HW4;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.Test;
/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 10.10.13
 * Time: 20:25
 * To change this template use File | Settings | File Templates.
 */
public class Junit_tests{
    public static class Junits_tests extends TestCase {
        Double answer = null;
        private static final double EPSILON = 0.0000001;
        MyActivity act = new MyActivity();
        Junits_tests(String value){
            super(value);
        }

        public void tests(String value, double result){
            try{
                answer = act.plus_priority(value);
            } catch (MyActivity.Division_by_zero e){
                e.printStackTrace();
            } catch (MyActivity.Parser_exception e){
                e.printStackTrace();
            }
            assertEquals(result, answer, EPSILON);
        }
        @Test
        public void my_global_test(){
            tests("-1/(11-12*3+(-12)*(-2))", 1);
        }

        @Test
        public void my_test_sum(){
            tests("1+(-1)", 0.0);
        }

        @Test
        public void my_test_minus(){
            tests("1-1)", 0.0);
        }

        @Test
        public void my_test_division(){
            tests("1/0.5)", 2.0);
        }

        @Test
        public void my_test_multiplication(){
            tests("2*4.1", 8.2);
        }

        @Test
        public void my_test_skobochka(){
            tests("(0+1)*(1+2)", 3.0);
        }
    }

    public static void main(){
        TestRunner my_run = new TestRunner();
        TestSuite my_suite = new TestSuite();
        my_suite.addTest(new Junits_tests("my_global_test"));
        my_suite.addTest(new Junits_tests("my_test_sum"));
        my_suite.addTest(new Junits_tests("my_test_minus"));
        my_suite.addTest(new Junits_tests("my_test_division"));
        my_suite.addTest(new Junits_tests("my_test_multiplication"));
        my_suite.addTest(new Junits_tests("my_test_skobochka"));
        my_run.doRun(my_suite);
    }
}
