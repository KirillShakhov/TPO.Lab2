package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTests {
    static LogN logn;
    static Ln ln;
    public final static double DEFAULT_PRECISION = 0.0000001;

    @BeforeAll
    public static void setUp() {
        logn = new LogN(DEFAULT_PRECISION);
        ln = new Ln(logn);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(double val) {
        assertAll(
                () -> assertEquals(Math.log(val) / Math.log(Math.E), ln.solve(val), DEFAULT_PRECISION * 1000)
        );
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) {
        assertAll(
                () -> assertEquals(Math.log(val) / Math.log(Math.E), ln.solve(val), DEFAULT_PRECISION * 1000)
        );
    }
}
