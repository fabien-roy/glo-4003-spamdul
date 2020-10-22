package ca.ulaval.glo4003.parkings.domain;

import com.github.javafaker.Faker;

public class ParkingStickerCodeGenerator {
  private static final String CODE_KEYWORD = "PARK";
  private static final String SEPARATOR = "-";
  private static final String CODE_PATTERN = "[A-Z0-9]{6}";

  public ParkingStickerCode generate() {
    return new ParkingStickerCode(buildCode());
  }

  private String buildCode() {
    String generatedCode = Faker.instance().regexify(CODE_PATTERN);
    return CODE_KEYWORD.concat(SEPARATOR).concat(generatedCode);
  }
}
