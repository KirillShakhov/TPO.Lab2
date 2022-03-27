package ru.ifmo.tpo.lab2.functions;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import ru.ifmo.tpo.lab2.funcions.Sin;

public class SinTest {

    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");

    @Test
    public void shouldCalculateForZero() {
        final Sin sin = new Sin();
        assertEquals(ZERO.setScale(4, HALF_EVEN), sin.solve(ZERO, DEFAULT_PRECISION));
    }

    @Test
    public void shouldCalculateForPiDividedByTwo() {
        final Sin sin = new Sin();
        assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                sin.solve(BigDecimal.valueOf(Math.PI / 2), DEFAULT_PRECISION));
    }

    @Test
    public void shouldCalculateForPiDividedByTwoCicle() {
        final Sin sin = new Sin();
        for (int i = 0; i < 100; i+=2){
            assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                    sin.solve(BigDecimal.valueOf(Math.PI / 2 + (i * Math.PI)), DEFAULT_PRECISION));
                    
            assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                    sin.solve(BigDecimal.valueOf(Math.PI / 2 - i * Math.PI), DEFAULT_PRECISION));
        }
    }

    @Test
    public void shouldCalculateForOne() {
        final Sin sin = new Sin();
        final BigDecimal expected = new BigDecimal("0.8415");
        assertEquals(expected, sin.solve(ONE, DEFAULT_PRECISION));
    }

    @Test
    public void shouldCalculateForPeriodic() {
        final Sin sin = new Sin();
        final BigDecimal expected = new BigDecimal("0.0972");
        assertEquals(expected, sin.solve(new BigDecimal(-113), DEFAULT_PRECISION));
    }
}
