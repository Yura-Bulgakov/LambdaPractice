package ru.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void solveExpression() {
        class Testcase{
            final String name;
            final String infixExpression;
            final double expectedResult;
            private static final double DIFFER = 0.0000001;

            public Testcase(String name, String infixExpression, double expectedResult) {
                this.name = name;
                this.infixExpression = infixExpression;
                this.expectedResult = expectedResult;
            }

            void run(){
                double got = calculator.solveExpression(infixExpression);
                double delta = Math.abs(expectedResult - got);
                Assertions.assertTrue(delta < DIFFER,
                        name + " Получено: " + got + ", ожидалось: " + expectedResult);
            }
        }
        Stream.of(
                new Testcase("1", "2 + 2", 4),
                new Testcase("2", "22 + 2", 24),
                new Testcase("3", "2 - 2", 0),
                new Testcase("4", "- 2 + 2", 0),
                new Testcase("5", "2 + (2 - 1)", 3),
                new Testcase("6", "- (2 + 2)", -4),
                new Testcase("7", "3.5 + 5", 8.5),
                new Testcase("8", "10 + 2.5 * 6", 25),
                new Testcase("9", "( 3 + 5.2 ) * 2", 16.4),
                new Testcase("10", "5 + 6 * 2.1 / 8 - 3", 3.575),
                new Testcase("11", "8 + 2 * ( 5 - 3.5 )", 11),
                new Testcase("12", "2 * ( 3 + 4.6 * ( 5 - 6 ) )", -3.2),
                new Testcase("13", "( 2.5 + 3 ) * ( 4 + 5.1 ) / ( 6 + 7 )", 3.85),
                new Testcase("14", "2 * ( 10.5 / 2.5 + 3 ) * 4 - 5.7", 51.9),
                new Testcase("15", "3 + 4.2 * 2 / ( 1 - 5 ) + 6.5", 7.4),
                new Testcase("16", "1 + 2.4 * 3 + 4 * 5.7 + 6", 37)

        ).forEach(Testcase::run);
    }
}