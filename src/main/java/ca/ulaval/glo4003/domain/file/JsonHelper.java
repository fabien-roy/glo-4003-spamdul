package ca.ulaval.glo4003.domain.file;

import ca.ulaval.glo4003.domain.file.exceptions.InvalidFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHelper {

  public String getFileToString() {

    try {
      String jsonPath = "data/infraction.json";
      byte[] jsonFile = Files.readAllBytes(Paths.get(jsonPath));

      return new String(jsonFile);
    } catch (IOException ioException) {
      throw new InvalidFileException();
    }
  }
}
