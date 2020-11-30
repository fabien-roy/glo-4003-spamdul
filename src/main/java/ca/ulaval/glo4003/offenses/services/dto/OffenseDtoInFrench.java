package ca.ulaval.glo4003.offenses.services.dto;

public class OffenseDtoInFrench {
  public String infraction;
  public String code;
  public int montant;

  @Override
  public String toString() {
    return String.format(
        "InfractionDto{infraction='%s', code='%s', montant='%s'}", infraction, code, montant);
  }
}
