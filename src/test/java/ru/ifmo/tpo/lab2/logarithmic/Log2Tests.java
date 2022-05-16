package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class Log2Tests {
    public static final double DEFAULT_PRECISION = 0.0000001;
    @Mock
    private static LogN lognMock;

    @Spy
    private static LogN lognSpy;
    private static Log2 l2;

    @BeforeAll
    public static void setUp() {
        lognSpy = spy(new LogN(DEFAULT_PRECISION));
//        lognMock = mock(LogN.class);
    }

    @Test
    public void checkLogNUse() {
        l2 = new Log2(lognSpy);
        Double res = l2.solve(-1D);
        verify(lognSpy, atLeastOnce()).solve(anyDouble());
        assertEquals(Math.log(-1D) / Math.log(2.0), res, 0.0001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 4.0, 6.666, 1000.00})
    public void checkPositive(Double val) {
        l2 = new Log2(lognMock);
        when(lognMock.solve(val)).thenReturn(Math.log(val));
        when(lognMock.solve(eq(2D))).thenReturn(0.6931471702560119);
        assertEquals(Math.log(val)/Math.log(2D), l2.solve(val), DEFAULT_PRECISION * 1000);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) {
        l2 = new Log2(lognMock);
        when(lognMock.solve(anyDouble())).thenReturn(Math.log(val));
        assertEquals(Math.log(val) / Math.log(2D), l2.solve(val), DEFAULT_PRECISION * 1000);
    }
}