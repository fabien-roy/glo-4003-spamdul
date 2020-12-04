package ca.ulaval.glo4003.gates;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.gates.api.GateResource;
import ca.ulaval.glo4003.gates.services.GateService;
import ca.ulaval.glo4003.parkings.services.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.times.services.converters.CustomDateTimeConverter;

public class GateInjector {

  public GateResource createGateResource(
      AccessPassService accessPassService,
      CustomDateTimeConverter customDateTimeConverter,
      ReportEventService reportEventService) {
    AccessStatusAssembler accessStatusAssembler = new AccessStatusAssembler();
    LicensePlateConverter licensePlateConverter = new LicensePlateConverter();
    GateService gateService =
        new GateService(
            accessPassService,
            customDateTimeConverter,
            accessStatusAssembler,
            licensePlateConverter,
            reportEventService);

    return new GateResource(gateService);
  }
}
