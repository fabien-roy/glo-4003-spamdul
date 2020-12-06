package ca.ulaval.glo4003.generators.domain;

import com.github.javafaker.Faker;

public class StringCodeGenerator {
  private static final String SEPARATOR = "-";
  private static final String PATTERN = "[A-Z0-9]{6}";

  public String generate(String keyword) {
    String generatedCode = Faker.instance().regexify(PATTERN);
    return keyword.concat(SEPARATOR).concat(generatedCode);
  }
}
