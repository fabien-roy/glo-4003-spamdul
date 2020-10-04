package ca.ulaval.glo4003.offenses.infrastructure;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.exceptions.OffenseTypeNotFoundException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OffenseTypeRepositoryInMemoryTest {

  private OffenseTypeRepository offenseTypeRepository;

  @Before
  public void setup() {
    offenseTypeRepository = new OffenseTypeRepositoryInMemory();
  }

  @Test(expected = OffenseTypeNotFoundException.class)
  public void givenNonExistentCode_whenFindingByCode_thenThrowOffenseTypeNotFoundException() {
    OffenseCodes nonExistentCode = createOffenseCode();

    offenseTypeRepository.findByCode(nonExistentCode);
  }

  @Test
  public void whenGettingAll_thenReturnAllOffenseTypes() {
    Offense offense = anOffenseType().build();
    offenseTypeRepository.save(offense);

    List<Offense> storedOffenses = offenseTypeRepository.getAll();

    assertThat(storedOffenses).contains(offense);
  }
}
