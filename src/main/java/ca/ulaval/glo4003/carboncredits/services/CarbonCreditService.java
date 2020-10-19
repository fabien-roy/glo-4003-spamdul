package ca.ulaval.glo4003.carboncredits.services;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;

public class CarbonCreditService {
  private CarbonCreditRepository carbonCreditRepository;
  private CarbonCreditAssembler carbonCreditAssembler;

  public CarbonCreditService(
      CarbonCreditRepository carbonCreditRepository, CarbonCreditAssembler carbonCreditAssembler) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.carbonCreditAssembler = carbonCreditAssembler;
  }

  public CarbonCreditDto getCarbonCredits() {
    return carbonCreditAssembler.assemble(carbonCreditRepository.get());
  }
}
