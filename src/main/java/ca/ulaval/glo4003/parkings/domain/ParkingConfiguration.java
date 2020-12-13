package ca.ulaval.glo4003.parkings.domain;

public class ParkingConfiguration {
  private static ParkingConfiguration parkingConfiguration = null;

  private final ParkingAreaCode bicycleParkingAreaCode = new ParkingAreaCode("ZoneVelo");

  private ParkingConfiguration() {}

  public static ParkingConfiguration getConfiguration() {
    if (parkingConfiguration == null) {
      parkingConfiguration = new ParkingConfiguration();
    }
    return parkingConfiguration;
  }

  public ParkingAreaCode getBicycleParkingAreaCode() {
    return bicycleParkingAreaCode;
  }
}
