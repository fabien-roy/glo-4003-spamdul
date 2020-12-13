package ca.ulaval.glo4003.gates;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.gates.api.GateResource;
import ca.ulaval.glo4003.gates.services.GateService;
import ca.ulaval.glo4003.reports.services.ReportEventService;

public class GateInjector {

  public GateResource createGateResource(
      AccessPassService accessPassService, ReportEventService reportEventService) {
    GateService gateService = new GateService(accessPassService, reportEventService);

    return new GateResource(gateService);
  }
}
