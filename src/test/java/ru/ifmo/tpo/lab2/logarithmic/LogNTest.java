package ru.ifmo.tpo.lab2.logarithmic;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ru.ifmo.tpo.lab2.models.NotANumberException;


class LogNTest {

    static LogN ln;
    public final static double eps = 0.0000001;

    @BeforeAll
    public static void setUp() {
        ln = new LogN();
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(double val) throws NotANumberException {
        Assertions.assertEquals(Math.log(val), ln.solve(val, eps), eps * 1000);
        
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) throws NotANumberException {
        Assertions.assertEquals(Math.log(val), ln.solve(val, eps), eps * 1000);
    }

}