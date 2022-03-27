package ru.ifmo.tpo.lab2.funcions;

import java.math.BigDecimal;

import ru.ifmo.tpo.lab2.intefaces.IFunc;

public abstract class IterationFunc implements IFunc{
    private static final int MAX_ITERATIONS = 1000;
    
    @Override
    public BigDecimal solve(BigDecimal x, BigDecimal accuracy) {
        return BigDecimal.valueOf(MAX_ITERATIONS);
    }
}
