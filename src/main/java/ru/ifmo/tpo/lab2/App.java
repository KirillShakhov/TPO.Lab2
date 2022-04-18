package ru.ifmo.tpo.lab2;

import java.io.IOException;

import ru.ifmo.tpo.lab2.logarithmic.*;
import ru.ifmo.tpo.lab2.trigonometric.*;

public class App {
    public static void main(String[] args) throws IOException {
        Double accuracy = 0.00001;
        Double from = -10D;
        Double to = 10D;
        Double step = 0.5D;

        final Cot cot = new Cot(accuracy);
        CsvWriter.write(
                "csv/cot.csv",
                cot,
                from,
                to,
                step);

        final Csc csc = new Csc(accuracy);
        CsvWriter.write(
                "csv/csc.csv",
                csc,
                from,
                to,
                step);

        final Sin sin = new Sin(accuracy);
        CsvWriter.write(
                "csv/sin.csv",
                sin,
                from,
                to,
                step);

        final Tg tg = new Tg(accuracy);
        CsvWriter.write(
                "csv/tg.csv",
                tg,
                from,
                to,
                step);

        final Ln ln = new Ln(accuracy);
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                from,
                to,
                step);

        final Log2 log2 = new Log2(accuracy);
        CsvWriter.write(
                "csv/log2.csv",
                log2,
                from,
                to,
                step);

        final Log3 log3 = new Log3(accuracy);
        CsvWriter.write(
                "csv/log3.csv",
                log3,
                from,
                to,
                step);

        final Log10 log10 = new Log10(accuracy);
        CsvWriter.write(
                "csv/log10.csv",
                log10,
                from,
                to,
                step);

        final FunctionalSystem fs = new FunctionalSystem(accuracy);
        CsvWriter.write(
                "csv/fs.csv",
                fs,
                from,
                to,
                step);
    }
}