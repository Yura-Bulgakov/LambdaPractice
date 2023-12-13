package ru.example;

import java.util.Scanner;

public class Main {
    static Calculator calculator = new Calculator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String expression = scanner.nextLine();
        System.out.println("Результат: " + calculator.solveExpression(expression));
    }
}