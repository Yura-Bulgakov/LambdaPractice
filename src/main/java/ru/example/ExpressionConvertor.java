package ru.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionConvertor {

    public String convertToPostfix(String infixExpression){
        var symbols = getSymbols(infixExpression);
        StringBuilder postfixExpression = new StringBuilder();
        Deque<String> operatorStack = new ArrayDeque<>();
        boolean unaryMinus = true;
        for (var symbol: symbols) {
            if (isOperand(symbol)){
                postfixExpression.append(symbol).append(" ");
                unaryMinus = false; // встретился операнд
            } else if (symbol.equals("(")) {
                if (unaryMinus && (operatorStack.peek() != null && operatorStack.peek().equals("-"))){
                    // унарный минус перед скобкой
                    postfixExpression.append("0 ");
                }
                operatorStack.push(symbol);
                unaryMinus = true;
            } else if (symbol.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")){
                    postfixExpression.append(operatorStack.pop()).append(" ");
                }
                if (operatorStack.isEmpty()){
                    throw new IllegalArgumentException("Внутри скобок отсутствуют операторы");
                }
                operatorStack.pop();
                unaryMinus = false; // за закрывающей скобкой операнд
            } else if (isOperator(symbol) && !symbol.equals("-")){
                if (unaryMinus){
                    // перед оператором унарный минус
                    postfixExpression.append("0 ");
                    unaryMinus = false;
                }
                while (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(symbol)){
                    postfixExpression.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(symbol);
            } else if (symbol.equals("-")) {
                if (unaryMinus){
                    postfixExpression.append("0 ");
                    operatorStack.push(symbol);
                    unaryMinus = false;
                } else {
                    while (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(symbol)) {
                       postfixExpression.append(operatorStack.pop()).append(" ");
                    }
                    operatorStack.push(symbol);
                    unaryMinus = true; // устанавлием флаг перед следующим символом
                }
            } else {
                throw new IllegalArgumentException("недопустимый символ: " + symbol);
            }
        }

        while (!operatorStack.isEmpty()){
            var operator = operatorStack.pop();
            if (operator.equals("(")){
                throw new IllegalArgumentException("Отсутствует закрывающая скобка");
            }
            postfixExpression.append(operator).append(" ");
        }
        return postfixExpression.toString().trim();
    }

    public static boolean isOperator(String symbol){
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^");
    }

    public static boolean isOperand(String symbol){
        return symbol.matches("\\d+(\\.\\d+)?");
    }

    private int priority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    private List<String> getSymbols(String expression){
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?|[\\-+*/()^]");
        Matcher matcher = pattern.matcher(expression);
        List<String> symbols = new ArrayList<>();
        while (matcher.find()) {
            symbols.add(matcher.group());
        }
        return symbols;
    }
}
