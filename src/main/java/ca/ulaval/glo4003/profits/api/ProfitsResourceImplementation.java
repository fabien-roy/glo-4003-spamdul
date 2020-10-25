package ca.ulaval.glo4003.profits.api;

import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import ca.ulaval.glo4003.profits.services.ProfitsService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
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
    return Response.status(Response.Status.OK)
        .entity(profitsDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getAccessPassProfits(int year, String isByConsumptionType) {
    Object entity;

    if (Boolean.parseBoolean(isByConsumptionType)) {
      List<ProfitsByConsumptionTypeDto> profitsByConsumptionTypesDto =
          profitsService.getAccessPassProfitsByConsumptionType(year);
      entity =
          new GenericEntity<List<ProfitsByConsumptionTypeDto>>(profitsByConsumptionTypesDto) {};
    } else {
      entity = profitsService.getAccessPassProfits(year);
    }

    return Response.status(Response.Status.OK)
        .entity(entity)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getOffenseProfits(int year) {
    ProfitsDto profitsDto = profitsService.getOffenseProfits(year);
    return Response.status(Response.Status.OK)
        .entity(profitsDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
