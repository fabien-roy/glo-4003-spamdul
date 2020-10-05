package ca.ulaval.glo4003.files.filesystem;

import ca.ulaval.glo4003.files.domain.StringMatrixFileHelper;
import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO : Test CsvHelper
public class CsvHelper implements StringMatrixFileHelper {

  public List<List<String>> readFile(String path) {
    try {
      return convertFileToStringMatrix(path);
    } catch (IOException exception) {
      throw new InvalidFileException();
    }
  }

  private List<List<String>> convertFileToStringMatrix(String path) throws IOException {
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
