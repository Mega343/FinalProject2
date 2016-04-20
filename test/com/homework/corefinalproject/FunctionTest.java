package com.homework.corefinalproject;

import com.homework.corefinalproject.parser.formula.FormulaParser;
import com.homework.corefinalproject.parser.function.Function;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class FunctionTest {
    Function function = new Function();


    @Test(expected = UnsupportedOperationException.class)
    public void testCalculateException() throws Exception {
        function.calculate();

    }

    @Test
    public void testCalculate() throws Exception {

        FormulaParser parser = new FormulaParser("x*2");
        Function function = parser.parse();
        function.setX(5);
        double expectedResult = 10.0;

        double result = function.calculate();
        Assert.assertEquals(expectedResult, result);
    }
}