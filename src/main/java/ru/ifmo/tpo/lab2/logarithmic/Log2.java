package ru.ifmo.tpo.lab2.logarithmic;

import ru.ifmo.tpo.lab2.models.IFunc;


public class Log2 implements IFunc {
    private final LogN logN;


    public Log2(Double accuracy) {
        this.logN = new LogN(accuracy);
    }

    public Log2(LogN logN) {
        this.logN = logN;
    }

    @Override
    public Double solve(Double x) {
        return logN.solve(x) / logN.solve(2D);
    }
}