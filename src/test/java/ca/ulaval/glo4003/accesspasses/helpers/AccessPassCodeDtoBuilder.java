package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;

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
