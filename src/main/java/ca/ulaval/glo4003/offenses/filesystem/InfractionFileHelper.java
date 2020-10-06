package ca.ulaval.glo4003.offenses.filesystem;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class InfractionFileHelper {
  private static final String INFRACTIONS_PATH = "data/infraction.json";

  private final StringFileReader fileReader;

  public InfractionFileHelper(StringFileReader fileReader) {
    this.fileReader = fileReader;
  }

  // TODO : Test InfractionFileHelper.getInfractions
  public List<InfractionDto> getInfractions() {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonFile = fileReader.readFile(INFRACTIONS_PATH);
      return objectMapper.readValue(jsonFile, new TypeReference<List<InfractionDto>>() {});
    } catch (IOException exception) {
      throw new InvalidFileException();
    }
  }
}
