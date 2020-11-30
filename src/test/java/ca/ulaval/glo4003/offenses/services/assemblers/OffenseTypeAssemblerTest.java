package ca.ulaval.glo4003.offenses.services.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.services.dto.OffenseTypeDto;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeAssemblerTest {
  private OffenseTypeAssembler offenseTypeAssembler;

  private final OffenseType offenseType = anOffenseType().build();

  @Before
  public void setUp() {
    offenseTypeAssembler = new OffenseTypeAssembler();
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenseDto() {
    int numberOfCopies = 2;

    List<OffenseTypeDto> offenseTypeDtos =
        offenseTypeAssembler.assembleMany(Collections.nCopies(numberOfCopies, offenseType));

    assertThat(offenseTypeDtos.size()).isEqualTo(2);
  }

  @Test
  public void whenAssemblingMany_thenReturnOffenseDtoWithDescription() {
    List<OffenseTypeDto> offenseTypeDtos =
        offenseTypeAssembler.assembleMany(Collections.singletonList(offenseType));

    assertThat(offenseTypeDtos.get(0).description).isEqualTo(offenseType.getDescription());
  }

  @Test
  public void whenAssemblingMany_thenReturnOffenseDtoWithCode() {
    List<OffenseTypeDto> offenseTypeDtos =
        offenseTypeAssembler.assembleMany(Collections.singletonList(offenseType));

    assertThat(offenseTypeDtos.get(0).code).isEqualTo(offenseType.getCode().toString());
  }

  @Test
  public void whenAssemblingMany_thenReturnOffenseDtoWithAmount() {
    List<OffenseTypeDto> offenseTypeDtos =
        offenseTypeAssembler.assembleMany(Collections.singletonList(offenseType));

    assertThat(offenseTypeDtos.get(0).amount).isEqualTo(offenseType.getAmount().toDouble());
  }
}
