package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;

public class AccessPassCodeDtoBuilder {
  private String accessPassCode = createAccessPassCode().toString();

  public static AccessPassCodeDtoBuilder anAccessPassCodeDto() {
    return new AccessPassCodeDtoBuilder();
  }

  public AccessPassCodeDto build() {
    AccessPassCodeDto accessPassCodeDto = new AccessPassCodeDto();
    accessPassCodeDto.accessPassCode = accessPassCode;
    return accessPassCodeDto;
  }
}
