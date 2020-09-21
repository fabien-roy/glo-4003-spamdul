package ca.ulaval.glo4003.domain.parking;

public class ParkingStickerCode {
  private String code;

  public ParkingStickerCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }
}
