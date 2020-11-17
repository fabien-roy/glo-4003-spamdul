package ca.ulaval.glo4003.reports.api;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.services.ReportService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ReportResourceImplementation implements ReportResource {
  private final ReportService reportService;

  public ReportResourceImplementation(ReportService reportService) {
    this.reportService = reportService;
  }

  @Override
  public Response getParkingStickerProfits(int year) {
    List<ReportPeriodDto> periodDtos =
        reportService.getAllProfits(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, year);

    return buildResponse(periodDtos);
  }

  @Override
  public Response getAccessPassProfits(int year, String isByConsumptionType) {
    List<ReportPeriodDto> periodDtos =
        reportService.getAllProfits(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS,
            year,
            Boolean.parseBoolean(isByConsumptionType));

    return buildResponse(periodDtos);
  }

  @Override
  public Response getOffenseProfits(int year) {
    List<ReportPeriodDto> periodDtos =
        reportService.getAllProfits(ReportEventType.BILL_PAID_FOR_OFFENSE, year);

    return buildResponse(periodDtos);
  }

  private Response buildResponse(List<ReportPeriodDto> periodDtos) {
    GenericEntity<List<ReportPeriodDto>> entities =
        new GenericEntity<List<ReportPeriodDto>>(periodDtos) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
