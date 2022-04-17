package ru.ifmo.tpo.lab2.funcions;


import ru.ifmo.tpo.lab2.models.IFunc;

public abstract class IterationFunc implements IFunc{
    private static final int MAX_ITERATIONS = 1000;
    
    @Override
    public Double solve(Double x, Double accuracy) {
        return Double.valueOf(MAX_ITERATIONS);
    }
}
