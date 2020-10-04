package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.InfractionDtoBuilder.anInfractionDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;

import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InfractionAssemblerTest {

  private InfractionAssembler infractionAssembler;

  private InfractionDto infractionDto = anInfractionDto().build();

  @Before
  public void setUp() {
    infractionAssembler = new InfractionAssembler();
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithDescription() {
    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getDescription()).isEqualTo(infractionDto.infraction);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithCode() {
    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getCode().toString()).isEqualTo(infractionDto.code);
  }

  @Test
  public void givenLowerCaseCode_whenAssembling_thenReturnOffenseWithCode() {
    String lowerCaseCode = createOffenseCode().toString().toLowerCase();
    infractionDto = anInfractionDto().withCode(lowerCaseCode).build();

    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getCode().toString()).isEqualTo(infractionDto.code.toUpperCase());
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithAmount() {
    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getAmount()).isEqualTo(infractionDto.montant);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenses() {
    List<InfractionDto> infractionDtos = Collections.nCopies(2, infractionDto);

    List<OffenseType> offenseTypes = infractionAssembler.assembleMany(infractionDtos);

    Truth.assertThat(offenseTypes.size()).isEqualTo(2);
  }
}
