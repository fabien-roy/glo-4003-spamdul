package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;

public class ParkingAreaDtoBuilder {
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();

  public static ParkingAreaDtoBuilder aParkingAreaDto() {
    return new ParkingAreaDtoBuilder();
  }

  public ParkingAreaDto build() {
    ParkingAreaDto parkingAreaDto = new ParkingAreaDto();
    parkingAreaDto.parkingArea = parkingAreaCode.toString();

    return parkingAreaDto;
  }
}
