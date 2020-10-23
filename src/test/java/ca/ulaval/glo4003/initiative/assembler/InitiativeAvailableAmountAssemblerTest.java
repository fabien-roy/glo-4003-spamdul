package ca.ulaval.glo4003.initiative.assembler;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeAvailableAmountDtoBuilder.aInitiativeAvailableAmountDto;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAvailableAmountDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeAvailableAmountAssemblerTest {
  private InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler;
  @Mock private InitiativeAvailableAmountDto initiativeAvailableAmountDto;

  private Double AVAILABLE_AMOUNT = createAmount();

  @Before
  public void setUp() {
    initiativeAvailableAmountAssembler = new InitiativeAvailableAmountAssembler();

    initiativeAvailableAmountDto =
        aInitiativeAvailableAmountDto().withAvailableAmount(AVAILABLE_AMOUNT).build();
  }

  @Test
  public void whenAssembling_thenReturnInitiativeAvailableAmountDto() {
    InitiativeAvailableAmountDto AssembledInitiativeAvailableAmountDto =
        initiativeAvailableAmountAssembler.assemble(Money.fromDouble(AVAILABLE_AMOUNT));

    Truth.assertThat(AssembledInitiativeAvailableAmountDto.availableAmount)
        .isEqualTo(initiativeAvailableAmountDto.availableAmount);
  }
}
