package ru.ifmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

public class TgTest {
    static Tg tg;
    public final static double DEFAULT_PRECISION = 0.0000001;

    @BeforeAll
    public static void setUp() {
        tg = new Tg(DEFAULT_PRECISION);
    }


    @ParameterizedTest(name = "tg({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = {0, Math.PI/12, Math.PI/8, Math.PI/6, Math.PI/4, Math.PI/3, -Math.PI/12, -Math.PI/8, -Math.PI/6, -Math.PI/4, -Math.PI/3, Math.PI})
    void checkPiDots(double param) {
        assertAll(
                () -> assertEquals(Math.tan(param), tg.solve(param), 0.015)
        );
    }

    @ParameterizedTest(name = "tg({0}) = NaN")
    @DisplayName("Check PI dots NaN")
    @ValueSource(doubles = {Math.PI/2, -Math.PI/2})
    void checkPiDotsNaN(double param) {
        assertAll(
                () -> assertEquals(Double.NaN, tg.solve(param))
        );
    }



    @ParameterizedTest(name = "tg({0}) = {1}")
    @DisplayName("Check table dots")
    @CsvFileSource(resources = "/tableTg.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsMinusPiAndPi(double x, double y) {
        assertAll(
                () -> assertEquals(y, tg.solve(x), 0.0001)
        );
    }
}
