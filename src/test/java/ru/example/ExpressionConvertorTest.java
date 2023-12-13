package ru.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionConvertorTest {
    ExpressionConvertor expressionConvertor;

    @BeforeEach
    void setUp() {
        expressionConvertor = new ExpressionConvertor();
    }

    @Test
    void convertToPostfix() {
        class Testcase{
            final String name;
            final String infixExpression;
            final String expectedExpression;

            public Testcase(String name, String infixExpression, String expectedExpression) {
                this.name = name;
                this.infixExpression = infixExpression;
                this.expectedExpression = expectedExpression;
            }

            void run(){
                Assertions.assertEquals(expectedExpression,
                        expressionConvertor.convertToPostfix(infixExpression),
                        name);
            }
        }
        Stream.of(
                new Testcase("1", "2 + 2", "2 2 +"),
                new Testcase("2", "22 + 2", "22 2 +"),
                new Testcase("3", "2 - 2", "2 2 -"),
                new Testcase("4", "- 2 + 2", "0 2 - 2 +"),
                new Testcase("5", "2 + (2 - 1)", "2 2 1 - +"),

                new Testcase("6", "- (2 + 2)", "0 2 2 + -"),
                new Testcase("7", "3.5+25*(2.1-8.3)/4.2", "3.5 25 2.1 8.3 - * 4.2 / +"),
                new Testcase("9", "3.5 + 5", "3.5 5 +"),
                new Testcase("9", "10 + 2.5 * 6", "10 2.5 6 * +"),
                new Testcase("10", "( 3 + 5.2 ) * 2", "3 5.2 + 2 *"),
                new Testcase("11", "5 + 6 * 2.1 / 8 - 3", "5 6 2.1 * 8 / + 3 -"),
                new Testcase("12", "4 * ( 7.5 - 2 ) / 3", "4 7.5 2 - * 3 /"),
                new Testcase("13", "8 + 2 * ( 5 - 3.5 )", "8 2 5 3.5 - * +"),
                new Testcase("14", "2 * ( 3 + 4.6 * ( 5 - 6 ) )", "2 3 4.6 5 6 - * + *"),
                new Testcase("15", "( 1.2 + 2 ) * ( 3 - 4 ) / ( 5 + 6 )", "1.2 2 + 3 4 - * 5 6 + /"),
                new Testcase("16", "( 2.5 + 3 ) * ( 4 + 5.1 ) / ( 6 + 7 )", "2.5 3 + 4 5.1 + * 6 7 + /"),
                new Testcase("17", "1 + 2.3 * 3 / 4.5 - 5.6 + 6", "1 2.3 3 * 4.5 / + 5.6 - 6 +"),
                new Testcase("18", "10.4 / 2.2 + 3 * 4.6 - 5", "10.4 2.2 / 3 4.6 * + 5 -"),
                new Testcase("19", "2 * ( 10.5 / 2.5 + 3 ) * 4 - 5.7", "2 10.5 2.5 / 3 + * 4 * 5.7 -"),
                new Testcase("20", "( 3.8 + 5 ) * ( 6 - 2 ) / ( 4 + 2.1 )", "3.8 5 + 6 2 - * 4 2.1 + /"),
                new Testcase("21", "3 + 4.2 * 2 / ( 1 - 5 ) + 6.5", "3 4.2 2 * 1 5 - / + 6.5 +"),
                new Testcase("22", "2 * ( 3.1 + 4 ) / 5.6", "2 3.1 4 + * 5.6 /"),
                new Testcase("23", "1 + 2.4 * 3 + 4 * 5.7 + 6", "1 2.4 3 * + 4 5.7 * + 6 +")

        ).forEach(Testcase::run);
    }

    @Test
    void isOperator() {
    }

}