package ru.ifmo.tpo.lab2.logarithmic;


import ru.ifmo.tpo.lab2.models.IFunc;

public class LogN implements IFunc {
    private final Double accuracy;

    public LogN() {
        this.accuracy = 0.0001;
    }

    public LogN(Double accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public Double solve(Double x) {
        if (x <= 0)
            return Double.NaN;
        double arg = (x - 1) / (x + 1);
        double result;
        double prevResult;
        result = arg;
        prevResult = Double.MAX_VALUE;
        int k = 3;

        while (Math.abs(result - prevResult) > accuracy) {
            prevResult = result;
            result += Math.pow(arg, k) / k;
            k += 2;
        }
        return (2 * result);
    }
}