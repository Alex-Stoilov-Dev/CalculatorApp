package org.todos;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int add(int a, int b) {return a + b;}
    public static int subtract(int a, int b) {return a - b;}
    public static int multiply (int a, int b) {return a * b;}
    public static double divide(double a, double b) {return a / b;}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalcLogic calcLogic = new CalcLogic();

        System.out.println("Enter expression: ");
        String expression = scanner.nextLine();

        double result = calcLogic.evaluate(expression);

        System.out.println("Your result is: " + result);
        System.out.println("====================================");
    }

}

