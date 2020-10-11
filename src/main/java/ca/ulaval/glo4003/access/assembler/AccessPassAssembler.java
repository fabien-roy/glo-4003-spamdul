package ca.ulaval.glo4003.access.assembler;

import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.Days;
import java.util.UUID;

public class AccessPassAssembler {
  public AccessPass assemble(AccessPassDto accessPassCodeDto, String accountId) {
    AccountId id = new AccountId(UUID.fromString(accountId));
    Days days = Days.get(accessPassCodeDto.accessDay);

    LicensePlate licensePlate;
    if (accessPassCodeDto.licensePlate != null) {
      licensePlate = new LicensePlate(accessPassCodeDto.licensePlate);
    } else {
      licensePlate = null;
    }
    AccessPass accessPass = new AccessPass(id, days, licensePlate);

    return accessPass;
  }
}
