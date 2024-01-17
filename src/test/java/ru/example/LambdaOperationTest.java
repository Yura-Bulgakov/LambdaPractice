package ru.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LambdaOperationTest {

    private LambdaOperation lambdaOperation;

    @BeforeEach
    void setUp() {
        lambdaOperation = new LambdaOperation();
    }

    @ParameterizedTest
    @CsvSource({
            "2, +, 2, 4",
            "2, -, 2, 0",
            "2, *, 3, 6",
            "6, /, 2, 3",
            "4, ^, 2, 16"})
    void applyValidOperator(double operand1, String operator, double operand2, double result) {
        lambdaOperation.switchOperation(operator);
        Assertions.assertEquals(result, lambdaOperation.apply(operand1, operand2));
    }

    @Test
    void switchInvalidOperator() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lambdaOperation.switchOperation("#");
        });
    }
}