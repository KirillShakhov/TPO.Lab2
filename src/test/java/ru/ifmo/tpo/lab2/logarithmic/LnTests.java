package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LnTests {
    public final static double DEFAULT_PRECISION = 0.0000001;
    static Ln ln;
    @Mock
    private static LogN lognMock;

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 6.666, 1000.00})
    public void checkPositive(double val) {
        ln = new Ln(lognMock);
        when(lognMock.solve(val)).thenReturn(Math.log(val));
        when(lognMock.solve(eq(Math.E))).thenReturn(0.999999989210841);
        assertEquals(Math.log(val) / Math.log(Math.E), ln.solve(val), DEFAULT_PRECISION * 1000);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) {
        ln = new Ln(lognMock);
        assertEquals(Math.log(val) / Math.log(Math.E), ln.solve(val), DEFAULT_PRECISION * 1000);
    }
}
