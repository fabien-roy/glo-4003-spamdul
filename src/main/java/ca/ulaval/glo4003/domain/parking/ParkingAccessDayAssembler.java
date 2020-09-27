package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;

public class ParkingAccessDayAssembler {
  public AccessStatusDto assemble(String status) {
    AccessStatusDto accessStatusDto = new AccessStatusDto();
    accessStatusDto.accessStatus = status;
    return accessStatusDto;
  }
}
