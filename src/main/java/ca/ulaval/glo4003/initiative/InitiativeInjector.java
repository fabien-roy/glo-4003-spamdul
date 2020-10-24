package ca.ulaval.glo4003.initiative;

import ca.ulaval.glo4003.accounts.domain.AccountIdGenerator;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.infrastructure.AccountRepositoryInMemory;
import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiative.api.InitiativeResource;
import ca.ulaval.glo4003.initiative.api.InitiativeResourceImplementation;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAddAllocatedAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiative.assembler.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiative.domain.InitiativeCodeGenerator;
import ca.ulaval.glo4003.initiative.domain.InitiativeFactory;
import ca.ulaval.glo4003.initiative.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiative.infrastructure.InitiativeRepositoryInMemory;
import ca.ulaval.glo4003.initiative.services.InitiativeService;

public class InitiativeInjector {

  private final AccountRepository accountRepository = new AccountRepositoryInMemory();
  private final AccountIdGenerator accountIdGenerator = new AccountIdGenerator();

  public InitiativeResource createInitiativeResource(InitiativeService initiativeService) {
    return new InitiativeResourceImplementation(initiativeService);
  }

  public InitiativeService createService(
      InitiativeFactory initiativeFactory,
      InitiativeRepository initiativeRepository,
      InitiativeCodeAssembler initiativeCodeAssembler,
      InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler,
      InitiativeAssembler initiativeAssembler,
      InitiativeAddAllocatedAmountAssembler initiativeAddAllocatedAmountAssembler,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    return new InitiativeService(
        initiativeFactory,
        initiativeRepository,
        initiativeCodeAssembler,
        initiativeAvailableAmountAssembler,
        initiativeAssembler,
        initiativeAddAllocatedAmountAssembler,
        sustainableMobilityProgramBankRepository);
  }

  public InitiativeFactory createInitiativeFactory(
      InitiativeCodeGenerator initiativeCodeGenerator) {
    return new InitiativeFactory(initiativeCodeGenerator);
  }

  public InitiativeRepository getInitiativeRepository() {
    return new InitiativeRepositoryInMemory();
  }

  public InitiativeCodeAssembler createInitiativeCodeAssembler() {
    return new InitiativeCodeAssembler();
  }

  public InitiativeAssembler createInitiativeAssembler(MoneyAssembler moneyAssembler) {
    return new InitiativeAssembler(moneyAssembler);
  }

  public InitiativeAddAllocatedAmountAssembler createInitiativeAddAllocatedAmountAssembler(
      MoneyAssembler moneyAssembler) {
    return new InitiativeAddAllocatedAmountAssembler(moneyAssembler);
  }

  public InitiativeAvailableAmountAssembler createInitiativeAvailableAmountAssembler() {
    return new InitiativeAvailableAmountAssembler();
  }

  public InitiativeCodeGenerator getInitiativeCodeGenerator() {
    return new InitiativeCodeGenerator();
  }
}
