package ca.ulaval.glo4003.infrastructure.parking;

import ca.ulaval.glo4003.domain.parking.ParkingArea;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import java.util.ArrayList;
import java.util.List;

public class ParkingAreaFakeFactory {

  public List<ParkingArea> createMockData() {
    List<ParkingArea> parkingAreas = new ArrayList<>();

    ParkingArea desjardins = new ParkingArea(new ParkingAreaCode("1"));
    parkingAreas.add(desjardins);

    ParkingArea pouliot = new ParkingArea(new ParkingAreaCode("2"));
    parkingAreas.add(pouliot);

    ParkingArea peps = new ParkingArea(new ParkingAreaCode("3"));
    parkingAreas.add(peps);

    return parkingAreas;
  }
}
