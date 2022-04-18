package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class Log3Tests {
    private LogN ln;
    private Log3 l3;
    public final double DEFAULT_PRECISION = 0.0000001;

    @BeforeEach
    public void setUp() {
        ln = new LogN(DEFAULT_PRECISION);
        l3 = new Log3(ln);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(Double val) {
        assertEquals(Math.log(val) / Math.log(3.0), l3.solve(val), DEFAULT_PRECISION * 1000);
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(Double val){
        assertEquals(Math.log(val) / Math.log(3.0), l3.solve(val), DEFAULT_PRECISION * 1000);
    }
}