package org.todos;

import java.util.*;

//TODO: Fix bugs with division not being handled properly
//TODO: Fix function so it is not static

public class CalcLogic {

    // Method to evaluate the expression
    public double evaluate(String expression) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        parseExpression(expression, numbers, operators);

        // Handle multiplication and division first
        processMultiplicationAndDivision(numbers, operators);

        // Now handle addition and subtraction
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            double number = numbers.get(i + 1);

            if (operator == '+') {
                result = result + number;
            } else if (operator == '-') {
                result = result - number;
            } else {
                throw new IllegalStateException("Unexpected operator: " + operator);
            }
        }

        return result;
    }

    // Method to parse the expression into numbers and operators
    private static void parseExpression(String expression, List<Double> numbers, List<Character> operators) {
        String numberBuffer = "";

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                numberBuffer += c;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (!numberBuffer.isEmpty()) {
                    numbers.add(Double.parseDouble(numberBuffer));
                    numberBuffer = "";
                }
                operators.add(c);
            }
        }

        if (!numberBuffer.isEmpty()) {
            numbers.add(Double.parseDouble(numberBuffer));
        }
    }

    // Method to process multiplication and division
    private static void processMultiplicationAndDivision(List<Double> numbers, List<Character> operators) {
        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);

            if (operator == '*' || operator == '/') {
                double num1 = numbers.get(i);
                double num2 = numbers.get(i + 1);
                double result = 0;

                if (operator == '*') {
                    result = num1 * num2;
                } else if (num2 != 0) {
                    result = (num1 / num2);
                } else {
                    throw new IllegalStateException("ERROR Denominator can't be 0 " + operator);
                }

                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;  // Adjust the index due to list modification
            }
        }
    }
}
