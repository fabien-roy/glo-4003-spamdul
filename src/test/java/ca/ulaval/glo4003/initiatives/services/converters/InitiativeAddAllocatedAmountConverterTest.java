package ca.ulaval.glo4003.initiatives.services.converters;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeAddAllocatedAmountDtoBuilder.aInitiativeAddAllocatedAmountDTO;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeAddAllocatedAmountDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeAddAllocatedAmountConverterTest {
  @Mock private MoneyConverter moneyConverter;

  private InitiativeAddAllocatedAmountConverter initiativeAddAllocatedAmountConverter;

  private final InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
      aInitiativeAddAllocatedAmountDTO().build();

  @Before
  public void setUp() {
    initiativeAddAllocatedAmountConverter =
        new InitiativeAddAllocatedAmountConverter(moneyConverter);

    when(moneyConverter.convert(initiativeAddAllocatedAmountDto.amountToAdd))
        .thenReturn(Money.fromDouble(initiativeAddAllocatedAmountDto.amountToAdd));
  }

  @Test
  public void whenConverting_thenReturnMoney() {
    Money money = initiativeAddAllocatedAmountConverter.convert(initiativeAddAllocatedAmountDto);

    assertThat(money.toDouble()).isEqualTo(initiativeAddAllocatedAmountDto.amountToAdd);
  }
}
