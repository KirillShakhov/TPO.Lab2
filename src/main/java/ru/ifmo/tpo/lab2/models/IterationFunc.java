package ru.ifmo.tpo.lab2.models;

public abstract class IterationFunc implements IFunc{
    private final int MAX_ITERATIONS = 1000;
    private final Double accuracy;
    
    IterationFunc(Double accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public Double solve(Double x) {
        return Double.valueOf(MAX_ITERATIONS);
    }
}
