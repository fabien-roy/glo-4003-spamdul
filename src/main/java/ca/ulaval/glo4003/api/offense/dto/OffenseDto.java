package ca.ulaval.glo4003.api.offense.dto;

public class OffenseDto {
  public String infraction;
  public String code;
  public double montant;

  @Override
  public String toString() {
    return String.format(
        "OffenseDto{reasonText='%s', reasonCode='%s', amount='%s'}", infraction, code, montant);
  }
}
