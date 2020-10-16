package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;

public class OffenseCodeAssembler {
  public OffenseCode assemble(String code) {
    String upperCaseCode = code.toUpperCase();

    return new OffenseCode(upperCaseCode);
  }
}
