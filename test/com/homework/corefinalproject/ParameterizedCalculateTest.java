package com.homework.corefinalproject;

import com.homework.corefinalproject.extremum.ExtremumSearcher;
import com.homework.corefinalproject.extremum.ExtremumTypes;
import com.homework.corefinalproject.parser.formula.FormulaParser;
import com.homework.corefinalproject.parser.function.Function;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ParameterizedCalculateTest {

    private double a, b, e, expected;
    private ExtremumTypes c;
    private String function;


    public ParameterizedCalculateTest(double a, double b, ExtremumTypes c, double e, String function, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.expected = expected;
        this.function = function;


    }


    @Parameterized.Parameters(name = "{index}: {4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 5, ExtremumTypes.MIN, 0.2, "10*x - 3*x^2", -23.455810546875},      //0   10*x - 3*Math.pow(x,2)
                {-1, 1, ExtremumTypes.MIN, 0.001, "cos(x)*cos(x) + sin(x)*sin(x)", 1},  //ExtremumTypes.MIN Math.cos(x)*Math.cos(x) + Math.sin(x)*Math.sin(x)
                {-1, 1, ExtremumTypes.MIN, 0.001, "cos(x)^2", 0.2923706737571799},      //2   Math.pow(Math.cos(x),2)
                {-1, 1, ExtremumTypes.MAX, 0.001, "sin(x)^2", 0.7076293262428202},     //3   Math.pow(Math.sin(x),2)
                {-1, 1, ExtremumTypes.MAX, 0.001, "tn(x)^2", 2.4203156806024992},      //4   Math.pow(Math(tan(x),2)
                {0, 10, ExtremumTypes.MIN, 0.0001, "(x^2+2)/(x^2 - 6*x +8)", 2.1250077486559276}, //5  (Math.pow(x,2) + 2)/(Math.pow(x,2) -6*x + 8)
                {0, 10, ExtremumTypes.MIN, 0.0001, "2^(-1/(x-3))", 1.2599247515207448},  //6  Math.pow(2, (-1/(x-3)))
                {0, 10, ExtremumTypes.MIN, 0.0001, "x^6 - 3*x^5 + 2*x^4 - 12*x^3 + x/2", -173.5335283324981}, //7 Math.pow(x,6) - 3*Math.pow(x,5) + 2*Math.pow(x,4) -12*Math.pow(x,3) + x/2
                {0, 10, ExtremumTypes.MIN, 0.0001, "x^(5*x)", 0.1589131900388006},       //8  Math.pow(x, 5*x)
                {-1, 1, ExtremumTypes.MIN, 0.1, "tn(x)", -1.4552966624690729},           //9  Math.tan(x)
                {-1, 1, ExtremumTypes.MIN, 0.1, "cos(x)", 0.5663301963933087},           //10 Math.cos(x)
                {-1, 1, ExtremumTypes.MIN, 0.001, "sin(x)", -0.8412070650219363},        //11 Math.sin(x)
                {-5, 5, ExtremumTypes.MIN, 0.001, "4^(-x)", 9.769757353209631E-4},         //12 Math.pow(4, -x)
                {-5, 5, ExtremumTypes.MIN, 0.001, "4^cos(x-1)", 0.25000000525829097},         //13 Math.pow(4, Math.cos(x-1))
                {-5, 5, ExtremumTypes.MIN, 0.001, "sqrt(x+2)", 0.028168369339562415},         //14 Math.sqrt(x+2)
                {0, 1, ExtremumTypes.MIN, 0.001, "x^6 * sqrt(x+2) - 8/(4*x^2-7)", 1.1428572985590932}, // 15 Math.pow(x,6)*Math.sqrt(4-Math.pow(x,2)) - 8/(4*Math.pow(x,2) -7)//15 Math.sqrt(x+2)
                {0, 1, ExtremumTypes.MIN, 0.001, "x^6 * sqrt(x+2) - 8/(4*x^2-7)^2", -0.1975237948833087}, // 16 Math.pow(x,6)*Math.sqrt(4-Math.pow(x,2)) - 8/Math.pow((4*Math.pow(x,2) -7),5)
                {-10, 0, ExtremumTypes.MIN, 0.001, "x^5/sqrt(1-x)", -30146.952230724823}, // 17 Math.pow(x,5)/Math.sqrt(1-x)
                {-1, 1, ExtremumTypes.MIN, 0.001, "cos(x)^ tn(x)", 0.3842084379981062}, // 18 Math.pow(Math.cos(x),Math.tan(x))
                {-1, 1, ExtremumTypes.MIN, 0.001, "ln(5*x+9)", 1.3869045264936322}, // 19 Math.log(5*x+9)
                {-1, 1, ExtremumTypes.MIN, 0.001, "sqrt(ln(5*x+9))", 1.177669107386974}, // 20 Math.sqrt(Math.log(5*x+9))
                {-1, 1, ExtremumTypes.MIN, 0.001, "lg(5*x+9)", 0.6023249827828269}, // 21 Math.log10(5*x+9)
                {-1, 1, ExtremumTypes.MIN, 0.001, "x/sin(x^2+3) + ln(cos(4*x))", 26.276369699719446}, // 22 x/Math.sin(Math.pow(x,2)+3) + Math.log(Math.cos(4*x));

        });
    }

    @Test(timeout = 2000)
    public void testCalculate() throws Exception {
        ExtremumSearcher calculate = new ExtremumSearcher();
        FormulaParser parser = new FormulaParser(function);
        Function function = parser.parse();
        double func = calculate.findExtremum(a, b, c, e, function);
        Assert.assertEquals(expected, func);
    }
}