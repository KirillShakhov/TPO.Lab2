package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ru.ifmo.tpo.lab2.models.NotANumberException;

public class Log10Tests { 
    static LogN ln;
    static Log10 l10;
    public final static double DEFAULT_PRECISION = 0.0000001;

    @BeforeAll
    public static void setUp() {
        ln = new LogN(DEFAULT_PRECISION);
        l10 = new Log10(ln);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(double val) throws NotANumberException {
        Assertions.assertEquals(Math.log(val) / Math.log(10.0), l10.solve(val), DEFAULT_PRECISION * 1000);
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) throws NotANumberException {
        Assertions.assertEquals(Math.log(val) / Math.log(3.0), l10.solve(val), DEFAULT_PRECISION * 1000);
    }
}
