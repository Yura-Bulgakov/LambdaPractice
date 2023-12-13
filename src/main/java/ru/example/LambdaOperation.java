package ru.example;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;


public class LambdaOperation {

    private BinaryOperator<Double> binaryOperation;

    public BinaryOperator<Double> getBinaryOperation() {
        return binaryOperation;
    }

    public Double apply(Double operand1, Double operand2) {
        return binaryOperation.apply(operand1, operand2);
    }

    public void switchOperation(String operator) {
        if (!ExpressionConvertor.isOperator(operator)){
            throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
        switch (operator) {
            case "+": {
                binaryOperation = (x, y) ->  x + y;
                break;
            }
            case "-": {
                binaryOperation = (x, y) -> x - y;
                break;
            }
            case "*": {
                binaryOperation = (x, y) -> x * y;
                break;
            }
            case "/": {
                binaryOperation = (x, y) -> x / y;
                break;
            }
            case "^": {
                binaryOperation = Math::pow;
                break;
            }
        }
    }
}
