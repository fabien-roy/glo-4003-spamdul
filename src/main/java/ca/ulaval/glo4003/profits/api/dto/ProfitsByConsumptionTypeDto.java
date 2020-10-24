package ca.ulaval.glo4003.profits.api.dto;

public class ProfitsByConsumptionTypeDto {
  public String consumptionType;
  public double profits;

  @Override
  public String toString() {
    return String.format(
        "ProfitsByConsumptionTypeDto{consumptionType='%s', profits='%.2f'}",
        consumptionType, profits);
  }
}
