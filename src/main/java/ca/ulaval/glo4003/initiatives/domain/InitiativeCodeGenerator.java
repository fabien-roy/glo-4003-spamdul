package ca.ulaval.glo4003.initiatives.domain;

import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;

public class InitiativeCodeGenerator {
  private static final String KEYWORD = "INIT";
  private final StringCodeGenerator stringCodeGenerator;

  public InitiativeCodeGenerator(StringCodeGenerator stringCodeGenerator) {
    this.stringCodeGenerator = stringCodeGenerator;
  }

  public InitiativeCode generate() {
    String code = stringCodeGenerator.buildCode(KEYWORD);
    return new InitiativeCode(code);
  }
}
