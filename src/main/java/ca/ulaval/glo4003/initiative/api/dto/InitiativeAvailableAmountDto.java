package ca.ulaval.glo4003.initiative.api.dto;

public class InitiativeAvailableAmountDto {
  public double availableAmount;

  @Override
  public String toString() {
    return String.format("availableAmount{availableAmount='%s'}", availableAmount);
  }
}
