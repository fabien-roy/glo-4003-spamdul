package ca.ulaval.glo4003.gateentries.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gateentries.assemblers.DayOfWeekAssembler;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import java.util.logging.Logger;

public class GateEntryService {
  private final Logger logger = Logger.getLogger(GateEntryService.class.getName());
  private final AccessPassService accessPassService;
  private final DayOfWeekAssembler dayOfWeekAssembler;
  private final AccessStatusAssembler accessStatusAssembler;
  private final LicensePlateAssembler licensePlateAssembler;

  public GateEntryService(
      AccessPassService accessPassService,
      DayOfWeekAssembler dayOfWeekAssembler,
      AccessStatusAssembler accessStatusAssembler,
      LicensePlateAssembler licensePlateAssembler) {
    this.accessPassService = accessPassService;
    this.dayOfWeekAssembler = dayOfWeekAssembler;
    this.accessStatusAssembler = accessStatusAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
  }

  // TODO: refator ? validateAccessPassWithCode
  public AccessStatusDto validateAccessPass(DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    logger.info(String.format("Validate access pass code %s", accessPassCode));

    DayOfWeek dayOfWeek = dayOfWeekAssembler.assemble(dayOfWeekDto);
    AccessPass accessPass = accessPassService.getAccessPass(accessPassCode);

    AccessStatus accessStatus = getAccessStatus(dayOfWeek, accessPass);

    return accessStatusAssembler.assemble(accessStatus);
  }

  // TODO refactor ? validateCar
  public AccessStatusDto validateCar(DayOfWeekDto dayOfWeekDto, String licensePlate) {
    logger.info(String.format("Validate for license plate %s", licensePlate));

    DayOfWeek dayOfWeek = dayOfWeekAssembler.assemble(dayOfWeekDto);
    LicensePlate licensePlateAssembled = licensePlateAssembler.assemble(licensePlate);
    AccessPass accessPass = accessPassService.getAccessPassByLicensePlate(licensePlateAssembled);

    AccessStatus accessStatus = getAccessStatus(dayOfWeek, accessPass);

    return accessStatusAssembler.assemble(accessStatus);
  }

  private AccessStatus getAccessStatus(DayOfWeek dayOfWeek, AccessPass accessPass) {
    return accessPass.validateAccessDay(dayOfWeek)
        ? AccessStatus.ACCESS_GRANTED
        : AccessStatus.ACCESS_REFUSED;
  }
}
