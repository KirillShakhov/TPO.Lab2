package ru.ifmo.tpo.lab2.trigonometric;

import ru.ifmo.tpo.lab2.models.IFunc;

public class Csc implements IFunc {
    private final Sin sin;

    public Csc(Double accuracy){
        this.sin = new Sin(accuracy);
    }

    public Csc(Sin sin){
        this.sin = sin;
    }

    @Override
    public Double solve(Double x) {
        x = x % Math.PI;
        if (x == Math.PI/2 || x == -Math.PI/2) {
            return 1D;
        }
        if (x == 0) {
            return Double.NaN;
        }
        return 1D/sin.solve(x);
    }
}
