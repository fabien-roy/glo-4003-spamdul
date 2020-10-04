package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.domain.Offense;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeAssemblerTest {
  private OffenseTypeAssembler offenseTypeAssembler;

  private final Offense offense = anOffenseType().build();

  @Before
  public void setUp() {
    offenseTypeAssembler = new OffenseTypeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithDescription() {
    OffenseTypeDto offenseTypeDto = offenseTypeAssembler.assemble(offense);

    Truth.assertThat(offenseTypeDto.description).isEqualTo(offense.getDescription());
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithCode() {
    OffenseTypeDto offenseTypeDto = offenseTypeAssembler.assemble(offense);

    Truth.assertThat(offenseTypeDto.code).isEqualTo(offense.getCode().toString());
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithAmount() {
    OffenseTypeDto offenseTypeDto = offenseTypeAssembler.assemble(offense);

    Truth.assertThat(offenseTypeDto.amount).isEqualTo(offense.getAmount());
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenseDto() {
    List<Offense> offenses = Collections.nCopies(2, offense);

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeAssembler.assembleMany(offenses);

    Truth.assertThat(offenseTypeDtos.size()).isEqualTo(2);
  }
}
