package ca.ulaval.glo4003.offenses.filesystem;

import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.files.filesystem.JsonHelper;
import ca.ulaval.glo4003.offenses.assemblers.InfractionAssembler;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

// TODO : Inject what you need
// TODO : Test infraction file helper
public class InfractionFileHelper {
  private final JsonHelper jsonHelper = new JsonHelper();
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final InfractionAssembler infractionAssembler = new InfractionAssembler();

  public List<Offense> getAllOffenses() {
    try {
      String jsonFile = jsonHelper.getFileToString();
      return infractionAssembler.assembleMany(
          objectMapper.readValue(jsonFile, new TypeReference<List<InfractionDto>>() {}));
    } catch (IOException exception) {
      throw new InvalidFileException();
    }
  }
}
