package ca.ulaval.glo4003.initiatives.services;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.domain.*;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeAddAllocatedAmountAssembler;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeAssembler;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiatives.services.dto.*;
import java.util.List;

public class InitiativeService extends InitiativeAddedAllocatedAmountObservable {
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
    Money amount = initiativeAddAllocatedAmountAssembler.assemble(initiativeAddAllocatedAmountDto);

    addAllocatedAmountToInitiative(code, amount);
  }

  public void addAllocatedAmountToInitiative(
      InitiativeCode initiativeCode, Money allocatedAmountToAdd) {
    Initiative initiative = initiativeRepository.get(initiativeCode);

    sustainableMobilityProgramBankRepository.remove(allocatedAmountToAdd);
    initiative.addAllocatedAmount(allocatedAmountToAdd);

    initiativeRepository.update(initiative);

    notifyInitiativeAddedAllocatedAmount(initiative, allocatedAmountToAdd);
  }
}
