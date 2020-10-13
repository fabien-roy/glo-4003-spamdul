package ca.ulaval.glo4003.profits.api;

import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import ca.ulaval.glo4003.profits.services.ProfitsService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ProfitsResourceImplementation implements ProfitsResource {

  private ProfitsService profitsService;

  public ProfitsResourceImplementation(ProfitsService profitsService) {
    this.profitsService = profitsService;
  }

  @Override
  public Response getParkingStickerProfits(int year) {
    ProfitsDto profitsDto = profitsService.getParkingStickerProfits(year);
    return Response.status(Response.Status.CREATED)
        .entity(profitsDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getAccessPassProfits(int year, boolean isByConsumptionType) {
    if (isByConsumptionType) {
      ProfitsByConsumptionTypeDto profitsByConsumptionTyepeDto =
          profitsService.getAccessPassProfitsByConsumptionType(year);
      return Response.status(Response.Status.CREATED)
          .entity(profitsByConsumptionTyepeDto)
          .type(MediaType.APPLICATION_JSON)
          .build();
    } else {
      ProfitsDto profitsDto = profitsService.getAccessPassProfits(year);
      return Response.status(Response.Status.CREATED)
          .entity(profitsDto)
          .type(MediaType.APPLICATION_JSON)
          .build();
    }
  }

  @Override
  public Response getOffenseProfits(int year) {
    ProfitsDto profitsDto = profitsService.getOffenseProfits(year);
    return Response.status(Response.Status.CREATED)
        .entity(profitsDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
