package ru.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    private Calculator calculator;
    private ExpressionConvertor mockConvertor;
    private LambdaOperation spyOperation;
    private static final double DIFFER = 0.0000001;

    @BeforeEach
    void setUp() {
        mockConvertor = mock(ExpressionConvertor.class);
        spyOperation = spy(LambdaOperation.class);
        calculator = new Calculator(mockConvertor, spyOperation);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/valid_expression_solve_data.csv", useHeadersInDisplayName = true)
    void solveValidExpression(String testCase, String expression, String convertedExpression, double expectedResult) {
        when(mockConvertor.convertToPostfix(expression)).thenReturn(convertedExpression);
        double got = calculator.solveExpression(expression);
        double delta = Math.abs(expectedResult - got);
        Assertions.assertTrue(delta < DIFFER,
                testCase + " Получено: " + got + ", ожидалось: " + expectedResult);
    }

    @Test
    void solveInvalidExpression() {
        when(mockConvertor.convertToPostfix(anyString())).thenReturn("2 2");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.solveExpression("1324");
        });
    }

    @Test
    void solveEmptyExpression() {
        when(mockConvertor.convertToPostfix(anyString())).thenReturn("");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.solveExpression("");
        });
    }
}