package ca.ulaval.glo4003.initiative.services;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiative.api.dto.*;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAddAllocatedAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;
import ca.ulaval.glo4003.initiative.domain.InitiativeFactory;
import ca.ulaval.glo4003.initiative.domain.InitiativeRepository;
import java.util.List;

public class InitiativeService {
  private final InitiativeFactory initiativeFactory;
  private final InitiativeRepository initiativeRepository;
  private final InitiativeCodeAssembler initiativeCodeAssembler;
  private final InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler;
  private final InitiativeAssembler initiativeAssembler;
  private final InitiativeAddAllocatedAmountAssembler initiativeAddAllocatedAmountAssembler;
  private final SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  public InitiativeService(
      InitiativeFactory initiativeFactory,
      InitiativeRepository initiativeRepository,
      InitiativeCodeAssembler initiativeCodeAssembler,
      InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler,
      InitiativeAssembler initiativeAssembler,
      InitiativeAddAllocatedAmountAssembler initiativeAddAllocatedAmountAssembler,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    this.initiativeFactory = initiativeFactory;
    this.initiativeRepository = initiativeRepository;
    this.initiativeCodeAssembler = initiativeCodeAssembler;
    this.initiativeAvailableAmountAssembler = initiativeAvailableAmountAssembler;
    this.initiativeAssembler = initiativeAssembler;
    this.initiativeAddAllocatedAmountAssembler = initiativeAddAllocatedAmountAssembler;
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
  }

  public InitiativeCodeDto addInitiative(AddInitiativeDto addInitiativeDto) {
    Initiative initiative = initiativeAssembler.assemble(addInitiativeDto);

    initiative = initiativeFactory.create(initiative);

    sustainableMobilityProgramBankRepository.remove(initiative.getAllocatedAmount());

    InitiativeCode initiativeCode = initiativeRepository.save(initiative);
    return initiativeCodeAssembler.assemble(initiativeCode);
  }

  public InitiativeDto getInitiative(String initiativeCode) {
    InitiativeCode code = initiativeCodeAssembler.assemble(initiativeCode);
    Initiative initiative = initiativeRepository.get(code);

    return initiativeAssembler.assemble(initiative);
  }

  public List<InitiativeDto> getAllInitiatives() {
    List<Initiative> initiatives = initiativeRepository.getAll();

    return initiativeAssembler.assembleMany(initiatives);
  }

  public InitiativeAvailableAmountDto getAvailableAmount() {
    Money availableAmount = sustainableMobilityProgramBankRepository.get();

    return initiativeAvailableAmountAssembler.assemble(availableAmount);
  }

  public void addAllocatedAmountToInitiative(
      String initiativeCode, InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto) {
    InitiativeCode code = initiativeCodeAssembler.assemble(initiativeCode);
    Money money = initiativeAddAllocatedAmountAssembler.assemble(initiativeAddAllocatedAmountDto);

    Initiative initiative = initiativeRepository.get(code);

    sustainableMobilityProgramBankRepository.remove(money);
    initiative.addAllocatedAmount(money);

    initiativeRepository.update(initiative);
  }
}
