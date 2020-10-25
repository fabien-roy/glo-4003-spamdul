package ca.ulaval.glo4003.carboncredits.assemblers;

import ca.ulaval.glo4003.carboncredits.api.dto.MonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;

public class MonthlyPaymentStatusAssembler {
  public MonthlyPaymentStatus assemble(MonthlyPaymentStatusDto monthlyPaymentStatusDto) {
    return MonthlyPaymentStatus.get(monthlyPaymentStatusDto.monthlyPaymentStatus);
  }
}
