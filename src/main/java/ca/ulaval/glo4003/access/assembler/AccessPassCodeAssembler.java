package ca.ulaval.glo4003.access.assembler;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.domain.AccessPassCode;

public class AccessPassCodeAssembler {
  public AccessPassCodeDto assemble(AccessPassCode accessPassCode) {
    AccessPassCodeDto accessPassCodeDto = new AccessPassCodeDto();
    accessPassCodeDto.accessPassCode = accessPassCode.toString();
    return accessPassCodeDto;
  }

  // TODO : AccessPassCodeAssembler.assemble
  public AccessPassCode assemble(String code) {
    return null;
  }
}
