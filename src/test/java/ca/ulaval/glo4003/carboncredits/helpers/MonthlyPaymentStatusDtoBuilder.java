package ca.ulaval.glo4003.carboncredits.helpers;

import static ca.ulaval.glo4003.carboncredits.helpers.MonthlyPaymentStatusMother.createMonthlyPaymentStatus;

import ca.ulaval.glo4003.carboncredits.services.dto.MonthlyPaymentStatusDto;

public class MonthlyPaymentStatusDtoBuilder {
  private String monthlyPaymentStatus = createMonthlyPaymentStatus().toString();

  public static MonthlyPaymentStatusDtoBuilder aMonthlyPaymentStatusDto() {
    return new MonthlyPaymentStatusDtoBuilder();
  }

  public MonthlyPaymentStatusDto build() {
    MonthlyPaymentStatusDto monthlyPaymentStatusDto = new MonthlyPaymentStatusDto();
    monthlyPaymentStatusDto.monthlyPaymentStatus = monthlyPaymentStatus;
    return monthlyPaymentStatusDto;
  }
}
