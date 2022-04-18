package ru.ifmo.tpo.lab2.trigonometric;

import ru.ifmo.tpo.lab2.models.IFunc;

public class Cot implements IFunc {
    private final Tg tg;

    public Cot(){
        this.tg = new Tg(0.0001);
    }

    public Cot(Double accuracy){
        this.tg = new Tg(accuracy);
    }

    public Cot(Tg tg){
        this.tg = tg;
    }

    @Override
    public Double solve(Double x) {
        if (x % Math.PI == 0)
            return Double.NaN;
        return -tg.solve(x + Math.PI / 2);
    }
}
