package ca.ulaval.glo4003.accesspasses.assembler;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassCodeException;

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
