package ca.ulaval.glo4003.files.filesystem;

import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.domain.exceptions.InvalidFileException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileReader implements StringMatrixFileReader {

  public List<List<String>> readFile(String path) {
    try {
      return convertFileToStringMatrix(path);
    } catch (IOException exception) {
      throw new InvalidFileException(path);
    }
  }

  private List<List<String>> convertFileToStringMatrix(String path) throws IOException {
    List<List<String>> csvData = new ArrayList<>();

    String line;
    BufferedReader bufferedReader =
        Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8);

    while ((line = bufferedReader.readLine()) != null) {
      csvData.add(Arrays.asList(line.split(",")));
    }

    return csvData;
  }
}
