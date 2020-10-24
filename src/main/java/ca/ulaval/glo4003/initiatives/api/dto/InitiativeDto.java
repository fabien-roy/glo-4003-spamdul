package ca.ulaval.glo4003.initiatives.api.dto;

public class InitiativeDto {
  public String code;
  public String name;
  public double allocatedAmount;

  @Override
  public String toString() {
    return String.format(
        "InitiativeDto{code='%s', name='%s', allocatedAmount='%s'}", code, name, allocatedAmount);
  }
}
