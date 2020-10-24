package ca.ulaval.glo4003.files.filesystem;

import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// This class does not have an interface, it always returns a Properties object
public class PropertyFileReader {
  public Properties readFile(String path) {
    Properties properties = new Properties();
    try {
      FileReader file = new FileReader(path);
      properties.load(file);
    } catch (IOException e) {
      throw new InvalidFileException();
    }
    return properties;
  }
}
