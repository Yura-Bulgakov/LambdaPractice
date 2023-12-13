package ru.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExpressionConvertor {
    public String convertToPostfix(String infixExpression){
        StringBuilder postfixExpression = new StringBuilder();
        Deque<Character> operatorStack = new ArrayDeque<>();
        boolean unaryMinus = true;
        for (char symbol: infixExpression.toCharArray()) {
            if (Character.isDigit(symbol)){
                postfixExpression.append(symbol);
                unaryMinus = false;
            } else if (symbol == '(') {
                if (unaryMinus){
                    postfixExpression.append("0 ");
                }
                operatorStack.push(symbol);
                unaryMinus = true;
            } else if (symbol == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '('){
                    postfixExpression.append(operatorStack.pop()).append(" ");
                }
                if (operatorStack.isEmpty()){
                    throw new IllegalArgumentException("Внутри скобок отсутствуют операторы");
                }
                operatorStack.pop();
                unaryMinus = false;
            } else if (isOperator(symbol)){
                if (unaryMinus){
                    postfixExpression.append("0 ");
                    unaryMinus = false;
                }
                while (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(symbol)){
                    postfixExpression.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(symbol);
                unaryMinus = true;
            } else {
                throw new IllegalArgumentException("недопустимый символ: " + symbol);
            }
        }

        while (!operatorStack.isEmpty()){
            char operator = operatorStack.pop();
            if (operator == '('){
                throw new IllegalArgumentException("Отсутствует закрывающая скобка");
            }
            postfixExpression.append(operator).append(" ");
        }
        return postfixExpression.toString().trim();
    }

    public static boolean isOperator(char symbol){
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '^';
    }

    private int priority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
}
