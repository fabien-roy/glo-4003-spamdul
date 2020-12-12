package ca.ulaval.glo4003.files.filesystem;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.domain.exceptions.InvalidFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader implements StringFileReader {

  public String readFile(String path) {
    try {
      byte[] jsonFile = Files.readAllBytes(Paths.get(path));

      return new String(jsonFile);
    } catch (IOException ioException) {
      throw new InvalidFileException(path);
    }
  }
}
