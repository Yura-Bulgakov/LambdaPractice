package ru.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ExpressionConvertorTest {
    ExpressionConvertor expressionConvertor;

    @BeforeEach
    void setUp() {
        expressionConvertor = new ExpressionConvertor();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/expression_convertor/valid_expression_convert_data.csv",
            useHeadersInDisplayName = true)
    void validExpressionsConvertToPostfix(String testcase, String expressionToConvert, String convertedExpression) {
        Assertions.assertEquals(convertedExpression, expressionConvertor.convertToPostfix(expressionToConvert), testcase);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/expression_convertor/invalid_expression_convert_data.csv",
            useHeadersInDisplayName = true)
    void invalidExpressionsConvertToPostfix(String testcase, String expressionToConvert) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            expressionConvertor.convertToPostfix(expressionToConvert);
        });
    }

    @Test
    void emptyExpressionConvertToPostfix() {
        Assertions.assertEquals(expressionConvertor.convertToPostfix(""), "");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/expression_convertor/valid_operators_data.csv",
            useHeadersInDisplayName = true)
    void isValidOperator(String testcase, String operator) {
        Assertions.assertTrue(ExpressionConvertor.isOperator(operator), testcase);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/expression_convertor/invalid_operators_data.csv",
            useHeadersInDisplayName = true)
    void isInvalidOperator(String testcase, String operator) {
        Assertions.assertFalse(ExpressionConvertor.isOperator(operator), testcase);
    }

}