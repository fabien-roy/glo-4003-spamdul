package ca.ulaval.glo4003.gate;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.gate.api.GateEntryResource;
import ca.ulaval.glo4003.gate.api.GateEntryResourceImplementation;
import ca.ulaval.glo4003.gate.assemblers.DayOfWeekAssembler;
import ca.ulaval.glo4003.gate.services.GateEntryService;
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
