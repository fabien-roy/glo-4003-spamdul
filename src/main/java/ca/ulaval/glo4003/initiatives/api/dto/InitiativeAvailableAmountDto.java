package ca.ulaval.glo4003.initiatives.api.dto;

public class InitiativeAvailableAmountDto {
  public double availableAmount;

  @Override
  public String toString() {
    return String.format("availableAmount{availableAmount='%s'}", availableAmount);
  }
}
