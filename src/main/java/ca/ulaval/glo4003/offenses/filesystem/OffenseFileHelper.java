package ca.ulaval.glo4003.offenses.filesystem;

import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.files.filesystem.JsonHelper;
import ca.ulaval.glo4003.offenses.api.dto.InfractionDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseAssembler;
import ca.ulaval.glo4003.offenses.domain.Offense;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class OffenseFileHelper {
  private JsonHelper jsonHelper = new JsonHelper();
  private ObjectMapper objectMapper = new ObjectMapper();
  private OffenseAssembler offenseAssembler = new OffenseAssembler();

  public List<Offense> getAllOffenses() {
    try {
      String jsonFile = jsonHelper.getFileToString();
      return offenseAssembler.assembleManyFromInfractionDtos(
          objectMapper.readValue(jsonFile, new TypeReference<List<InfractionDto>>() {}));
    } catch (IOException ioException) {
      throw new InvalidFileException();
    }
  }
}
