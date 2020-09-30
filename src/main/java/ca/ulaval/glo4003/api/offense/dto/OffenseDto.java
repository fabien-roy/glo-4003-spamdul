package ca.ulaval.glo4003.api.offense.dto;

public class OffenseDto {
  public String description;
  public String code;
  public double amount;

  @Override
  public String toString() {
    return String.format(
        "OffenseDto{description='%s', code='%s', amount='%s'}", description, code, amount);
  }
}
