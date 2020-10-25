package ca.ulaval.glo4003.initiatives;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.api.InitiativeResource;
import ca.ulaval.glo4003.initiatives.api.InitiativeResourceImplementation;
import ca.ulaval.glo4003.initiatives.assembler.InitiativeAddAllocatedAmountAssembler;
import ca.ulaval.glo4003.initiatives.assembler.InitiativeAssembler;
import ca.ulaval.glo4003.initiatives.assembler.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiatives.assembler.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCodeGenerator;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFactory;
import ca.ulaval.glo4003.initiatives.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiatives.infrastructure.InitiativeRepositoryInMemory;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;

public class InitiativeInjector {
  private final InitiativeRepository initiativeRepository = new InitiativeRepositoryInMemory();
  private final InitiativeCodeGenerator initiativeCodeGenerator =
      new InitiativeCodeGenerator(new StringCodeGenerator());

  public InitiativeResource createInitiativeResource(InitiativeService initiativeService) {
    return new InitiativeResourceImplementation(initiativeService);
  }

  public InitiativeService createService(
      MoneyAssembler moneyAssembler,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    InitiativeFactory initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);
    InitiativeCodeAssembler initiativeCodeAssembler = new InitiativeCodeAssembler();
    InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler =
        new InitiativeAvailableAmountAssembler();
    InitiativeAssembler initiativeAssembler = new InitiativeAssembler(moneyAssembler);
    InitiativeAddAllocatedAmountAssembler initiativeAddAllocatedAmountAssembler =
        new InitiativeAddAllocatedAmountAssembler(moneyAssembler);

    return new InitiativeService(
        initiativeFactory,
        initiativeRepository,
        initiativeCodeAssembler,
        initiativeAvailableAmountAssembler,
        initiativeAssembler,
        initiativeAddAllocatedAmountAssembler,
        sustainableMobilityProgramBankRepository);
  }
}
