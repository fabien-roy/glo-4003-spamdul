package ca.ulaval.glo4003.api.offense.dto;

public class OffenseFileDTO {
  public String infraction;
  public String code;
  public int montant;

  @Override
  public String toString() {
    return String.format(
        "OffenseFileDto{infraction='%s', code='%s', montant='%s'}", infraction, code, montant);
  }
}
