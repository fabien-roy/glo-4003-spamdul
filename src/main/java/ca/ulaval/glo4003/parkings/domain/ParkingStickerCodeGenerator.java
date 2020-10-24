package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;

public class ParkingStickerCodeGenerator {
  private static final String KEYWORD = "PARK";
  private final StringCodeGenerator stringCodeGenerator;

  public ParkingStickerCodeGenerator(StringCodeGenerator stringCodeGenerator) {
    this.stringCodeGenerator = stringCodeGenerator;
  }

  public ParkingStickerCode generate() {
    String code = stringCodeGenerator.generate(KEYWORD);
    return new ParkingStickerCode(code);
  }
}
