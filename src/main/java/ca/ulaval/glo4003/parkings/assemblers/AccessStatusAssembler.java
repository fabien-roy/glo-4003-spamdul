package ca.ulaval.glo4003.parkings.assemblers;

import ca.ulaval.glo4003.parkings.api.dto.AccessStatusDto;

public class AccessStatusAssembler {
  public AccessStatusDto assemble(String status) {
    AccessStatusDto accessStatusDto = new AccessStatusDto();
    accessStatusDto.accessStatus = status;
    return accessStatusDto;
  }
}
