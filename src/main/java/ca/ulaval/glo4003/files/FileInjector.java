package ca.ulaval.glo4003.files;

import ca.ulaval.glo4003.files.filesystem.JsonFileReader;

public class FileInjector {

  public JsonFileReader createJsonFileReader() {
    return new JsonFileReader();
  }
}
