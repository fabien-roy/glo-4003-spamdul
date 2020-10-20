package ca.ulaval.glo4003.initiative.services;

import static ca.ulaval.glo4003.initiative.helpers.AddInitiativeDtoBuilder.aAddInitiativeDto;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeAddAllocatedAmountDtoBuilder.aInitiativeAddAllocatedAmountDTO;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeBuilder.aInitiative;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeCodeDtoBuilder.aInitiativeCodeDto;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeDtoBuilder.aInitiativeDto;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAddAllocatedAmountDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeFactory;
import ca.ulaval.glo4003.initiative.domain.InitiativeRepository;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeServiceTest {
  private InitiativeService initiativeService;

  @Mock private InitiativeFactory initiativeFactory;
  @Mock private InitiativeRepository initiativeRepository;
  @Mock private InitiativeCodeAssembler initiativeCodeAssembler;
  @Mock private InitiativeAssembler initiativeAssembler;
  @Mock private MoneyAssembler moneyAssembler;
  private AddInitiativeDto addInitiativeDto = aAddInitiativeDto().build();
  private Initiative initiative = aInitiative().withAllocatedAmount(Money.ZERO()).build();
  private InitiativeCodeDto initiativeCodeDto =
      aInitiativeCodeDto().withCode(initiative.getInitiativeCode().toString()).build();
  private InitiativeDto initiativeDto = aInitiativeDto().build();
  private InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
      aInitiativeAddAllocatedAmountDTO().build();

  @Before
  public void setUp() {
    initiativeService =
        new InitiativeService(
            initiativeFactory,
            initiativeRepository,
            initiativeCodeAssembler,
            initiativeAssembler,
            moneyAssembler);
    when(initiativeFactory.createInitiative(addInitiativeDto.name)).thenReturn(initiative);
    when(moneyAssembler.assemble(addInitiativeDto.amount))
        .thenReturn(Money.fromDouble(addInitiativeDto.amount));
    when(moneyAssembler.assemble(initiativeAddAllocatedAmountDto.amountToAdd))
        .thenReturn(Money.fromDouble(initiativeAddAllocatedAmountDto.amountToAdd));
    when(initiativeCodeAssembler.assemble(initiative.getInitiativeCode().toString()))
        .thenReturn(initiative.getInitiativeCode());
  }

  @Test
  public void whenAddingInitiative_initiativeFactoryCreateInitiative() {
    initiativeService.addInitiative(addInitiativeDto);

    verify(initiativeFactory).createInitiative(addInitiativeDto.name);
  }

  @Test
  public void whenAddingInitiative_amountIsRemovedFromPiggyBank() {
    // TODO
  }

  @Test
  public void whenAddingInitiative_amountIsAddedToInitiative() {
    initiativeService.addInitiative(addInitiativeDto);

    Truth.assertThat(initiative.getAllocatedAmount()).isEqualTo(new Money(addInitiativeDto.amount));
  }

  @Test
  public void whenAddingInitiative_initiativeIsSavedToRepository() {
    initiativeService.addInitiative(addInitiativeDto);

    verify(initiativeRepository).save(initiative);
  }

  @Test
  public void whenAddingInitiative_returnCreatedInitiativeCode() {
    when(initiativeCodeAssembler.assemble(initiative.getInitiativeCode()))
        .thenReturn(initiativeCodeDto);
    when(initiativeRepository.save(initiative)).thenReturn(initiative.getInitiativeCode());

    InitiativeCodeDto initiativeCodeDto = initiativeService.addInitiative(addInitiativeDto);

    Truth.assertThat(initiativeCodeDto.initiativeCode)
        .isEqualTo(initiative.getInitiativeCode().toString());
  }

  @Test
  public void whenGettingInitiative_thenAssembleInitiativeCode() {
    initiativeService.getInitiative(initiative.getInitiativeCode().toString());

    verify(initiativeCodeAssembler).assemble(initiative.getInitiativeCode().toString());
  }

  @Test
  public void whenGettingInitiative_thenGetInitiativeFromRepository() {
    when(initiativeCodeAssembler.assemble(initiative.getInitiativeCode().toString()))
        .thenReturn(initiative.getInitiativeCode());

    initiativeService.getInitiative(initiative.getInitiativeCode().toString());

    verify(initiativeRepository).getInitiative(initiative.getInitiativeCode());
  }

  @Test
  public void whenGettingInitiative_thenReturnInitiative() {
    when(initiativeAssembler.assemble(initiative)).thenReturn(initiativeDto);
    when(initiativeRepository.getInitiative(initiative.getInitiativeCode())).thenReturn(initiative);

    InitiativeDto returnedInitiativeDto =
        initiativeService.getInitiative(initiative.getInitiativeCode().toString());

    Truth.assertThat(returnedInitiativeDto.initiativeCode).isEqualTo(initiativeDto.initiativeCode);
    Truth.assertThat(returnedInitiativeDto.initiativeName).isEqualTo(initiativeDto.initiativeName);
    Truth.assertThat(returnedInitiativeDto.allocatedAmount)
        .isEqualTo(initiativeDto.allocatedAmount);
  }

  @Test
  public void whenGettingAllInitiatives_thenGetInitiativesFromRepository() {
    initiativeService.getAllInitiatives();

    verify(initiativeRepository).getAllInitiatives();
  }

  @Test
  public void whenAddingAllocatedAmountToInitiative_thenAddAmountToInitiative() {
    when(initiativeRepository.getInitiative(initiative.getInitiativeCode())).thenReturn(initiative);

    initiativeService.AddAllocatedAmountToInitiative(
        initiative.getInitiativeCode().toString(), initiativeAddAllocatedAmountDto);

    Truth.assertThat(initiative.getAllocatedAmount())
        .isEqualTo(new Money(initiativeAddAllocatedAmountDto.amountToAdd));
  }

  @Test
  public void whenAddingAllocatedAmountToInitiative_thenUpdateRepositoryWithInitiative() {
    when(initiativeRepository.getInitiative(initiative.getInitiativeCode())).thenReturn(initiative);

    initiativeService.AddAllocatedAmountToInitiative(
        initiative.getInitiativeCode().toString(), initiativeAddAllocatedAmountDto);

    verify(initiativeRepository).updateInitiative(initiative);
  }
}
