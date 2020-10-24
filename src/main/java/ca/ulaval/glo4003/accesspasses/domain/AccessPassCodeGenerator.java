package ca.ulaval.glo4003.accesspasses.domain;

import com.github.javafaker.Faker;

public class AccessPassCodeGenerator {
  private static final String KEYWORD = "PASS";
  private static final String SEPARATOR = "-";
  private static final String PATTERN = "[A-Z0-9]{6}";

  public AccessPassCode generate() {
    return new AccessPassCode(buildCode());
  }

  private String buildCode() {
    String generatedCode = Faker.instance().regexify(PATTERN);
    return KEYWORD.concat(SEPARATOR).concat(generatedCode);
  }
}
