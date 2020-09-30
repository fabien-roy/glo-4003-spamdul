package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import ca.ulaval.glo4003.domain.file.JsonHelper;
import ca.ulaval.glo4003.domain.file.exceptions.InvalidFileException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class OffenseHelper {
  private JsonHelper jsonHelper = new JsonHelper();
  private ObjectMapper objectMapper = new ObjectMapper();

  public List<OffenseDto> getAllOffenses() {
    try {
      String jsonFile = jsonHelper.getFileToString();
      return objectMapper.readValue(jsonFile, new TypeReference<List<OffenseDto>>() {});
    } catch (IOException ioException) {
      throw new InvalidFileException();
    }
  }
}
