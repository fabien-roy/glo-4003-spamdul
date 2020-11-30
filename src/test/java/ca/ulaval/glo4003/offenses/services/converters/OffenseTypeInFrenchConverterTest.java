package ca.ulaval.glo4003.offenses.services.converters;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseInFrenchDtoBuilder.anOffenseInFrenchDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.services.assemblers.OffenseCodeAssembler;
import ca.ulaval.glo4003.offenses.services.dto.OffenseDtoInFrench;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeInFrenchConverterTest {

  @Mock OffenseCodeAssembler offenseCodeAssembler;
  @Mock MoneyConverter moneyConverter;

  private OffenseTypeInFrenchConverter offenseTypeInFrenchConverter;

  private final OffenseCode offenseCode = createOffenseCode();
  private final Money amount = createMoney();
  private final OffenseDtoInFrench offenseDtoInFrench = anOffenseInFrenchDto().build();

  @Before
  public void setUp() {
    offenseTypeInFrenchConverter =
        new OffenseTypeInFrenchConverter(offenseCodeAssembler, moneyConverter);

    when(offenseCodeAssembler.assemble(offenseDtoInFrench.code)).thenReturn(offenseCode);
    when(moneyConverter.convert(offenseDtoInFrench.montant)).thenReturn(amount);
  }

  @Test
  public void whenConvertingMany_thenReturnManyOffenses() {
    int numberOfCopies = 2;

    List<OffenseType> offenseTypes =
        offenseTypeInFrenchConverter.assembleMany(
            Collections.nCopies(numberOfCopies, offenseDtoInFrench));

    assertThat(offenseTypes.size()).isEqualTo(numberOfCopies);
  }

  @Test
  public void whenConvertingMany_thenReturnOffenseWithDescription() {
    List<OffenseType> offenseTypes =
        offenseTypeInFrenchConverter.assembleMany(Collections.singletonList(offenseDtoInFrench));

    assertThat(offenseTypes.get(0).getDescription()).isEqualTo(offenseDtoInFrench.infraction);
  }

  @Test
  public void whenConvertingMany_thenReturnOffenseWithCode() {
    List<OffenseType> offenseTypes =
        offenseTypeInFrenchConverter.assembleMany(Collections.singletonList(offenseDtoInFrench));

    assertThat(offenseTypes.get(0).getCode()).isEqualTo(offenseCode);
  }

  @Test
  public void whenConvertingMany_thenReturnOffenseWithAmount() {
    List<OffenseType> offenseTypes =
        offenseTypeInFrenchConverter.assembleMany(Collections.singletonList(offenseDtoInFrench));

    assertThat(offenseTypes.get(0).getAmount()).isEqualTo(amount);
  }
}
