package ca.ulaval.glo4003.carboncredits.assemblers;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditMonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;

public class CarbonCreditMonthlyPaymentStatusAssembler {
  public MonthlyPaymentStatus assemble(
      CarbonCreditMonthlyPaymentStatusDto carbonCreditMonthlyPaymentStatusDto) {
    return MonthlyPaymentStatus.get(carbonCreditMonthlyPaymentStatusDto.monthlyPaymentStatus);
  }
}
