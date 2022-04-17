package ru.ifmo.tpo.lab2.logarithmic;

import java.math.BigDecimal;

import ru.ifmo.tpo.lab2.models.IFunc;
import ru.ifmo.tpo.lab2.models.NotANumberException;

public class Ln implements IFunc {
    LogN logN = new LogN();

    public Ln() {
        this.logN = new LogN();
    }

    public Ln(LogN logN) {
        this.logN = logN;
    }

    @Override
    public Double solve(Double x, Double accuracy) {
        return logN.solve(x, accuracy)/logN.solve(Double.valueOf(Math.E), accuracy);
    }
}
