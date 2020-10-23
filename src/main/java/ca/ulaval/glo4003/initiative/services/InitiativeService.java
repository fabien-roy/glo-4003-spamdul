package ca.ulaval.glo4003.initiative.services;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiative.api.dto.*;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;
import ca.ulaval.glo4003.initiative.domain.InitiativeFactory;
import ca.ulaval.glo4003.initiative.domain.InitiativeRepository;
import java.util.List;

public class InitiativeService {
  private InitiativeFactory initiativeFactory;
  private InitiativeRepository initiativeRepository;
  private InitiativeCodeAssembler initiativeCodeAssembler;
  private InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler;
  private InitiativeAssembler initiativeAssembler;
  private MoneyAssembler moneyAssembler;
  private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  public InitiativeService(
      InitiativeFactory initiativeFactory,
      InitiativeRepository initiativeRepository,
      InitiativeCodeAssembler initiativeCodeAssembler,
      InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler,
      InitiativeAssembler initiativeAssembler,
      MoneyAssembler moneyAssembler,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {

    this.initiativeFactory = initiativeFactory;
    this.initiativeRepository = initiativeRepository;
    this.initiativeCodeAssembler = initiativeCodeAssembler;
    this.initiativeAvailableAmountAssembler = initiativeAvailableAmountAssembler;
    this.initiativeAssembler = initiativeAssembler;
    this.moneyAssembler = moneyAssembler;
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
  }

  public InitiativeCodeDto addInitiative(AddInitiativeDto addInitiativeDto) {
    Initiative initiative = initiativeFactory.createInitiative(addInitiativeDto.name);
    Money money = moneyAssembler.assemble(addInitiativeDto.amount);

    sustainableMobilityProgramBankRepository.remove(money);
    initiative.addAllocatedAmount(money);

    InitiativeCode initiativeCode = initiativeRepository.save(initiative);
    return initiativeCodeAssembler.assemble(initiativeCode);
  }

  public InitiativeDto getInitiative(String initiativeCode) {
    InitiativeCode code = initiativeCodeAssembler.assemble(initiativeCode);
    Initiative initiative = initiativeRepository.getInitiative(code);

    return initiativeAssembler.assemble(initiative);
  }

  public List<InitiativeDto> getAllInitiatives() {
    List<Initiative> initiatives = initiativeRepository.getAllInitiatives();

    return initiativeAssembler.assemble(initiatives);
  }

  public InitiativeAvailableAmountDto getAvailableAmount() {
    Money availableAmount = sustainableMobilityProgramBankRepository.get();

    return initiativeAvailableAmountAssembler.assemble(availableAmount);
  }

  public void AddAllocatedAmountToInitiative(
      String initiativeCode, InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDTO) {
    InitiativeCode code = initiativeCodeAssembler.assemble(initiativeCode);
    Money money = moneyAssembler.assemble(initiativeAddAllocatedAmountDTO.amountToAdd);

    Initiative initiative = initiativeRepository.getInitiative(code);

    sustainableMobilityProgramBankRepository.remove(money);
    initiative.addAllocatedAmount(money);

    initiativeRepository.updateInitiative(initiative);
  }
}
