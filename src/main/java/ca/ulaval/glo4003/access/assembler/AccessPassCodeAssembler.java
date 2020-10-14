package ca.ulaval.glo4003.access.assembler;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.access.exceptions.InvalidAccessPassCodeException;

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
