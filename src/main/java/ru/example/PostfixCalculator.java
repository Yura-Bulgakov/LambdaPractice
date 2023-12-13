package ru.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class PostfixCalculator {
    private ExpressionConvertor expressionConvertor;
    private LambdaOperation operation;

    public PostfixCalculator() {
        this.expressionConvertor = new ExpressionConvertor();
        this.operation = new LambdaOperation();
    }

    public double solveExpression(String expression){
        String postfixExpression = expressionConvertor.convertToPostfix(expression);
        Deque<Double> operandStack = new ArrayDeque<>();
        String[] expressionElements = postfixExpression.split("\\s+");
        for (var element:expressionElements) {
            if (ExpressionConvertor.isOperator(element.charAt(0))){
                operation.switchOperation(element.charAt(0));
                double operand2 = operandStack.pop();
                if (operation.getUnaryOperation() != null){
                    operandStack.push(operation.apply(operand2));
                } else {
                    double operand1 = operandStack.pop();
                }
            }
        }


    }
}
