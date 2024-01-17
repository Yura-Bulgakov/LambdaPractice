package ru.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private ExpressionConvertor expressionConvertor;
    private LambdaOperation operation;

    public Calculator(ExpressionConvertor expressionConvertor, LambdaOperation lambdaOperation) {
        this.expressionConvertor = expressionConvertor;
        this.operation = lambdaOperation;
    }

    public double solveExpression(String expression) {
        String postfixExpression = expressionConvertor.convertToPostfix(expression);
        Deque<Double> operandStack = new ArrayDeque<>();
        String[] expressionElements = postfixExpression.split(" ");
        for (var element : expressionElements) {
            if (ExpressionConvertor.isOperator(element) && element.length() == 1) {
                //Оператор
                char operator = element.charAt(0);
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                operation.switchOperation(String.valueOf(operator));
                operandStack.push(operation.apply(operand1, operand2));
            } else {
                // Операнды
                operandStack.push(Double.parseDouble(element));
            }
        }
        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Некорректная запись, результатом должно быть одно число");
        }
        return operandStack.pop();
    }
}
