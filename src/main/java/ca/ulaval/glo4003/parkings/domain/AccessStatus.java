package ca.ulaval.glo4003.parkings.domain;

public enum AccessStatus {
  ACCESS_GRANTED("Access granted"),
  ACCESS_REFUSED("Access refused");

  private String status;

  AccessStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return status;
  }
}
