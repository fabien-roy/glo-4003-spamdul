package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.InfractionDtoBuilder.anInfractionDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseBuilder.anOffense;
import static ca.ulaval.glo4003.offenses.helpers.OffenseMother.createOffenseCode;

import ca.ulaval.glo4003.offenses.api.dto.InfractionDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseDto;
import ca.ulaval.glo4003.offenses.domain.Offense;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseAssemblerTest {
  private OffenseAssembler offenseAssembler;

  private Offense offense;
  private InfractionDto infractionDto;

  @Before
  public void setUp() {
    offenseAssembler = new OffenseAssembler();

    offense = anOffense().build();
    infractionDto = anInfractionDto().build();
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithDescription() {
    OffenseDto offenseDto = offenseAssembler.assemble(offense);

    Truth.assertThat(offenseDto.description).isEqualTo(offense.getDescription());
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithCode() {
    OffenseDto offenseDto = offenseAssembler.assemble(offense);

    Truth.assertThat(offenseDto.code).isEqualTo(offense.getCode().toString());
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithAmount() {
    OffenseDto offenseDto = offenseAssembler.assemble(offense);

    Truth.assertThat(offenseDto.amount).isEqualTo(offense.getAmount());
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenseDto() {
    List<Offense> offenses = Collections.nCopies(2, offense);

    List<OffenseDto> offenseDtos = offenseAssembler.assembleMany(offenses);

    Truth.assertThat(offenseDtos.size()).isEqualTo(2);
  }

  @Test
  public void whenAssemblingFromInfractionDto_thenReturnOffenseWithDescription() {
    Offense offense = offenseAssembler.assembleFromInfractionDto(infractionDto);

    Truth.assertThat(offense.getDescription()).isEqualTo(infractionDto.infraction);
  }

  @Test
  public void whenAssemblingFromInfractionDto_thenReturnOffenseWithCode() {
    Offense offense = offenseAssembler.assembleFromInfractionDto(infractionDto);

    Truth.assertThat(offense.getCode().toString()).isEqualTo(infractionDto.code);
  }

  @Test
  public void givenLowerCaseCode_whenAssemblingFromInfractionDto_thenReturnOffenseWithCode() {
    String lowerCaseCode = createOffenseCode().toString().toLowerCase();
    infractionDto = anInfractionDto().withCode(lowerCaseCode).build();

    Offense offense = offenseAssembler.assembleFromInfractionDto(infractionDto);

    Truth.assertThat(offense.getCode().toString()).isEqualTo(infractionDto.code.toUpperCase());
  }

  @Test
  public void whenAssemblingFromInfractionDto_thenReturnOffenseWithAmount() {
    Offense offense = offenseAssembler.assembleFromInfractionDto(infractionDto);

    Truth.assertThat(offense.getAmount()).isEqualTo(infractionDto.montant);
  }

  @Test
  public void whenAssemblingManyFromInfractionDtos_thenReturnManyOffenses() {
    List<InfractionDto> infractionDtos = Collections.nCopies(2, infractionDto);

    List<Offense> offenses = offenseAssembler.assembleManyFromInfractionDtos(infractionDtos);

    Truth.assertThat(offenses.size()).isEqualTo(2);
  }
}
