package ca.ulaval.glo4003.gates.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.gates.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.times.api.dto.DateTimeDto;
import ca.ulaval.glo4003.times.assemblers.CustomDateTimeAssembler;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import java.util.List;
import java.util.logging.Logger;

public class GateService {
  private final Logger logger = Logger.getLogger(GateService.class.getName());
  private final AccessPassService accessPassService;
  private final CustomDateTimeAssembler customDateTimeAssembler;
  private final AccessStatusAssembler accessStatusAssembler;
  private final LicensePlateAssembler licensePlateAssembler;
  private final ReportEventService reportEventService;

  public GateService(
      AccessPassService accessPassService,
      CustomDateTimeAssembler customDateTimeAssembler,
      AccessStatusAssembler accessStatusAssembler,
      LicensePlateAssembler licensePlateAssembler,
      ReportEventService reportEventService) {
    this.accessPassService = accessPassService;
    this.customDateTimeAssembler = customDateTimeAssembler;
    this.accessStatusAssembler = accessStatusAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
    this.reportEventService = reportEventService;
  }

  public AccessStatusDto validateAccessPassEntryWithCode(
      DateTimeDto dateTimeDto, String accessPassCode) {
    logger.info(String.format("Validate entry with access pass code %s", accessPassCode));

    CustomDateTime dateTime = customDateTimeAssembler.assemble(dateTimeDto);
    AccessPass accessPass = accessPassService.getAccessPass(accessPassCode);

    AccessStatus accessStatus = getAccessStatus(dateTime, accessPass);

    if (accessStatus == AccessStatus.ACCESS_GRANTED) {
      accessPassService.enterCampus(accessPass);
      reportEventService.addAccessAreasCodeEvent(accessPass.getParkingAreaCode());
    }

    return accessStatusAssembler.assemble(accessStatus);
  }

  public AccessStatusDto validateAccessPassEntryWithLicensePlate(
      DateTimeDto dateTimeDto, String licensePlate) {
    logger.info(String.format("Validate entry with license plate %s", licensePlate));
    AccessPass associatedAccessPass = null;

    CustomDateTime dateTime = customDateTimeAssembler.assemble(dateTimeDto);
    List<AccessPass> accessPasses = getAccessPasses(licensePlate);

    for (AccessPass accessPass : accessPasses) {
      if (getAccessStatus(dateTime, accessPass).equals(AccessStatus.ACCESS_GRANTED)) {
        associatedAccessPass = accessPass;
      }
    }

    if (associatedAccessPass != null) {
      accessPassService.enterCampus(associatedAccessPass);
      reportEventService.addAccessAreasCodeEvent(associatedAccessPass.getParkingAreaCode());
      return accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED);
    }

    return accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED);
  }

  public void validateAccessPassExitWithCode(String accessPassCode) {
    logger.info(String.format("Validate exit with access pass code %s", accessPassCode));
    AccessPass accessPass = accessPassService.getAccessPass(accessPassCode);
    accessPassService.exitCampus(accessPass);
  }

  public void validateAccessPassExitWithLicensePlate(String licensePlate) {
    logger.info(String.format("Validate exit with license plate %s", licensePlate));
    List<AccessPass> accessPasses = getAccessPasses(licensePlate);
    AccessPass associatedAccessPass = null;

    for (AccessPass accessPass : accessPasses) {
      if (accessPass.isAdmittedOnCampus()) {
        associatedAccessPass = accessPass;
      }
    }

    if (associatedAccessPass != null) {
      accessPassService.exitCampus(associatedAccessPass);
    } else {
      throw new InvalidAccessPassExitException();
    }
  }

  private AccessStatus getAccessStatus(CustomDateTime dateTime, AccessPass accessPass) {
    return accessPass.validateAccess(dateTime) && !accessPass.isAdmittedOnCampus()
        ? AccessStatus.ACCESS_GRANTED
        : AccessStatus.ACCESS_REFUSED;
  }

  private List<AccessPass> getAccessPasses(String licensePlate) {
    LicensePlate licensePlateAssembled = licensePlateAssembler.assemble(licensePlate);

    return accessPassService.getAccessPasses(licensePlateAssembled);
  }
}
