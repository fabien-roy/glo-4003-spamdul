package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;

public class AccessPassCodeGenerator {
  private static final String KEYWORD = "PASS";
  private final StringCodeGenerator stringCodeGenerator;

  public AccessPassCodeGenerator(StringCodeGenerator stringCodeGenerator) {
    this.stringCodeGenerator = stringCodeGenerator;
  }

  public AccessPassCode generate() {
    String code = stringCodeGenerator.generate(KEYWORD);
    return new AccessPassCode(code);
  }
}
