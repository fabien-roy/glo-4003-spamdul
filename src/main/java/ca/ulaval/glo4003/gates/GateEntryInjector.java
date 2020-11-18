package ca.ulaval.glo4003.gates;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.gates.api.GateEntryResource;
import ca.ulaval.glo4003.gates.api.GateEntryResourceImplementation;
import ca.ulaval.glo4003.gates.assemblers.DayOfWeekAssembler;
import ca.ulaval.glo4003.gates.services.GateEntryService;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;

public class GateEntryInjector {

  public GateEntryResource createGateEntryResource(AccessPassService accessPassService) {
    DayOfWeekAssembler dayOfWeekAssembler = new DayOfWeekAssembler();
    AccessStatusAssembler accessStatusAssembler = new AccessStatusAssembler();
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    GateEntryService gateEntryService =
        new GateEntryService(
            accessPassService, dayOfWeekAssembler, accessStatusAssembler, licensePlateAssembler);

    return new GateEntryResourceImplementation(gateEntryService);
  }
}
