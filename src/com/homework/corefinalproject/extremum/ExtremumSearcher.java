package com.homework.corefinalproject.extremum;

import com.homework.corefinalproject.parser.function.Function;

public class ExtremumSearcher {

    public double findExtremum(double a, double b, ExtremumTypes extremumType, double e, Function userInputFunction) {

        double firstTempFunction, secondTempFunction;
        double resultFunction = 0;

        double c = extremumType.equals(ExtremumTypes.MAX) ? -1 : 1;
        double x = 0;

        boolean accuracyLimitNotReachedYet = true;

        while (accuracyLimitNotReachedYet) {
            x = (a + b) / 2;
            userInputFunction.setX(x - e);
            firstTempFunction = userInputFunction.calculate();
            userInputFunction.setX(x + e);
            secondTempFunction = userInputFunction.calculate();

            if (c * firstTempFunction < c * secondTempFunction) {
                b = x;
            } else {
                a = x;
            }
            if (Math.abs(b - a) < e) {
                x = (a + b) / 2;
                userInputFunction.setX(x);
                resultFunction = userInputFunction.calculate();
                accuracyLimitNotReachedYet = false;
            } else {
                accuracyLimitNotReachedYet = true;
            }
        }

        return resultFunction;
    }

}
