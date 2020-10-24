package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseInFrenchDtoBuilder.anOffenseInFrenchDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.OffenseInFrenchDto;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeInFrenchAssemblerTest {

  @Mock OffenseCodeAssembler offenseCodeAssembler;
  @Mock MoneyAssembler moneyAssembler;

  private OffenseTypeInFrenchAssembler offenseTypeInFrenchAssembler;

  private final OffenseCode offenseCode = createOffenseCode();
  private final Money amount = createMoney();
  private final OffenseInFrenchDto offenseInFrenchDto = anOffenseInFrenchDto().build();

  @Before
  public void setUp() {
    offenseTypeInFrenchAssembler =
        new OffenseTypeInFrenchAssembler(offenseCodeAssembler, moneyAssembler);

    when(offenseCodeAssembler.assemble(offenseInFrenchDto.code)).thenReturn(offenseCode);
    when(moneyAssembler.assemble(offenseInFrenchDto.montant)).thenReturn(amount);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithDescription() {
    OffenseType offenseType = offenseTypeInFrenchAssembler.assemble(offenseInFrenchDto);

    Truth.assertThat(offenseType.getDescription()).isEqualTo(offenseInFrenchDto.infraction);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithCode() {
    OffenseType offenseType = offenseTypeInFrenchAssembler.assemble(offenseInFrenchDto);

    Truth.assertThat(offenseType.getCode()).isEqualTo(offenseCode);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithAmount() {
    OffenseType offenseType = offenseTypeInFrenchAssembler.assemble(offenseInFrenchDto);

    Truth.assertThat(offenseType.getAmount()).isEqualTo(amount);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenses() {
    List<OffenseInFrenchDto> offenseInFrenchDtos = Collections.nCopies(2, offenseInFrenchDto);

    List<OffenseType> offenseTypes = offenseTypeInFrenchAssembler.assembleMany(offenseInFrenchDtos);

    Truth.assertThat(offenseTypes.size()).isEqualTo(2);
  }
}
