package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class LogNTest {

    static LogN ln;
    public final static double DEFAULT_PRECISION = 0.0000001;

    @BeforeAll
    public static void setUp() {
        ln = new LogN(DEFAULT_PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(double val) {
        Assertions.assertEquals(Math.log(val), ln.solve(val), DEFAULT_PRECISION * 1000);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) {
        Assertions.assertEquals(Math.log(val), ln.solve(val), DEFAULT_PRECISION * 1000);
    }

}