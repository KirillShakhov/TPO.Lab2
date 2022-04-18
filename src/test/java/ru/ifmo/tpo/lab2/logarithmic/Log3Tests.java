package ru.ifmo.tpo.lab2.logarithmic;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class Log3Tests {
    @Spy
    private static LogN lognSpy;
    private static Log3 l3;
    public static final double DEFAULT_PRECISION = 0.0000001;
    
    @BeforeAll
    public static void setUp() {
        l3 = new Log3(new LogN(DEFAULT_PRECISION));
    }


    @Before
    public void initcheckLogNUse(){
        lognSpy = spy(new LogN(DEFAULT_PRECISION));
        l3 = new Log3(lognSpy);
    }

    @Test
    public void checkLogNUse() {
        Double res = l3.solve(-1D);
        verify(lognSpy, atLeastOnce()).solve(anyDouble());
        Assertions.assertEquals(Math.log(-1D) / Math.log(3D), res, 0.0001);
    }

     @ParameterizedTest
     @ValueSource(doubles = {0.01, 0.10, 0.5, 0.8, 1.0, 1.5, 3.5, 4.0, 6.666, 1000.00})
     public void checkPositive(Double val) {
         Assertions.assertEquals(Math.log(val) / Math.log(3D), l3.solve(val), DEFAULT_PRECISION * 1000);
     }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -20.0002, -1000.00})
    public void checkBad(double val) {
        Assertions.assertEquals(Math.log(val) / Math.log(3D), l3.solve(val), DEFAULT_PRECISION * 1000);
    }
}