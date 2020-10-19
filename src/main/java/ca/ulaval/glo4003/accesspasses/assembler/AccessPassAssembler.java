package ca.ulaval.glo4003.accesspasses.assembler;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

// TODO : Test AccessPassAssembler
public class AccessPassAssembler {

  private final AccountIdAssembler accountIdAssembler;
  private final LicensePlateAssembler licensePlateAssembler;

  public AccessPassAssembler(
      AccountIdAssembler accountIdAssembler, LicensePlateAssembler licensePlateAssembler) {
    this.accountIdAssembler = accountIdAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
  }

  public AccessPass assemble(AccessPassDto accessPassCodeDto, String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    DayOfWeek dayOfWeek = DayOfWeek.get(accessPassCodeDto.accessDay);
    LicensePlate licensePlate;
    if (accessPassCodeDto.licensePlate != null) {
      licensePlate = licensePlateAssembler.assemble(accessPassCodeDto.licensePlate);
    } else {
      licensePlate = null;
    }
    return new AccessPass(id, dayOfWeek, licensePlate);
  }
}
