package ca.ulaval.glo4003.accesspasses.domain;

import com.github.javafaker.Faker;

public class AccessPassCodeGenerator {
  private static final String CODE_KEYWORD = "PASS";
  private static final String SEPARATOR = "-";
  private static final String CODE_PATTERN = "[A-Z0-9]{6}";

  public AccessPassCode generate() {
    return new AccessPassCode(buildCode());
  }

  private String buildCode() {
    String generatedCode = Faker.instance().regexify(CODE_PATTERN);
    return CODE_KEYWORD.concat(SEPARATOR).concat(generatedCode);
  }
}
