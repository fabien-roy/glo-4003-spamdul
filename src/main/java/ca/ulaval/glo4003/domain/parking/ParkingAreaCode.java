package ca.ulaval.glo4003.domain.parking;

public class ParkingAreaCode {
  private String code;

  public ParkingAreaCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ParkingAreaCode parkingAreaCode = (ParkingAreaCode) object;

    return code.equals(parkingAreaCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
