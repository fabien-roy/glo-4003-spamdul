package ca.ulaval.glo4003.offenses.filesystem;

import static ca.ulaval.glo4003.offenses.helpers.InfractionDtoBuilder.anInfractionDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfractionFileHelperTest {

  @Mock private StringFileReader fileReader;

  private InfractionFileHelper infractionFileHelper;

  private static final String JSON_FILE = "data/infraction.json";
  private final InfractionDto infractionDto = anInfractionDto().build();
  private final List<InfractionDto> infractionDtos = Collections.singletonList(infractionDto);

  @Before
  public void setUp() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonData = objectMapper.writeValueAsString(infractionDtos);

    when(fileReader.readFile(JSON_FILE)).thenReturn(jsonData);

    infractionFileHelper = new InfractionFileHelper(fileReader);
  }

  @Test
  public void whenReadingFile_thenReturnListOfInfractionDto() {
    List<InfractionDto> readInfractionDtos = infractionFileHelper.getInfractions();

    Truth.assertThat(readInfractionDtos).hasSize(1);
    Truth.assertThat(readInfractionDtos.get(0).toString()).isEqualTo(infractionDto.toString());
  }

  @Test(expected = InvalidFileException.class)
  public void givenInvalidJsonData_whenReadingFile_thenThrowInvalidFileException() {
    String invalidJsonData = "invalidJsonData";
    when(fileReader.readFile(JSON_FILE)).thenReturn(invalidJsonData);

    infractionFileHelper.getInfractions();
  }
}
