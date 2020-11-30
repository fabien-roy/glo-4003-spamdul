package ca.ulaval.glo4003.carboncredits.api;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.MonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/carbonCredits")
public class CarbonCreditResource {
  private final CarbonCreditService carbonCreditService;

  public CarbonCreditResource(CarbonCreditService carbonCreditService) {
    this.carbonCreditService = carbonCreditService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response modifyCarbonCreditMonthlyPaymentStatus(
      MonthlyPaymentStatusDto monthlyPaymentStatusDto) {
    carbonCreditService.modifyMonthlyPaymentStatus(monthlyPaymentStatusDto);
    return Response.status(Response.Status.OK).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCarbonCredits() {
    CarbonCreditDto carbonCreditDto = carbonCreditService.getCarbonCredits();
    return Response.status(Response.Status.OK)
        .entity(carbonCreditDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
