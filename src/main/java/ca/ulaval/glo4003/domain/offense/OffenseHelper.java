package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.InfractionDto;
import ca.ulaval.glo4003.domain.file.JsonHelper;
import ca.ulaval.glo4003.domain.file.exceptions.InvalidFileException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class OffenseHelper {
  private JsonHelper jsonHelper = new JsonHelper();
  private ObjectMapper objectMapper = new ObjectMapper();
  private OffenseAssembler offenseAssembler = new OffenseAssembler();

  public List<Offense> getAllOffenses() {
    try {
      String jsonFile = jsonHelper.getFileToString();
      return offenseAssembler.assembleOffenses(
          objectMapper.readValue(jsonFile, new TypeReference<List<InfractionDto>>() {}));
    } catch (IOException ioException) {
      throw new InvalidFileException();
    }
  }
}
