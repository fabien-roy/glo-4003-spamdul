package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.InfractionDtoBuilder.anInfractionDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfractionAssemblerTest {

  @Mock OffenseCodeAssembler offenseCodeAssembler;
  @Mock MoneyAssembler moneyAssembler;

  private InfractionAssembler infractionAssembler;

  private final OffenseCode offenseCode = createOffenseCode();
  private final Money amount = createMoney();
  private final InfractionDto infractionDto = anInfractionDto().build();

  @Before
  public void setUp() {
    infractionAssembler = new InfractionAssembler(offenseCodeAssembler, moneyAssembler);

    when(offenseCodeAssembler.assemble(infractionDto.code)).thenReturn(offenseCode);
    when(moneyAssembler.assemble(infractionDto.montant)).thenReturn(amount);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithDescription() {
    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getDescription()).isEqualTo(infractionDto.infraction);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithCode() {
    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getCode()).isEqualTo(offenseCode);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithAmount() {
    OffenseType offenseType = infractionAssembler.assemble(infractionDto);

    Truth.assertThat(offenseType.getAmount()).isEqualTo(amount);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenses() {
    List<InfractionDto> infractionDtos = Collections.nCopies(2, infractionDto);

    List<OffenseType> offenseTypes = infractionAssembler.assembleMany(infractionDtos);

    Truth.assertThat(offenseTypes.size()).isEqualTo(2);
  }
}
