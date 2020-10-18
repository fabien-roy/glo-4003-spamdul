package ca.ulaval.glo4003.carboncredits.assemblers;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;

public class CarbonCreditAssembler {
  public CarbonCreditDto assemble(CarbonCredit carbonCredit) {
    CarbonCreditDto carbonCreditDto = new CarbonCreditDto();

    carbonCreditDto.carbonCredits = carbonCredit.toDouble();

    return carbonCreditDto;
  }
}
