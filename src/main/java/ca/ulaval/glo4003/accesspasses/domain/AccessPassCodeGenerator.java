package ca.ulaval.glo4003.accesspasses.domain;

import com.github.javafaker.Faker;

public class AccessPassCodeGenerator {
  private static final String CODE_KEYWORD = "PASS";
  private static final String SEPARATOR = "-";
  private static final String CODE_PATTERN = "[A-Z0-9]{6}";

  public AccessPassCode generate() {
    String generatedCode = Faker.instance().regexify(CODE_PATTERN);
    String accessPassCode = CODE_KEYWORD.concat(SEPARATOR).concat(generatedCode);

    return new AccessPassCode(accessPassCode);
  }
}
