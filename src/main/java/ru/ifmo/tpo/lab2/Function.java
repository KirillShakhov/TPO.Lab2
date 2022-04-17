package ru.ifmo.tpo.lab2;

import ru.ifmo.tpo.lab2.logarithmic.Cot;
import ru.ifmo.tpo.lab2.logarithmic.Csc;
import ru.ifmo.tpo.lab2.logarithmic.Ln;
import ru.ifmo.tpo.lab2.logarithmic.Log10;
import ru.ifmo.tpo.lab2.logarithmic.Log2;
import ru.ifmo.tpo.lab2.logarithmic.Log3;

public class Function {
    Cot cot = new Cot();
    Csc csc = new Csc();
    Log2 log2 = new Log2();
    Log3 log3 = new Log3();
    Log10 log10 = new Log10();
    Ln ln = new Ln();
    Function(){
        cot = new Cot();
        csc = new Csc();
        log3 = new Log3();
        log10 = new Log10();
        ln = new Ln();
    }

    public Double solve(Double x, Double eps){
        if (x <= 0){
            return csc.solve(x, eps)/cot.solve(x, eps);
        }
        else{
            return (((log3.solve(x, eps)*log10.solve(x, eps))*(ln.solve(x, eps)-log3.solve(x, eps)* Math.pow(log2.solve(x, eps), 2)))*(log3.solve(x, eps) + Math.pow(log10.solve(x, eps), 2)));
        }
    }
}
