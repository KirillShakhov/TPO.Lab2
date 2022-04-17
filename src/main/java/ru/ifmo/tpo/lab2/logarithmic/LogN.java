package ru.ifmo.tpo.lab2.logarithmic;


import ru.ifmo.tpo.lab2.models.IFunc;

public class LogN implements IFunc {

    @Override
    public Double solve(Double x, Double accuracy) {
        if (x.doubleValue() <= 0)
            return Double.NaN;
        double arg = (x.doubleValue() - 1) / (x.doubleValue() + 1);
        double result;
        double prevResult;
        result = arg;
        prevResult = Double.MAX_VALUE;
        int k = 3;

        while (Math.abs(result - prevResult) > accuracy.doubleValue()) {
            prevResult = result;
            result += Math.pow(arg, k) / k;
            k += 2;
        }
        return (2 * result);
    }
}