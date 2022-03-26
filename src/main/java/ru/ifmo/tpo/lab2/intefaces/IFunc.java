package ru.ifmo.tpo.lab2.intefaces;

import java.math.BigDecimal;

public interface IFunc {
    BigDecimal solve(final BigDecimal x, final BigDecimal accuracy);
}