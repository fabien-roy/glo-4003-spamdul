package ca.ulaval.glo4003.accesspasses.services.assemblers;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.InvalidAccessPassCodeException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;

public class AccessPassCodeAssembler {
  public AccessPassCodeDto assemble(AccessPassCode accessPassCode) {
    AccessPassCodeDto accessPassCodeDto = new AccessPassCodeDto();
    accessPassCodeDto.accessPassCode = accessPassCode.toString();
    return accessPassCodeDto;
  }

  public AccessPassCode assemble(String code) {
    if (code == null) throw new InvalidAccessPassCodeException();

    return new AccessPassCode(code);
  }
}
