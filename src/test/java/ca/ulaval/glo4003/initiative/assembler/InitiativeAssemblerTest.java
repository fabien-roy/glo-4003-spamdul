package ca.ulaval.glo4003.initiative.assembler;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeBuilder.aInitiative;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeAssemblerTest {
  private InitiativeAssembler initiativeAssembler;
  @Mock private Initiative initiative;
  @Mock private MoneyAssembler moneyAssembler;

  private String NAME = createName();
  private InitiativeCode INITIATIVE_CODE = createCode();
  private Money ALLOCATED_MONEY = Money.fromDouble(createAmount());

  @Before
  public void setUp() {
    initiativeAssembler = new InitiativeAssembler(moneyAssembler);

    initiative =
        aInitiative()
            .withInitiativeName(NAME)
            .withInitiativeCode(INITIATIVE_CODE)
            .withAllocatedAmount(ALLOCATED_MONEY)
            .build();
  }

  @Test
  public void whenAssembling_thenReturnInitiativeDto() {
    InitiativeDto AssembledInitiativeDto = initiativeAssembler.assemble(initiative);

    Truth.assertThat(AssembledInitiativeDto.name).isEqualTo(initiative.getInitiativeName());
    Truth.assertThat(AssembledInitiativeDto.code)
        .isEqualTo(initiative.getInitiativeCode().toString());
    Truth.assertThat(AssembledInitiativeDto.allocatedAmount)
        .isEqualTo(initiative.getAllocatedAmount().toDouble());
  }
}
