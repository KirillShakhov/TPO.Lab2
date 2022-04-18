package ru.ifmo.tpo.lab2.trigonometric;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
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

@ExtendWith(MockitoExtension.class)
public class CscTest {
    private final static double DEFAULT_PRECISION = 0.0000001;
    private AutoCloseable openMocks;

    @Mock
    private Sin sinMock;
    @Spy
    private Sin sinSpy;

    private Csc csc;

    @BeforeEach
    public void initMocks() {
        openMocks = MockitoAnnotations.openMocks(this);
        csc = new Csc(sinMock);
    }

    @AfterEach
    public void afterMethod() throws Exception {
        openMocks.close();
    }

    @Before
    public void initcheckSinUse() {
        openMocks = MockitoAnnotations.openMocks(this);
        this.csc = new Csc(sinSpy);
    }

    @Test
    @DisplayName("Check tg use")
    public void checkTgUse() {
        this.csc.solve(Math.PI/8);
        verify(sinSpy, atLeastOnce()).solve(anyDouble());
    }

    @ParameterizedTest(name = "csc({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = { Math.PI / 12, Math.PI / 8, Math.PI / 6, Math.PI / 4, Math.PI / 3,
            -Math.PI / 12, -Math.PI / 8, -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, -Math.PI / 2 })
    public void checkPiDots(double param) {
        csc = new Csc(sinMock);
        when(sinMock.solve(anyDouble())).thenReturn(Math.sin(param));
        assertEquals(1.0 / Math.sin(param), csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = 1")
    @DisplayName("Check PI dots NaN")
    @ValueSource(doubles = { Math.PI/2, -Math.PI/2, 3*Math.PI/2, -3*Math.PI/2})
    public void checkPiDotsPIdivTwo(double param) {
        assertEquals(1D, csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = NaN")
    @DisplayName("Check PI dots NaN")
    @ValueSource(doubles = { Math.PI, -Math.PI, 0 , 2*Math.PI -2*Math.PI})
    public void checkPiDotsNaN(double param) {
        assertEquals(Double.NaN, csc.solve(param), DEFAULT_PRECISION);
    }

    @ParameterizedTest(name = "csc({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableCsc.csv", numLinesToSkip = 1, delimiter = ';')
    public void checkBetweenDotsMinusPiAndPi(double x, double y) {
        when(sinMock.solve(anyDouble())).thenReturn(Math.sin(x));
        assertEquals(y, csc.solve(x), 0.001);
    }
}
