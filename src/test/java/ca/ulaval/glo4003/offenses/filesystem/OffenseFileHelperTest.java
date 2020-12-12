package ca.ulaval.glo4003.offenses.filesystem;

import static ca.ulaval.glo4003.offenses.helpers.OffenseInFrenchDtoBuilder.anOffenseInFrenchDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.domain.exceptions.InvalidFileException;
import ca.ulaval.glo4003.offenses.services.dto.OffenseDtoInFrench;
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
  private final OffenseDtoInFrench offenseDtoInFrench = anOffenseInFrenchDto().build();
  private final List<OffenseDtoInFrench> offenseDtoInFrenches =
      Collections.singletonList(offenseDtoInFrench);

  @Before
  public void setUp() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonData = objectMapper.writeValueAsString(offenseDtoInFrenches);

    when(fileReader.readFile(JSON_FILE)).thenReturn(jsonData);

    offenseFileHelper = new OffenseFileHelper(fileReader);
  }

  @Test
  public void whenReadingFile_thenReturnListOfOffenseInFrenchDto() {
    List<OffenseDtoInFrench> readOffenseDtoInFrenches = offenseFileHelper.getOffenseInFrench();

    Truth.assertThat(readOffenseDtoInFrenches).hasSize(1);
    Truth.assertThat(readOffenseDtoInFrenches.get(0).toString())
        .isEqualTo(offenseDtoInFrench.toString());
  }

  @Test(expected = InvalidFileException.class)
  public void givenInvalidJsonData_whenReadingFile_thenThrowInvalidFileException() {
    String invalidJsonData = "invalidJsonData";
    when(fileReader.readFile(JSON_FILE)).thenReturn(invalidJsonData);

    offenseFileHelper.getOffenseInFrench();
  }
}
