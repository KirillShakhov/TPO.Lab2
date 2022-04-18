package ru.ifmo.tpo.lab2.trigonometric;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CscTests {
    private final static double DEFAULT_PRECISION = 0.0000001;

    @Mock
    private static Sin sinMock;
    @Spy
    private static Sin sinSpy;

    private static Csc csc;
    private static Csc cscSpy;

    @BeforeAll
    public static void setUp() {
        csc = new Csc(sinMock);
        sinSpy = spy(new Sin(DEFAULT_PRECISION));
        cscSpy = new Csc(sinSpy);
    }

    @Test
    @DisplayName("Check tg use")
    public void checkTgUse() {
        cscSpy.solve(Math.PI / 8);
        verify(sinSpy, atLeastOnce()).solve(anyDouble());
    }

    @ParameterizedTest(name = "csc({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = {Math.PI / 12, Math.PI / 8, Math.PI / 6, Math.PI / 4, Math.PI / 3,
            -Math.PI / 12, -Math.PI / 8, -Math.PI / 6, -Math.PI / 4, -Math.PI / 3})
    public void checkPiDots(double param) {
        csc = new Csc(sinMock);
        when(sinMock.solve(anyDouble())).thenReturn(Math.sin(param));
        Assertions.assertEquals(1.0 / Math.sin(param), csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = 1")
    @DisplayName("Check PI dots NaN")
    @ValueSource(doubles = {Math.PI / 2, 3 * Math.PI / 2})
    public void checkPiDotsPIdivTwo(double param) {
        Assertions.assertEquals(1D, csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = -1")
    @DisplayName("Check PI dots PI/2")
    @ValueSource(doubles = {-Math.PI / 2, -3 * Math.PI / 2})
    public void checkPiDotsMinusPIdivTwo(double param) {
        Assertions.assertEquals(-1D, csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = NaN")
    @DisplayName("Check PI dots NaN")
    @ValueSource(doubles = {Math.PI, -Math.PI, 0, 0.0})
    public void checkPiDotsNaN(double param) {
        Assertions.assertEquals(Double.NaN, csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableCsc.csv", numLinesToSkip = 1, delimiter = ';')
    public void checkBetweenDotsMinusPiAndPi(double x, double y) {
        csc = new Csc(sinMock);
        when(sinMock.solve(anyDouble())).thenReturn(Math.sin(x));
        Assertions.assertEquals(y, csc.solve(x), 0.001);
    }
}
