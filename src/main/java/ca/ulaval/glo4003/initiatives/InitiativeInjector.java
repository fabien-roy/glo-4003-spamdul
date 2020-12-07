package ca.ulaval.glo4003.initiatives;

import ca.ulaval.glo4003.carboncredits.configuration.CarbonCreditConfiguration;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.api.InitiativeResource;
import ca.ulaval.glo4003.initiatives.domain.*;
import ca.ulaval.glo4003.initiatives.infrastructure.InitiativeRepositoryInMemory;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;
import java.util.List;

public class InitiativeInjector {
  private final InitiativeRepository initiativeRepository = new InitiativeRepositoryInMemory();
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

  public InitiativeResource createInitiativeResource(InitiativeService initiativeService) {
    return new InitiativeResource(initiativeService);
  }

  public InitiativeService createService(
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository,
      List<InitiativeAddedAllocatedAmountObserver> initiativeAddedAllocatedAmountObservers) {
    InitiativeFactory initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);

    InitiativeService initiativeService =
        new InitiativeService(
            initiativeFactory, initiativeRepository, sustainableMobilityProgramBankRepository);

    initiativeAddedAllocatedAmountObservers.forEach(initiativeService::register);
    return initiativeService;
  }
}
