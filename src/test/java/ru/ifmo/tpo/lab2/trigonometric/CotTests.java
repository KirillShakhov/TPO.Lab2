package ru.ifmo.tpo.lab2.trigonometric;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CotTests {
    private final static double DEFAULT_PRECISION = 0.0000001;

    @Mock
    private static Tg tgMock;
    @Spy
    private static Tg spyTg;

    private static Cot cot;

    @BeforeAll
    public static void initMocks() {
        cot = new Cot(tgMock);
        spyTg = spy(new Tg());
    }

    @Test
    @DisplayName("Check tg use")
    public void checkTgUse() {
        cot = new Cot(spyTg);
        cot.solve(Math.PI/8);
        verify(spyTg, atLeastOnce()).solve(anyDouble());
    }

    @ParameterizedTest(name = "cot({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = { Math.PI / 12, Math.PI / 8, Math.PI / 6, Math.PI / 4, Math.PI / 2, Math.PI / 3,
            -Math.PI / 12, -Math.PI / 8, -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, -Math.PI / 2 })
    public void checkPiDots(double param) {
        cot = new Cot(tgMock);
        when(tgMock.solve(anyDouble())).thenReturn(Math.tan(param + Math.PI / 2));
        assertEquals(1.0 / Math.tan(param), cot.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "cot({0}) = NaN")
    @DisplayName("Check PI dots NaN")
    @ValueSource(doubles = { Math.PI, -Math.PI })
    public void checkPiDotsNaN(double param) {
        assertEquals(Double.NaN, cot.solve(param));
    }

    @ParameterizedTest(name = "cot({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableCot.csv", numLinesToSkip = 1, delimiter = ';')
    public void checkBetweenDotsMinusPiAndPi(double x, double y) {
        cot = new Cot(tgMock);
        when(tgMock.solve(anyDouble())).thenReturn(Math.tan(x + Math.PI / 2));
        assertEquals(y, cot.solve(x), 0.0001);
    }
}
