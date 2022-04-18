package ru.ifmo.tpo.lab2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FunctionalSystemTests {
    @Mock private Cot cotMock;
    @Mock private Csc cscMock;
    @Mock private Sin sinMock;
    @Mock private Tg tgMock;

    @Mock private Ln lnMock;
    @Mock private Log2 l2Mock;
    @Mock private Log3 l3Mock;
    @Mock private Log10 l10Mock;


    @Spy
    private Cot cotSpy;
    @Spy
    private Csc cscSpy;
    @Spy
    private Sin sinSpy;
    @Spy
    private Tg tgSpy;

    @Spy
    private Ln lnSpy;
    @Spy
    private Log2 l2Spy;
    @Spy
    private Log3 l3Spy;
    @Spy
    private Log10 l10Spy;
    
    private FunctionalSystem fs;


    private AutoCloseable openMocks;

    public final static double DEFAULT_PRECISION = 0.0001;

    @BeforeEach
    public void initMocks() {
        openMocks = MockitoAnnotations.openMocks(this);
        fs = new FunctionalSystem(cotMock, cscMock, l2Mock, l3Mock, l10Mock, lnMock);
    }

    @AfterEach
    public void afterMethod() throws Exception {
        openMocks.close();
    }

    @Before
    public void initcheckFuncsUse(){
        cotSpy = spy(new Cot());
        cscSpy = spy(new Csc());
        sinSpy = spy(new Sin());
        tgSpy = spy(new Tg());
        lnSpy = spy(new Ln());
        l2Spy = spy(new Log2());
        l3Spy = spy(new Log3());
        l10Spy = spy(new Log10());
        fs = new FunctionalSystem(cotSpy, cscSpy, l2Spy, l3Spy, l10Spy, lnSpy);
    }

    @Test
    public void checkFuncsUse() {
        fs.solve(1D);
        fs.solve(-1D);
        verify(cotSpy, atLeastOnce()).solve(anyDouble());
        verify(cscSpy, atLeastOnce()).solve(anyDouble());
        verify(l2Spy, atLeastOnce()).solve(anyDouble());
        verify(l3Spy, atLeastOnce()).solve(anyDouble());
        verify(l10Spy, atLeastOnce()).solve(anyDouble());
        verify(lnSpy, atLeastOnce()).solve(anyDouble());
        assertAll(
            () -> assertEquals(0.1, 0, 0.1)
    );
    }

    // @ParameterizedTest(name = "Funtional System({0}) = NaN")
    // @DisplayName("Check PI dots NaN")
    // @ValueSource(doubles = {Math.PI/2, -Math.PI/2})
    // void checkPiDotsNaN(double param) {
    //     assertAll(
    //             () -> assertEquals(Double.NaN, tg.solve(param))
    //     );
    // }


    @ParameterizedTest(name = "Funtional System({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableFS.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsMinusPiAndPi(double x, double y) {
        when(cotMock.solve(anyDouble())).thenReturn(1.0 / Math.tan(x));
        when(cscMock.solve(anyDouble())).thenReturn(1.0 / Math.sin(x));
        when(l2Mock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(2));
        when(l3Mock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(3));
        when(l10Mock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(10));
        when(lnMock.solve(anyDouble())).thenReturn(Math.log(x) / Math.log(Math.E));

        assertAll(
                () -> assertEquals(y, fs.solve(x), 0.0001)
        );
    }
}
