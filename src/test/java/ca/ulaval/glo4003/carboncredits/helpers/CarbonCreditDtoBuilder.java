package ca.ulaval.glo4003.carboncredits.helpers;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditMother.createCarbonCredit;

import ca.ulaval.glo4003.carboncredits.services.dto.CarbonCreditDto;

public class CarbonCreditDtoBuilder {
  private double carbonCreditAmount = createCarbonCredit().toDouble();

  private CarbonCreditDtoBuilder() {}

  public static CarbonCreditDtoBuilder aCarbonCreditDto() {
    return new CarbonCreditDtoBuilder();
  }

  public CarbonCreditDto build() {
    CarbonCreditDto carbonCreditDto = new CarbonCreditDto();
    carbonCreditDto.carbonCredits = carbonCreditAmount;

    return carbonCreditDto;
  }
}
