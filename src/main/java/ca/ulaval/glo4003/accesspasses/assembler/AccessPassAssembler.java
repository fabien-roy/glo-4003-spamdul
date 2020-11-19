package ca.ulaval.glo4003.accesspasses.assembler;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class AccessPassAssembler {

  private final AccountIdAssembler accountIdAssembler;
  private final LicensePlateAssembler licensePlateAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public AccessPassAssembler(
      AccountIdAssembler accountIdAssembler,
      LicensePlateAssembler licensePlateAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    this.accountIdAssembler = accountIdAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
  }

  public AccessPass assemble(AccessPassDto accessPassCodeDto, String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    DayOfWeek dayOfWeek = DayOfWeek.get(accessPassCodeDto.accessDay);
    LicensePlate licensePlate;
    ParkingAreaCode parkingAreaCode;
    if (accessPassCodeDto.licensePlate != null) {
      licensePlate = licensePlateAssembler.assemble(accessPassCodeDto.licensePlate);
      parkingAreaCode = parkingAreaCodeAssembler.assemble(accessPassCodeDto.parkingAreaCode);
    } else {
      licensePlate = null;
      parkingAreaCode = null;
    }
    return new AccessPass(id, dayOfWeek, licensePlate, parkingAreaCode, false);
  }
}
