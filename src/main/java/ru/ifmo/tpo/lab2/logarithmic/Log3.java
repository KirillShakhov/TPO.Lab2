package ru.ifmo.tpo.lab2.logarithmic;


import ru.ifmo.tpo.lab2.models.IFunc;

public class Log3 implements IFunc {
    private final IFunc logN;

    public Log3() {
        this.logN = new LogN(0.0001);
    }

    public Log3(Double accuracy) {
        this.logN = new LogN(accuracy);
    }

    public Log3(IFunc logN) {
        this.logN = logN;
    }

    @Override
    public Double solve(Double x) {
        return logN.solve(x)/logN.solve(3D);
    }
}