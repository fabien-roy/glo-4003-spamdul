package ca.ulaval.glo4003.initiative.api.dto;

public class InitiativeDto {
  public String initiativeCode;
  public String initiativeName;
  public Double allocatedAmount;

  @Override
  public String toString() {
    return String.format(
        "InitiativeDto{initiativeCode='%s', initiativeName='%s', allocatedAmount='%s'}",
        initiativeCode, initiativeName, allocatedAmount);
  }
}
