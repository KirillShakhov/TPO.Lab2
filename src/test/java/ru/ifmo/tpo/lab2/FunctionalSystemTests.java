package ru.ifmo.tpo.lab2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import ru.ifmo.tpo.lab2.trigonometric.*;
import ru.ifmo.tpo.lab2.logarithmic.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

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
    
    // @ParameterizedTest(name = "Funtional System({0})")
    // @DisplayName("Check PI dots")
    // @ValueSource(doubles = {0, Math.PI/12, Math.PI/8, Math.PI/6, Math.PI/4, Math.PI/3, -Math.PI/12, -Math.PI/8, -Math.PI/6, -Math.PI/4, -Math.PI/3, Math.PI})
    // void checkPiDots(double param) {
    //     assertEquals(Math.tan(param), tg.solve(param), DEFAULT_PRECISION)
    // }

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
