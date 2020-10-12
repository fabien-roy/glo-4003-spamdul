package ca.ulaval.glo4003.access.assembler;

import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.Days;

public class AccessPassAssembler {

  private AccountIdAssembler accountIdAssembler;
  private LicensePlateAssembler licensePlateAssembler;

  public AccessPassAssembler(
      AccountIdAssembler accountIdAssembler, LicensePlateAssembler licensePlateAssembler) {
    this.accountIdAssembler = accountIdAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
  }

  public AccessPass assemble(AccessPassDto accessPassCodeDto, String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    Days days = Days.get(accessPassCodeDto.accessDay);
    LicensePlate licensePlate = licensePlateAssembler.assemble(accessPassCodeDto.licensePlate);
    AccessPass accessPass = new AccessPass(id, days, licensePlate);

    return accessPass;
  }
}
