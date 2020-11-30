package ca.ulaval.glo4003.offenses.services.assemblers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseInFrenchDtoBuilder.anOffenseInFrenchDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.services.dto.OffenseDtoInFrench;
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
  @Mock MoneyConverter moneyConverter;

  private OffenseTypeInFrenchAssembler offenseTypeInFrenchAssembler;

  private final OffenseCode offenseCode = createOffenseCode();
  private final Money amount = createMoney();
  private final OffenseDtoInFrench offenseDtoInFrench = anOffenseInFrenchDto().build();

  @Before
  public void setUp() {
    offenseTypeInFrenchAssembler =
        new OffenseTypeInFrenchAssembler(offenseCodeAssembler, moneyConverter);

    when(offenseCodeAssembler.assemble(offenseDtoInFrench.code)).thenReturn(offenseCode);
    when(moneyConverter.convert(offenseDtoInFrench.montant)).thenReturn(amount);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithDescription() {
    OffenseType offenseType = offenseTypeInFrenchAssembler.assemble(offenseDtoInFrench);

    Truth.assertThat(offenseType.getDescription()).isEqualTo(offenseDtoInFrench.infraction);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithCode() {
    OffenseType offenseType = offenseTypeInFrenchAssembler.assemble(offenseDtoInFrench);

    Truth.assertThat(offenseType.getCode()).isEqualTo(offenseCode);
  }

  @Test
  public void whenAssembling_thenReturnOffenseWithAmount() {
    OffenseType offenseType = offenseTypeInFrenchAssembler.assemble(offenseDtoInFrench);

    Truth.assertThat(offenseType.getAmount()).isEqualTo(amount);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenses() {
    List<OffenseDtoInFrench> offenseDtoInFrenches = Collections.nCopies(2, offenseDtoInFrench);

    List<OffenseType> offenseTypes =
        offenseTypeInFrenchAssembler.assembleMany(offenseDtoInFrenches);

    Truth.assertThat(offenseTypes.size()).isEqualTo(2);
  }
}
