package ca.ulaval.glo4003.parkings.assemblers;

import ca.ulaval.glo4003.gates.services.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;

public class AccessStatusAssembler {
  public AccessStatusDto assemble(AccessStatus status) {
    AccessStatusDto accessStatusDto = new AccessStatusDto();
    accessStatusDto.accessStatus = status.toString();
    return accessStatusDto;
  }
}
