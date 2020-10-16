package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResourceImplementation;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.insfrastructure.CarbonCreditRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;

public class CarbonCreditInjector {
  private final CarbonCreditRepository carbonCreditRepository =
      new CarbonCreditRepositoryInMemory();

  public CarbonCreditResource createCarbonCreditResource() {
    CarbonCreditAssembler carbonCreditAssembler = new CarbonCreditAssembler();
    CarbonCreditService carbonCreditService =
        new CarbonCreditService(carbonCreditRepository, carbonCreditAssembler);

    return new CarbonCreditResourceImplementation(carbonCreditService);
  }
}
