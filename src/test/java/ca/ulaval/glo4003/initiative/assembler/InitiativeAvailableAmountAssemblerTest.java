package ca.ulaval.glo4003.initiative.assembler;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeAvailableAmountDtoBuilder.aInitiativeAvailableAmountDto;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAvailableAmountDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeAvailableAmountAssemblerTest {
  @Mock private InitiativeAvailableAmountDto initiativeAvailableAmountDto;

  private InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler;

  private final Money availabledAmount = createMoney();

  @Before
  public void setUp() {
    initiativeAvailableAmountAssembler = new InitiativeAvailableAmountAssembler();

    initiativeAvailableAmountDto =
        aInitiativeAvailableAmountDto().withAvailableAmount(availabledAmount.toDouble()).build();
  }

  @Test
  public void whenAssembling_thenReturnInitiativeAvailableAmountDto() {
    InitiativeAvailableAmountDto initiativeAvailableAmountDto =
        initiativeAvailableAmountAssembler.assemble(availabledAmount);

    assertThat(initiativeAvailableAmountDto.availableAmount)
        .isEqualTo(this.initiativeAvailableAmountDto.availableAmount);
  }
}
