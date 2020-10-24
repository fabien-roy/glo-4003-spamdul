package ca.ulaval.glo4003.initiatives.assembler;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.initiatives.helpers.AddInitiativeDtoBuilder.anAddInitiativeDto;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeBuilder.anInitiative;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiatives.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeAssemblerTest {
  @Mock private MoneyAssembler moneyAssembler;

  private InitiativeAssembler initiativeAssembler;

  private final Initiative initiative = anInitiative().build();
  private final AddInitiativeDto addInitiativeDto = anAddInitiativeDto().build();
  private final Money allocatedAmount = createMoney();

  @Before
  public void setUp() {
    initiativeAssembler = new InitiativeAssembler(moneyAssembler);

    when(moneyAssembler.assemble(addInitiativeDto.amount)).thenReturn(allocatedAmount);
  }

  @Test
  public void whenAssembling_thenReturnInitiativeWithName() {
    Initiative initiative = initiativeAssembler.assemble(addInitiativeDto);

    assertThat(initiative.getName()).isEqualTo(addInitiativeDto.name);
  }

  @Test
  public void whenAssembling_thenReturnInitiativeWithAmount() {
    Initiative initiative = initiativeAssembler.assemble(addInitiativeDto);

    assertThat(initiative.getAllocatedAmount()).isSameInstanceAs(allocatedAmount);
  }

  @Test
  public void whenAssemblingDto_thenReturnInitiativeDtoWithCode() {
    InitiativeDto initiativeDto = initiativeAssembler.assemble(initiative);

    assertThat(initiativeDto.code).isEqualTo(initiative.getCode().toString());
  }

  @Test
  public void whenAssemblingDto_thenReturnInitiativeDtoWithName() {
    InitiativeDto initiativeDto = initiativeAssembler.assemble(initiative);

    assertThat(initiativeDto.name).isEqualTo(initiative.getName());
  }

  @Test
  public void whenAssemblingDto_thenReturnInitiativeDtoWithAllocatedAmount() {
    InitiativeDto initiativeDto = initiativeAssembler.assemble(initiative);

    assertThat(initiativeDto.allocatedAmount).isEqualTo(initiative.getAllocatedAmount().toDouble());
  }

  @Test
  public void whenAssemblingManyDtos_thenReturnInitiativeDtos() {
    int numberOfDtos = 2;

    List<InitiativeDto> initiativeDtos =
        initiativeAssembler.assembleMany(Collections.nCopies(2, initiative));

    assertThat(initiativeDtos).hasSize(numberOfDtos);
  }
}
