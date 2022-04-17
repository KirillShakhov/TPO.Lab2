package ru.ifmo.tpo.lab2.funcions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ru.ifmo.tpo.lab2.models.IFunc;

public class Sin implements IFunc {

    @Override
    public Double solve(Double x, Double accuracy) {
        double X = x.doubleValue();

        double PI2 = Math.PI * 2;
        int i = 0;
        BigDecimal sum = new BigDecimal(0);
        BigDecimal prev;

        if (X >= 0)
            while (X > PI2) 
                X -= PI2;
        else 
            while (X < PI2)
                X += PI2;

        do {
            prev = sum;
            sum = sum.add(minusOnePow(i).multiply(prod(X, 2 * i + 1)));
            i++;
        } while (new BigDecimal("0.1").pow(BigDecimal.valueOf(accuracy).scale()).compareTo(prev.subtract(sum).abs()) < 0);

        return sum.setScale(BigDecimal.valueOf(accuracy).scale(), RoundingMode.HALF_EVEN).doubleValue();
    }

    private static BigDecimal minusOnePow(int n) {
        int val = 1 - (n % 2) * 2;
        return BigDecimal.valueOf(val);
    }

    private static BigDecimal prod(double x, int n) {
        BigDecimal accum = new BigDecimal(1);

        for (int i = 1; i <= n; i++) {
            accum = accum.multiply(BigDecimal.valueOf(x / i));
        }

        return accum;
    }

}
