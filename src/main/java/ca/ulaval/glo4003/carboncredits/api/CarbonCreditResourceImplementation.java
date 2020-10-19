package ca.ulaval.glo4003.carboncredits.api;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditMonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CarbonCreditResourceImplementation implements CarbonCreditResource {
  private final CarbonCreditService carbonCreditService;

  public CarbonCreditResourceImplementation(CarbonCreditService carbonCreditService) {
    this.carbonCreditService = carbonCreditService;
  }

  @Override
  public Response modifyCarbonCreditMonthlyPaymentStatus(
      CarbonCreditMonthlyPaymentStatusDto carbonCreditMonthlyPaymentStatusDto) {
    carbonCreditService.modifyCarbonCreditMonthlyPaymentStatus(carbonCreditMonthlyPaymentStatusDto);
    return Response.status(Response.Status.OK).build();
  }

  @Override
  public Response getCarbonCredits() {
    CarbonCreditDto carbonCreditDto = carbonCreditService.getCarbonCredits();
    return Response.status(Response.Status.OK)
        .entity(carbonCreditDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
