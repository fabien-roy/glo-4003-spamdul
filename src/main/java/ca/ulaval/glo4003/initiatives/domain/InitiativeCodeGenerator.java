package ca.ulaval.glo4003.initiatives.domain;

import com.github.javafaker.Faker;

public class InitiativeCodeGenerator {
  private static final String KEYWORD = "INIT";
  private static final String SEPARATOR = "-";
  private static final String PATTERN = "[A-Z0-9]{6}";

  public InitiativeCode generate() {
    return new InitiativeCode(buildCode());
  }

  private String buildCode() {
    String generatedCode = Faker.instance().regexify(PATTERN);
    return KEYWORD.concat(SEPARATOR).concat(generatedCode);
  }
}
