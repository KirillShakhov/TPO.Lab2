package ru.ifmo.tpo.lab2.logarithmic;

import ru.ifmo.tpo.lab2.models.IFunc;


public class Log10 implements IFunc {
    private final IFunc logN;

    public Log10(Double accuracy) {
        this.logN = new LogN(accuracy);
    }

    public Log10(IFunc logN) {
        this.logN = logN;
    }

    @Override
    public Double solve(Double x) {
        return logN.solve(x)/logN.solve(10D);
    }
}
