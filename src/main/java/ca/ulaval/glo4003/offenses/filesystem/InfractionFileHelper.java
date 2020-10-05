package ca.ulaval.glo4003.offenses.filesystem;

import ca.ulaval.glo4003.files.domain.StringFileHelper;
import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class InfractionFileHelper {
  private static final String INFRACTIONS_PATH = "data/infraction.json";

  private final StringFileHelper fileHelper;

  public InfractionFileHelper(StringFileHelper fileHelper) {
    this.fileHelper = fileHelper;
  }

  // TODO : Test InfractionFileHelper.readInfractions
  public List<InfractionDto> readInfractions() {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonFile = fileHelper.readFile(INFRACTIONS_PATH);
      return objectMapper.readValue(jsonFile, new TypeReference<List<InfractionDto>>() {});
    } catch (IOException exception) {
      throw new InvalidFileException();
    }
  }
}
