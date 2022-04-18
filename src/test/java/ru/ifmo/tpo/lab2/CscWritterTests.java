package ru.ifmo.tpo.lab2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CscWritterTests {
    @Test
    public void checkCscWritter() throws IOException {
        Double from = -1D;
        Double to = 1D;
        Double step = 1D;
        String path = "csv/test_csv.csv";

        CsvWriter.write(
                path,
                x -> Math.sin(x),
                from,
                to,
                step);

        File f = new File(path);
        assertTrue(f.exists() && !f.isDirectory());
        StringBuilder res = new StringBuilder();
        Scanner input = new Scanner(f);
        while (input.hasNextLine())
        {
            res.append(input.nextLine()).append("\n");
        }
        assertEquals("-1.0,-0.8414709848078965\n" +
                "0.0,0.0\n" +
                "1.0,0.8414709848078965\n", res.toString());
    }
}
