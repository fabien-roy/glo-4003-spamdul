package ca.ulaval.glo4003.cars.domain;

public class LicensePlate {
  private String licensePlate;

  public LicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  @Override
  public String toString() {
    return licensePlate;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    LicensePlate licensePlate = (LicensePlate) object;

    return this.licensePlate.equals(licensePlate.toString());
  }

  @Override
  public int hashCode() {
    return licensePlate.hashCode();
  }
}
