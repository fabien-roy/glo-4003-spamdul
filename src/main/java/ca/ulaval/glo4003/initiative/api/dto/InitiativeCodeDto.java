package ca.ulaval.glo4003.initiative.api.dto;

public class InitiativeCodeDto {
  public String initiativeCode;

  @Override
  public String toString() {
    return String.format("InitiativeCodeDto{initiativeCode='%s'}", initiativeCode);
  }
}
