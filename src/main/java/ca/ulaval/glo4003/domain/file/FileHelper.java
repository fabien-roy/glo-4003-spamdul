package ca.ulaval.glo4003.domain.file;

import ca.ulaval.glo4003.domain.file.exceptions.InvalidFileException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHelper {

  public List<List<String>> getCsvFileInJavaFormat(String path) {
    try {
      return readFile(path);
    } catch (IOException exception) {
      throw new InvalidFileException();
    }
  }

  private List<List<String>> readFile(String path) throws IOException {
    List<List<String>> csvData = new ArrayList<>();

    String line;
    File file = new File(path);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

    while ((line = bufferedReader.readLine()) != null) {
      csvData.add(Arrays.asList(line.split(",")));
    }

    return csvData;
  }
}
