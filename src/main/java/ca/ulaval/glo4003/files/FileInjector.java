package ca.ulaval.glo4003.files;

import ca.ulaval.glo4003.files.filesystem.JsonHelper;

public class FileInjector {

  public JsonHelper createJsonHelper() {
    return new JsonHelper();
  }
}
