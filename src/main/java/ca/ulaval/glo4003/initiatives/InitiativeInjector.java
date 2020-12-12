package ca.ulaval.glo4003.initiatives;

import ca.ulaval.glo4003.carboncredits.configuration.CarbonCreditConfiguration;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.generators.domain.StringCodeGenerator;
import ca.ulaval.glo4003.initiatives.api.InitiativeResource;
import ca.ulaval.glo4003.initiatives.domain.*;
import ca.ulaval.glo4003.initiatives.infrastructure.InitiativeRepositoryInMemory;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
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
      List<InitiativeAddedAllocatedAmountObserver> initiativeAddedAllocatedAmountObservers) {
    InitiativeFactory initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);

    InitiativeService initiativeService =
        new InitiativeService(initiativeFactory, initiativeRepository);

    initiativeAddedAllocatedAmountObservers.forEach(initiativeService::register);
    return initiativeService;
  }
}
