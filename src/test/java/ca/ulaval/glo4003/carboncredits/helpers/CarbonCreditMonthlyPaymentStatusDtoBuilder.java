package ca.ulaval.glo4003.carboncredits.helpers;

import static ca.ulaval.glo4003.carboncredits.helpers.MonthlyPaymentStatusMother.createMonthlyPaymentStatus;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditMonthlyPaymentStatusDto;

public class CarbonCreditMonthlyPaymentStatusDtoBuilder {
  private String monthlyPaymentStatus = createMonthlyPaymentStatus().toString();

  private CarbonCreditMonthlyPaymentStatusDtoBuilder() {}

  public static CarbonCreditMonthlyPaymentStatusDtoBuilder aCarbonCreditMonthlyPaymentStatusDto() {
    return new CarbonCreditMonthlyPaymentStatusDtoBuilder();
  }

  public CarbonCreditMonthlyPaymentStatusDto build() {
    CarbonCreditMonthlyPaymentStatusDto carbonCreditMonthlyPaymentStatusDto =
        new CarbonCreditMonthlyPaymentStatusDto();
    carbonCreditMonthlyPaymentStatusDto.monthlyPaymentStatus = monthlyPaymentStatus;
    return carbonCreditMonthlyPaymentStatusDto;
  }
}
