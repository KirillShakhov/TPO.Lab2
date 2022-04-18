package ru.ifmo.tpo.lab2;

import ru.ifmo.tpo.lab2.models.IFunc;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

public class CsvWriter {

  public static void write(
      final String filename,
      final IFunc function,
      final Double from,
      final Double to,
      final Double step)
      throws IOException {
    final Path path = Paths.get(filename);
    final File file = new File(path.toUri());
    if (file.exists()) {
      Files.delete(path);
    }
    file.createNewFile();
    final PrintWriter printWriter = new PrintWriter(file);
    for (Double current = from; current <= to; current = current += step) {
      printWriter.println(current + "," + function.solve(current));
    }
    printWriter.close();
  }

}
