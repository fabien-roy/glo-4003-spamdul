package ca.ulaval.glo4003.offenses.filesystem;

import static ca.ulaval.glo4003.offenses.helpers.OffenseInFrenchDtoBuilder.anOffenseInFrenchDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.exceptions.InvalidFileException;
import ca.ulaval.glo4003.offenses.filesystem.dto.OffenseInFrenchDto;
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
public class OffenseFileHelperTest {

  @Mock private StringFileReader fileReader;

  private OffenseFileHelper offenseFileHelper;

  private static final String JSON_FILE = "data/infraction.json";
  private final OffenseInFrenchDto offenseInFrenchDto = anOffenseInFrenchDto().build();
  private final List<OffenseInFrenchDto> offenseInFrenchDtos =
      Collections.singletonList(offenseInFrenchDto);

  @Before
  public void setUp() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonData = objectMapper.writeValueAsString(offenseInFrenchDtos);

    when(fileReader.readFile(JSON_FILE)).thenReturn(jsonData);

    offenseFileHelper = new OffenseFileHelper(fileReader);
  }

  @Test
  public void whenReadingFile_thenReturnListOfOffenseInFrenchDto() {
    List<OffenseInFrenchDto> readOffenseInFrenchDtos = offenseFileHelper.getOffenseInFrench();

    Truth.assertThat(readOffenseInFrenchDtos).hasSize(1);
    Truth.assertThat(readOffenseInFrenchDtos.get(0).toString())
        .isEqualTo(offenseInFrenchDto.toString());
  }

  @Test(expected = InvalidFileException.class)
  public void givenInvalidJsonData_whenReadingFile_thenThrowInvalidFileException() {
    String invalidJsonData = "invalidJsonData";
    when(fileReader.readFile(JSON_FILE)).thenReturn(invalidJsonData);

    offenseFileHelper.getOffenseInFrench();
  }
}
