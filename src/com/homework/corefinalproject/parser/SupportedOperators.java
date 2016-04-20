package com.homework.corefinalproject.parser;

public enum SupportedOperators {
    OPEN_BRACKET(0), CLOSE_BRACKET(1),
    PLUS(2), MINUS(2),
    MULT(3), DIV(3),
    POW(4), SQRT(5), SIN(5), COS(5), TN(5), LN(5), LG(5),
    E(0), PI (0);

    private int priority;

    SupportedOperators(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}