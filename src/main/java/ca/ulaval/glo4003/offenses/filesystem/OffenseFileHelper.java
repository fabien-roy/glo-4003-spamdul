package ca.ulaval.glo4003.offenses.filesystem;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.offenses.filesystem.dto.OffenseInFrenchDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class OffenseFileHelper {
  private static final String OFFENSE_IN_FRENCH_PATH = "data/infraction.json";

  private final StringFileReader fileReader;

  public OffenseFileHelper(StringFileReader fileReader) {
    this.fileReader = fileReader;
  }

  public List<OffenseInFrenchDto> getOffenseInFrench() {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonData = fileReader.readFile(OFFENSE_IN_FRENCH_PATH);
      return objectMapper.readValue(jsonData, new TypeReference<List<OffenseInFrenchDto>>() {});
    } catch (IOException exception) {
      throw new InvalidFileException();
    }
  }
}
