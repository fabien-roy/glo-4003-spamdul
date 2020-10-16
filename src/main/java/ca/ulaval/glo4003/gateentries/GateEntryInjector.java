package ca.ulaval.glo4003.gateentries;

import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.gateentries.api.GateEntryResource;
import ca.ulaval.glo4003.gateentries.api.GateEntryResourceImplementation;
import ca.ulaval.glo4003.gateentries.assemblers.DayOfWeekAssembler;
import ca.ulaval.glo4003.gateentries.services.GateEntryService;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.domain.*;

public class GateEntryInjector {

  public GateEntryResource createGateEntryResource(AccessPassService accessPassService) {
    DayOfWeekAssembler dayOfWeekAssembler = new DayOfWeekAssembler();
    AccessStatusAssembler accessStatusAssembler = new AccessStatusAssembler();
    GateEntryService gateEntryService =
        new GateEntryService(accessPassService, dayOfWeekAssembler, accessStatusAssembler);

    return new GateEntryResourceImplementation(gateEntryService);
  }
}
