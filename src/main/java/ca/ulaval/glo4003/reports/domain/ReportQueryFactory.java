package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import java.util.List;

public interface ReportQueryFactory<Q extends ReportQuery> {

  Q createGateEntriesReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes);

  Q createProfitsReportQuery(
      ReportEventType reportEventType, int year, boolean isByConsumptionType);
}
