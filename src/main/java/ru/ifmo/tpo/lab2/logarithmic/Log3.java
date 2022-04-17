package ru.ifmo.tpo.lab2.logarithmic;


import ru.ifmo.tpo.lab2.models.IFunc;

public class Log3 implements IFunc {
    LogN logN = new LogN();

    public Log3() {
        this.logN = new LogN();
    }
    
    public Log3(LogN logN) {
        this.logN = logN;
    }

    @Override
    public Double solve(Double x, Double accuracy) {
        return logN.solve(x, accuracy)/logN.solve(Double.valueOf(3), accuracy);
    }
}