package ru.ifmo.tpo.lab2.logarithmic;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ru.ifmo.tpo.lab2.models.NotANumberException;


class Log3Test {

    static LogN ln;
    static Log3 l3;
    public final static double eps = 0.0000001;

    @BeforeAll
    public static void setUp() {
        ln = new LogN();
        l3 = new Log3(ln);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(double val) throws NotANumberException {
        Assertions.assertEquals(Math.log(val) / Math.log(3.0), l3.solve(val, eps), eps * 1000);
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) throws NotANumberException {
        Assertions.assertEquals(Math.log(val) / Math.log(3.0), l3.solve(val, eps), eps * 1000);
    }
}