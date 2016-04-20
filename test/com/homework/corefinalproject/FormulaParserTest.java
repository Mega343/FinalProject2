package com.homework.corefinalproject;

import com.homework.corefinalproject.parser.formula.FormulaParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;


public class FormulaParserTest {
    static ArrayList<String> userInput = new ArrayList<>();

    @BeforeClass
    public static void setUp() throws Exception {

        userInput.add("-5*x +2*x");
        userInput.add("12*x-x^2");
        userInput.add("10*cos(x+2)");
        userInput.add("-10*cos(x^3 - x) + sqrt(5-x)");
        userInput.add("5^e - 3*x + cos(2*pi)");

    }

    @Test
    public void testParseFormulaTextIntoBlocks() throws Exception {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("[{-1, NUMBER}, {*, OPERATOR}, {5, NUMBER}, {*, OPERATOR}, {x, OTHER}, {+, OPERATOR}, {2, NUMBER}, {*, OPERATOR}, {x, OTHER}]");
        expectedList.add("[{12, NUMBER}, {*, OPERATOR}, {x, OTHER}, {-, OPERATOR}, {x, OTHER}, {^, OPERATOR}, {2, NUMBER}]");
        expectedList.add("[{10, NUMBER}, {*, OPERATOR}, {cos, OTHER}, {(, OPEN_BRACKET}, {x, OTHER}, {+, OPERATOR}, {2, NUMBER}, {), CLOSE_BRACKET}]");
        expectedList.add("[{-1, NUMBER}, {*, OPERATOR}, {10, NUMBER}, {*, OPERATOR}, {cos, OTHER}, {(, OPEN_BRACKET}, {x, OTHER}," +
                " {^, OPERATOR}, {3, NUMBER}, {-, OPERATOR}, {x, OTHER}, {), CLOSE_BRACKET}, {+, OPERATOR}, {sqrt, OTHER}, {(, OPEN_BRACKET}, {5, NUMBER}, {-, OPERATOR}, {x, OTHER}, {), CLOSE_BRACKET}]");
        expectedList.add("[{5, NUMBER}, {^, OPERATOR}, {e, OTHER}, {-, OPERATOR}, {3, NUMBER}, {*, OPERATOR}, {x, OTHER}," +
                " {+, OPERATOR}, {cos, OTHER}, {(, OPEN_BRACKET}, {2, NUMBER}, {*, OPERATOR}, {pi, OTHER}, {), CLOSE_BRACKET}]");

        int i = 0;
        for (String s : userInput) {
            FormulaParser formulaParser = new FormulaParser(s);
            formulaParser.parseFormulaTextIntoBlocks();
            Assert.assertEquals(expectedList.get(i).toString(), formulaParser.getParsedFormulaText().toString());
            i++;
        }
    }

    @Test
    public void testConvertParsedTextIntoFormulaElements() throws Exception {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("[-1.0, MULT, 5.0, MULT, x, PLUS, 2.0, MULT, x]");
        expectedList.add("[12.0, MULT, x, MINUS, x, POW, 2.0]");
        expectedList.add("[10.0, MULT, COS, OPEN_BRACKET, x, PLUS, 2.0, CLOSE_BRACKET]");
        expectedList.add("[-1.0, MULT, 10.0, MULT, COS, OPEN_BRACKET, x, POW, 3.0, MINUS, x," +
                " CLOSE_BRACKET, PLUS, SQRT, OPEN_BRACKET, 5.0, MINUS, x, CLOSE_BRACKET]");
        expectedList.add("[5.0, POW, E, MINUS, 3.0, MULT, x, PLUS, COS, OPEN_BRACKET, 2.0, MULT, PI, CLOSE_BRACKET]");


        for (int i = 0; i < userInput.size(); i++) {
            FormulaParser formulaParser = new FormulaParser(userInput.get(i));
            formulaParser.parseFormulaTextIntoBlocks();
            formulaParser.convertParsedTextIntoFormulaElements();
            Assert.assertEquals(expectedList.get(i).toString(), formulaParser.getFormulaIN().toString());

        }
    }

    @Test
    public void testConvertFormulaToRPN() throws Exception {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("[-1.0, 5.0, MULT, x, MULT, 2.0, x, MULT, PLUS]");
        expectedList.add("[12.0, x, MULT, x, 2.0, POW, MINUS]");
        expectedList.add("[10.0, x, 2.0, PLUS, COS, MULT]");
        expectedList.add("[-1.0, 10.0, MULT, x, 3.0, POW, x, MINUS, COS, MULT, 5.0, x, MINUS, SQRT, PLUS]");
        expectedList.add("[5.0, E, POW, 3.0, x, MULT, MINUS, 2.0, PI, MULT, COS, PLUS]");
        for (int i = 0; i < userInput.size(); i++) {
            FormulaParser formulaParser = new FormulaParser(userInput.get(i));
            formulaParser.parseFormulaTextIntoBlocks();
            formulaParser.convertParsedTextIntoFormulaElements();
            formulaParser.convertFormulaToRPN();
            Assert.assertEquals(expectedList.get(i).toString(), formulaParser.getFormulaRPN().toString());

        }
    }
}