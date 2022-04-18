package ru.ifmo.tpo.lab2;

import ru.ifmo.tpo.lab2.logarithmic.Ln;
import ru.ifmo.tpo.lab2.logarithmic.Log10;
import ru.ifmo.tpo.lab2.logarithmic.Log2;
import ru.ifmo.tpo.lab2.logarithmic.Log3;
import ru.ifmo.tpo.lab2.models.IFunc;
import ru.ifmo.tpo.lab2.trigonometric.Cot;
import ru.ifmo.tpo.lab2.trigonometric.Csc;

public class FunctionalSystem implements IFunc {
    Cot cot;
    Csc csc;
    Log2 log2;
    Log3 log3;
    Log10 log10;
    Ln ln;
    
    FunctionalSystem(Double accuracy){
        this.cot = new Cot(accuracy);
        this.csc = new Csc(accuracy);
        this.log2 = new Log2(accuracy);
        this.log3 = new Log3(accuracy);
        this.log10 = new Log10(accuracy);
        this.ln = new Ln(accuracy);
    }

    FunctionalSystem(Cot cot, Csc csc, Log2 log2, Log3 log3, Log10 log10, Ln ln){
        this.cot = cot;
        this.csc = csc;
        this.log2 = log2;
        this.log3 = log3;
        this.log10 = log10;
        this.ln = ln;
    }

    @Override
    public Double solve(Double x){
        if (x <= 0){
            return csc.solve(x)/cot.solve(x);
        }
        else{
            return ((Math.pow((((log3.solve(x)*log10.solve(x))*(ln.solve(x)-log3.solve(x)))*log10.solve(x)),2))*Math.pow((log3.solve(x)+log2.solve(x)), 2));
        }
    }
}
