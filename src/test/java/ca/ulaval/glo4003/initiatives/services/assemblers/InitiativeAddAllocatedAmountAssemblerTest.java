package ca.ulaval.glo4003.initiatives.services.assemblers;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeAddAllocatedAmountDtoBuilder.aInitiativeAddAllocatedAmountDTO;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeAddAllocatedAmountDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeAddAllocatedAmountAssemblerTest {
  private InitiativeAddAllocatedAmountAssembler initiativeAddAllocatedAmountAssembler;
  @Mock private MoneyConverter moneyConverter;

  private InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
      aInitiativeAddAllocatedAmountDTO().build();

  @Before
  public void setUp() {
    initiativeAddAllocatedAmountAssembler =
        new InitiativeAddAllocatedAmountAssembler(moneyConverter);

    when(moneyConverter.convert(initiativeAddAllocatedAmountDto.amountToAdd))
        .thenReturn(Money.fromDouble(initiativeAddAllocatedAmountDto.amountToAdd));
  }

  @Test
  public void whenAssembling_thenReturnMoney() {
    Money money = initiativeAddAllocatedAmountAssembler.assemble(initiativeAddAllocatedAmountDto);

    Truth.assertThat(money.toDouble()).isEqualTo(initiativeAddAllocatedAmountDto.amountToAdd);
  }
}
