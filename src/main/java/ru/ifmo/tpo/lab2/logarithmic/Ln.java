package ru.ifmo.tpo.lab2.logarithmic;

import ru.ifmo.tpo.lab2.models.IFunc;

public class Ln implements IFunc {
    private final IFunc logN;

    public Ln() {
        this.logN = new LogN(0.0000001);
    }

    public Ln(Double accuracy) {
        this.logN = new LogN(accuracy);
    }

    public Ln(IFunc logN) {
        this.logN = logN;
    }

    @Override
    public Double solve(Double x) {
        return logN.solve(x)/logN.solve(Math.E);
    }
}
