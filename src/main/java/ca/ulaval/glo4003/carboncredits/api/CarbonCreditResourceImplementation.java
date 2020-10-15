package ca.ulaval.glo4003.carboncredits.api;

import ca.ulaval.glo4003.carboncredits.services.CarbonCreditsService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CarbonCreditResourceImplementation implements CarbonCreditResource {
  private final CarbonCreditsService carbonCreditsService;

  public CarbonCreditResourceImplementation(CarbonCreditsService carbonCreditsService) {
    this.carbonCreditsService = carbonCreditsService;
  }

  public Response getCarbonCredits() {
    Double carbonCredits = carbonCreditsService.getCarbonCredits();
    return Response.status(Response.Status.OK)
        .entity(carbonCredits)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
