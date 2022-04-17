package ru.ifmo.tpo.lab2;

import ru.ifmo.tpo.lab2.logarithmic.Cot;
import ru.ifmo.tpo.lab2.logarithmic.Csc;
import ru.ifmo.tpo.lab2.logarithmic.Ln;
import ru.ifmo.tpo.lab2.logarithmic.Log10;
import ru.ifmo.tpo.lab2.logarithmic.Log2;
import ru.ifmo.tpo.lab2.logarithmic.Log3;

public class Function {

    Cot cot;
    Csc csc;
    Log2 log2;
    Log3 log3;
    Log10 log10;
    Ln ln;
    
    Function(Double accuracy){
        cot = new Cot(accuracy);
        csc = new Csc(accuracy);
        log3 = new Log3(accuracy);
        log10 = new Log10(accuracy);
        ln = new Ln(accuracy);
    }

    public Double solve(Double x){
        if (x <= 0){
            return csc.solve(x)/cot.solve(x);
        }
        else{
            return (((log3.solve(x)*log10.solve(x))*(ln.solve(x)-log3.solve(x)* Math.pow(log2.solve(x), 2)))*(log3.solve(x) + Math.pow(log10.solve(x), 2)));
        }
    }
}
