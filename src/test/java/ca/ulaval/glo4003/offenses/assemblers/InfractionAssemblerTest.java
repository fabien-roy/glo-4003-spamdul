package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.InfractionDtoBuilder.anInfractionDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;

import ca.ulaval.glo4003.offenses.domain.Offense;
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
    Offense offense = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offense.getDescription()).isEqualTo(infractionDto.infraction);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithCode() {
    Offense offense = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offense.getCode().toString()).isEqualTo(infractionDto.code);
  }

  @Test
  public void givenLowerCaseCode_whenAssembling_thenReturnOffenseWithCode() {
    String lowerCaseCode = createOffenseCode().toString().toLowerCase();
    infractionDto = anInfractionDto().withCode(lowerCaseCode).build();

    Offense offense = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offense.getCode().toString()).isEqualTo(infractionDto.code.toUpperCase());
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithAmount() {
    Offense offense = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offense.getAmount()).isEqualTo(infractionDto.montant);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenses() {
    List<InfractionDto> infractionDtos = Collections.nCopies(2, infractionDto);

    List<Offense> offenses = infractionAssembler.assembleMany(infractionDtos);

    Truth.assertThat(offenses.size()).isEqualTo(2);
  }
}
