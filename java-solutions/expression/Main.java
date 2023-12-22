package expression;

import expression.exceptions.CheckedLog2;
import expression.exceptions.CheckedPow2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int value = input.nextInt();
        System.out.println(new CheckedPow2(new Variable("x"))
                .evaluate(value));
    }
}