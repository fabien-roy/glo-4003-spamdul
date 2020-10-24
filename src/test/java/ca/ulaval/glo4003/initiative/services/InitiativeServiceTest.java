package ca.ulaval.glo4003.initiative.services;

import static ca.ulaval.glo4003.initiative.helpers.AddInitiativeDtoBuilder.anAddInitiativeDto;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeAddAllocatedAmountDtoBuilder.aInitiativeAddAllocatedAmountDTO;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeAvailableAmountDtoBuilder.aInitiativeAvailableAmountDto;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeBuilder.anInitiative;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeCodeDtoBuilder.aInitiativeCodeDto;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeDtoBuilder.anInitiativeDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiative.api.dto.*;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAddAllocatedAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeFactory;
import ca.ulaval.glo4003.initiative.domain.InitiativeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeServiceTest {
  @Mock private InitiativeFactory initiativeFactory;
  @Mock private InitiativeRepository initiativeRepository;
  @Mock private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;
  @Mock private InitiativeCodeAssembler initiativeCodeAssembler;
  @Mock private InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler;
  @Mock private InitiativeAssembler initiativeAssembler;
  @Mock private InitiativeAddAllocatedAmountAssembler initiativeAddAllocatedAmountAssembler;

  private InitiativeService initiativeService;

  private final AddInitiativeDto addInitiativeDto = anAddInitiativeDto().build();
  private final Initiative initiative = anInitiative().withAllocatedAmount(Money.zero()).build();
  private final InitiativeCodeDto initiativeCodeDto =
      aInitiativeCodeDto().withCode(initiative.getCode().toString()).build();
  private final InitiativeAvailableAmountDto initiativeAvailableAmountDto =
      aInitiativeAvailableAmountDto().build();
  private final InitiativeDto initiativeDto = anInitiativeDto().build();
  private final InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
      aInitiativeAddAllocatedAmountDTO().build();
  private final Money availableAmount =
      Money.fromDouble(initiativeAvailableAmountDto.availableAmount);

  @Before
  public void setUp() {
    initiativeService =
        new InitiativeService(
            initiativeFactory,
            initiativeRepository,
            initiativeCodeAssembler,
            initiativeAvailableAmountAssembler,
            initiativeAssembler,
            initiativeAddAllocatedAmountAssembler,
            sustainableMobilityProgramBankRepository);
    when(initiativeFactory.create(initiative)).thenReturn(initiative);
    when(initiativeAssembler.assemble(addInitiativeDto)).thenReturn(initiative);
    when(initiativeAddAllocatedAmountAssembler.assemble(initiativeAddAllocatedAmountDto))
        .thenReturn(Money.fromDouble(initiativeAddAllocatedAmountDto.amountToAdd));
    when(initiativeCodeAssembler.assemble(initiative.getCode().toString()))
        .thenReturn(initiative.getCode());
  }

  @Test
  public void whenAddingInitiative_initiativeFactoryCreateInitiative() {
    initiativeService.addInitiative(addInitiativeDto);

    verify(initiativeFactory).create(initiative);
  }

  @Test
  public void whenAddingInitiative_thenAmountIsRemovedFromSustainableMobilityProgramBank() {
    initiativeService.addInitiative(addInitiativeDto);

    verify(sustainableMobilityProgramBankRepository).remove(initiative.getAllocatedAmount());
  }

  @Test
  public void whenAddingInitiative_initiativeIsSavedToRepository() {
    initiativeService.addInitiative(addInitiativeDto);

    verify(initiativeRepository).save(initiative);
  }

  @Test
  public void whenAddingInitiative_returnCreatedInitiativeCode() {
    when(initiativeCodeAssembler.assemble(initiative.getCode())).thenReturn(initiativeCodeDto);
    when(initiativeRepository.save(initiative)).thenReturn(initiative.getCode());

    InitiativeCodeDto initiativeCodeDto = initiativeService.addInitiative(addInitiativeDto);

    assertThat(initiativeCodeDto.initiativeCode).isEqualTo(initiative.getCode().toString());
  }

  @Test
  public void whenGettingInitiative_thenAssembleInitiativeCode() {
    initiativeService.getInitiative(initiative.getCode().toString());

    verify(initiativeCodeAssembler).assemble(initiative.getCode().toString());
  }

  @Test
  public void whenGettingInitiative_thenGetInitiativeFromRepository() {
    when(initiativeCodeAssembler.assemble(initiative.getCode().toString()))
        .thenReturn(initiative.getCode());

    initiativeService.getInitiative(initiative.getCode().toString());

    verify(initiativeRepository).get(initiative.getCode());
  }

  @Test
  public void whenGettingInitiative_thenReturnInitiative() {
    when(initiativeAssembler.assemble(initiative)).thenReturn(initiativeDto);
    when(initiativeRepository.get(initiative.getCode())).thenReturn(initiative);

    InitiativeDto returnedInitiativeDto =
        initiativeService.getInitiative(initiative.getCode().toString());

    assertThat(returnedInitiativeDto.code).isEqualTo(initiativeDto.code);
    assertThat(returnedInitiativeDto.name).isEqualTo(initiativeDto.name);
    assertThat(returnedInitiativeDto.allocatedAmount).isEqualTo(initiativeDto.allocatedAmount);
  }

  @Test
  public void whenGettingAllInitiatives_thenGetInitiativesFromRepository() {
    initiativeService.getAllInitiatives();

    verify(initiativeRepository).getAll();
  }

  @Test
  public void
      whenAddingAllocatedAmountToInitiative_thenAmountIsRemovedFromSustainableMobilityProgramBank() {
    when(initiativeRepository.get(initiative.getCode())).thenReturn(initiative);

    initiativeService.addAllocatedAmountToInitiative(
        initiative.getCode().toString(), initiativeAddAllocatedAmountDto);

    verify(sustainableMobilityProgramBankRepository)
        .remove(Money.fromDouble(initiativeAddAllocatedAmountDto.amountToAdd));
  }

  @Test
  public void whenAddingAllocatedAmountToInitiative_thenAddAmountToInitiative() {
    when(initiativeRepository.get(initiative.getCode())).thenReturn(initiative);

    initiativeService.addAllocatedAmountToInitiative(
        initiative.getCode().toString(), initiativeAddAllocatedAmountDto);

    assertThat(initiative.getAllocatedAmount())
        .isEqualTo(new Money(initiativeAddAllocatedAmountDto.amountToAdd));
  }

  @Test
  public void whenAddingAllocatedAmountToInitiative_thenUpdateRepositoryWithInitiative() {
    when(initiativeRepository.get(initiative.getCode())).thenReturn(initiative);

    initiativeService.addAllocatedAmountToInitiative(
        initiative.getCode().toString(), initiativeAddAllocatedAmountDto);

    verify(initiativeRepository).update(initiative);
  }

  @Test
  public void whenGettingAvailableAmount_thenReturnAvailableAmount() {
    when(sustainableMobilityProgramBankRepository.get()).thenReturn(availableAmount);
    when(initiativeAvailableAmountAssembler.assemble(availableAmount))
        .thenReturn(initiativeAvailableAmountDto);

    InitiativeAvailableAmountDto returnedInitiativeAvailableAmountDto =
        initiativeService.getAvailableAmount();

    assertThat(returnedInitiativeAvailableAmountDto.availableAmount)
        .isEqualTo(availableAmount.toDouble());
  }
}
