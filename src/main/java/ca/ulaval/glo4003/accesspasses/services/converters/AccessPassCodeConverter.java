package ca.ulaval.glo4003.accesspasses.services.converters;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassCodeException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;

public class AccessPassCodeConverter {
  public AccessPassCodeDto convert(AccessPassCode accessPassCode) {
    AccessPassCodeDto accessPassCodeDto = new AccessPassCodeDto();
    accessPassCodeDto.accessPassCode = accessPassCode.toString();
    return accessPassCodeDto;
  }

  public AccessPassCode convert(String code) {
    if (code == null) throw new InvalidAccessPassCodeException();

    return new AccessPassCode(code);
  }
}
