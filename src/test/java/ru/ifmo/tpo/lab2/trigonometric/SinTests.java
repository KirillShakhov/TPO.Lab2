package ru.ifmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class SinTests {

    private static final Double DEFAULT_PRECISION = Double.valueOf("0.0001");

    @Test
    public void shouldCalculateForZero() {
        final Sin sin = new Sin(DEFAULT_PRECISION);
        assertEquals(0, sin.solve(0D), DEFAULT_PRECISION);
    }

    @Test
    public void shouldCalculateForPiDividedByTwo() {
        final Sin sin = new Sin(DEFAULT_PRECISION);
        assertEquals(1, sin.solve(Math.PI / 2), DEFAULT_PRECISION);
    }

    @Test
    public void shouldCalculateForPiDividedByTwoCicle() {
        final Sin sin = new Sin(DEFAULT_PRECISION);
        for (int i = 0; i < 100; i+=2){
            assertEquals(1, sin.solve(Math.PI / 2 + (i * Math.PI)), DEFAULT_PRECISION);
            assertEquals(1, sin.solve(Math.PI / 2 - i * Math.PI), DEFAULT_PRECISION);
        }
    }

    @Test
    public void shouldCalculateForOne() {
        final Sin sin = new Sin(DEFAULT_PRECISION);
        assertEquals(0.8414, sin.solve(1.0), DEFAULT_PRECISION);
    }

    @Test
    public void shouldCalculateForPeriodic() {
        final Sin sin = new Sin(DEFAULT_PRECISION);
        assertEquals(0.09718, sin.solve(-113D), DEFAULT_PRECISION);
    }
}
