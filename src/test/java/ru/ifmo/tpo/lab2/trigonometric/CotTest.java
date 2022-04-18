package ru.ifmo.tpo.lab2.trigonometric;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

@ExtendWith(MockitoExtension.class)
public class CotTest {
    private final static double DEFAULT_PRECISION = 0.0000001;
    private AutoCloseable openMocks;

    @Mock
    private Tg tgMock;
    @Spy
    private Tg spyTg;

    private Cot cot;

    @BeforeEach
    public void initMocks() {
        openMocks = MockitoAnnotations.openMocks(this);
        cot = new Cot(tgMock);
    }

    @AfterEach
    public void afterMethod() throws Exception {
        openMocks.close();
    }

    @Before
    public void initcheckTgUse() {
        this.cot = new Cot(spyTg);
    }

    @Test
    @DisplayName("Check tg use")
    public void checkTgUse() {
        this.cot.solve(Math.PI/8);
        verify(spyTg, atLeastOnce()).solve(anyDouble());
    }

    @ParameterizedTest(name = "cot({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = { Math.PI / 12, Math.PI / 8, Math.PI / 6, Math.PI / 4, Math.PI / 2, Math.PI / 3,
            -Math.PI / 12, -Math.PI / 8, -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, -Math.PI / 2 })
    public void checkPiDots(double param) {
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
        when(tgMock.solve(anyDouble())).thenReturn(Math.tan(x + Math.PI / 2));
        assertEquals(y, cot.solve(x), 0.0001);
    }
}
