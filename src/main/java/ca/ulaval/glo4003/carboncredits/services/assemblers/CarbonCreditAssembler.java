package ca.ulaval.glo4003.carboncredits.services.assemblers;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.services.dto.CarbonCreditDto;

public class CarbonCreditAssembler {
  public CarbonCreditDto assemble(CarbonCredit carbonCredit) {
    CarbonCreditDto carbonCreditDto = new CarbonCreditDto();

    carbonCreditDto.carbonCredits = carbonCredit.toDouble();

    return carbonCreditDto;
  }
}
