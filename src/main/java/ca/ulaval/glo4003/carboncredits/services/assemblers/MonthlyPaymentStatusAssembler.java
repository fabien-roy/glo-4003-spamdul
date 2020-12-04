package ca.ulaval.glo4003.carboncredits.services.assemblers;

import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.services.dto.MonthlyPaymentStatusDto;

public class MonthlyPaymentStatusAssembler {
  public MonthlyPaymentStatus assemble(MonthlyPaymentStatusDto monthlyPaymentStatusDto) {
    return MonthlyPaymentStatus.get(monthlyPaymentStatusDto.monthlyPaymentStatus);
  }
}
