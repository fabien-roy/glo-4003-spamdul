package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaCodeDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;

public class ParkingAreaCodeDtoBuilder {
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();

  public static ParkingAreaCodeDtoBuilder aParkingAreaCodeDto() {
    return new ParkingAreaCodeDtoBuilder();
  }

  public ParkingAreaCodeDto build() {
    ParkingAreaCodeDto parkingAreaCodeDto = new ParkingAreaCodeDto();
    parkingAreaCodeDto.parkingArea = parkingAreaCode.toString();

    return parkingAreaCodeDto;
  }
}
