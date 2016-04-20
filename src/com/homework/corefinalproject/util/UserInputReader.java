package com.homework.corefinalproject.util;

public class UserInputReader {

    public static double parseDouble(String userInputText, String fieldName) {

        double result = 0;

        try {
            result = Double.parseDouble(userInputText);
        } catch (Exception e) {
            throw new IllegalArgumentException(fieldName + ": value should be double: "
                    + userInputText);
        }

        return result;
    }

    public static int parseInt(String userInputText, String fieldName) {

        int result = 0;

        try {
            result = Integer.parseInt(userInputText);
        } catch (Exception e) {
            throw new IllegalArgumentException(fieldName + ": value should be int: "
                    + userInputText);
        }

        return result;
    }



}