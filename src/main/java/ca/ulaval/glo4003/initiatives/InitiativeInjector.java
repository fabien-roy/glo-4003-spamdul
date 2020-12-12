package ca.ulaval.glo4003.initiatives;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditConfiguration;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.generators.domain.StringCodeGenerator;
import ca.ulaval.glo4003.initiatives.api.InitiativeResource;
import ca.ulaval.glo4003.initiatives.domain.*;
import ca.ulaval.glo4003.initiatives.infrastructure.InitiativeRepositoryInMemory;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeAssembler;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeAvailableAmountAssembler;
import ca.ulaval.glo4003.initiatives.services.assemblers.InitiativeCodeAssembler;
import ca.ulaval.glo4003.initiatives.services.converters.InitiativeAddAllocatedAmountConverter;
import java.util.List;

public class InitiativeInjector {
  private final InitiativeRepositoryInMemory initiativeRepositoryInMemory =
      new InitiativeRepositoryInMemory();
  private final InitiativeRepository initiativeRepository = initiativeRepositoryInMemory;
  private final InitiativeFundCollector initiativeFundCollector = initiativeRepositoryInMemory;
  private final InitiativeCodeGenerator initiativeCodeGenerator =
      new InitiativeCodeGenerator(new StringCodeGenerator());

  public InitiativeInjector() {
    CarbonCreditConfiguration carbonCreditConfiguration =
        CarbonCreditConfiguration.getConfiguration();

    Initiative carbonCreditInitiative =
        new Initiative(carbonCreditConfiguration.getCarbonCreditInitiativeName(), Money.zero());
    carbonCreditInitiative.setCode(carbonCreditConfiguration.getCarbonCreditInitiativeCode());
    initiativeRepository.save(carbonCreditInitiative);
  }

  public InitiativeFundCollector getInitiativeFundCollector() {
    return initiativeFundCollector;
  }

  public InitiativeRepository getInitiativeRepository() {
    return initiativeRepository;
  }

  public InitiativeResource createInitiativeResource(InitiativeService initiativeService) {
    return new InitiativeResource(initiativeService);
  }

  public InitiativeService createService(
      MoneyConverter moneyConverter,
      List<InitiativeAddedAllocatedAmountObserver> initiativeAddedAllocatedAmountObservers) {
    InitiativeFactory initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);
    InitiativeCodeAssembler initiativeCodeAssembler = new InitiativeCodeAssembler();
    InitiativeAvailableAmountAssembler initiativeAvailableAmountAssembler =
        new InitiativeAvailableAmountAssembler();
    InitiativeAssembler initiativeAssembler = new InitiativeAssembler(moneyConverter);
    InitiativeAddAllocatedAmountConverter initiativeAddAllocatedAmountConverter =
        new InitiativeAddAllocatedAmountConverter(moneyConverter);

    InitiativeService initiativeService =
        new InitiativeService(
            initiativeFactory,
            initiativeRepository,
            initiativeCodeAssembler,
            initiativeAvailableAmountAssembler,
            initiativeAssembler,
            initiativeAddAllocatedAmountConverter);

    initiativeAddedAllocatedAmountObservers.forEach(initiativeService::register);
    return initiativeService;
  }
}
