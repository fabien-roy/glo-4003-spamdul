package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;

public class AccessPassCodeDtoBuilder {
  public static AccessPassCodeDtoBuilder anAccessPassCodeDto() {
    return new AccessPassCodeDtoBuilder();
  }

  public AccessPassCodeDto build() {
    AccessPassCodeDto accessPassCodeDto = new AccessPassCodeDto();
    accessPassCodeDto.accessPassCode = createAccessPassCode().toString();
    return accessPassCodeDto;
  }
}
