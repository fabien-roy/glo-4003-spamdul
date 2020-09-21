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

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ParkingStickerCode parkingStickerCode = (ParkingStickerCode) object;

    return code.equals(parkingStickerCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
