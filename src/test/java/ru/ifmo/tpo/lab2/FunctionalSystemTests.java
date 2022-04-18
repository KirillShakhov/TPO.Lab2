package ru.ifmo.tpo.lab2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import ru.ifmo.tpo.lab2.trigonometric.*;
import ru.ifmo.tpo.lab2.logarithmic.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FunctionalSystemTests {
    @Mock private static Cot cotMock;
    @Mock private static Csc cscMock;

    @Mock private static Ln lnMock;
    @Mock private static Log2 l2Mock;
    @Mock private static Log3 l3Mock;
    @Mock private static Log10 l10Mock;

    @Spy private static Cot cotSpy;
    @Spy private static Csc cscSpy;

    @Spy private static Ln lnSpy;
    @Spy private static Log2 l2Spy;
    @Spy private static Log3 l3Spy;
    @Spy private static Log10 l10Spy;
    
    private static FunctionalSystem fs;


    public final static double DEFAULT_PRECISION = 0.0000001;

    @BeforeAll
    public static void initMocks() {
        fs = new FunctionalSystem(cotMock, cscMock, l2Mock, l3Mock, l10Mock, lnMock);
        cotSpy = spy(new Cot(DEFAULT_PRECISION));
        cscSpy = spy(new Csc(DEFAULT_PRECISION));
        lnSpy = spy(new Ln(DEFAULT_PRECISION));
        l2Spy = spy(new Log2(DEFAULT_PRECISION));
        l3Spy = spy(new Log3(DEFAULT_PRECISION));
        l10Spy = spy(new Log10(DEFAULT_PRECISION));
        fs = new FunctionalSystem(cotSpy, cscSpy, l2Spy, l3Spy, l10Spy, lnSpy);

    }



    @Test
    public void checkFuncsUse() {
        fs = new FunctionalSystem(cotSpy, cscSpy, l2Spy, l3Spy, l10Spy, lnSpy);

        fs.solve(1D);
        fs.solve(-1D);
        verify(cotSpy, atLeastOnce()).solve(anyDouble());
        verify(cscSpy, atLeastOnce()).solve(anyDouble());
        verify(l2Spy, atLeastOnce()).solve(anyDouble());
        verify(l3Spy, atLeastOnce()).solve(anyDouble());
        verify(l10Spy, atLeastOnce()).solve(anyDouble());
        verify(lnSpy, atLeastOnce()).solve(anyDouble());
    }

    @ParameterizedTest(name = "Funtional System({0}) = NaN")
    @DisplayName("Check NaN")
    @ValueSource(doubles = {0})
    public void checkNaN(double x) {
        fs = new FunctionalSystem(cotSpy, cscSpy, l2Spy, l3Spy, l10Spy, lnSpy);
        assertAll(
                () -> assertEquals(Double.NaN, fs.solve(x))
        );
    }


    @ParameterizedTest(name = "Funtional System({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableFS.csv", numLinesToSkip = 1, delimiter = ';')
    public void checkBetweenDotsMinusPiAndPi(double x, double y) {
        fs = new FunctionalSystem(cotMock, cscMock, l2Mock, l3Mock, l10Mock, lnMock);

        when(cotMock.solve(anyDouble())).thenReturn(1.0 / Math.tan(x));
        when(cscMock.solve(anyDouble())).thenReturn(1.0 / Math.sin(x));
        when(l2Mock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(2));
        when(l3Mock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(3));
        when(l10Mock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(10));
        when(lnMock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(Math.E));

        assertAll(
                () -> assertEquals(y, fs.solve(x), 0.001)
        );
    }

    @ParameterizedTest(name = "Funtional System({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableFS.csv", numLinesToSkip = 1, delimiter = ';')
    public void checkIntegrateTest(double x, double y) {
        fs = new FunctionalSystem(cotSpy, cscSpy, l2Spy, l3Spy, l10Spy, lnSpy);
        assertAll(
                () -> assertEquals(y, fs.solve(x), 0.001)
        );
    }
}
