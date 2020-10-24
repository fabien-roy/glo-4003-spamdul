package ca.ulaval.glo4003.offenses.filesystem.dto;

public class OffenseInFrenchDto {
  public String infraction;
  public String code;
  public int montant;

  @Override
  public String toString() {
    return String.format(
        "InfractionDto{infraction='%s', code='%s', montant='%s'}", infraction, code, montant);
  }
}