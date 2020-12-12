package ca.ulaval.glo4003.offenses.infrastructure;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.domain.exceptions.NotFoundOffenseTypeException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OffenseTypeRepositoryInMemoryTest {

  private OffenseTypeRepository offenseTypeRepository;

  @Before
  public void setUp() {
    offenseTypeRepository = new OffenseTypeRepositoryInMemory();
  }

  @Test(expected = NotFoundOffenseTypeException.class)
  public void givenNonExistentCode_whenFindingByCode_thenThrowOffenseTypeNotFoundException() {
    OffenseCode nonExistentCode = createOffenseCode();

    offenseTypeRepository.findByCode(nonExistentCode);
  }

  @Test
  public void whenGettingAll_thenReturnAllOffenseTypes() {
    OffenseType offenseType = anOffenseType().build();
    offenseTypeRepository.save(offenseType);

    List<OffenseType> offenseTypes = offenseTypeRepository.getAll();

    assertThat(offenseTypes).contains(offenseType);
  }
}
