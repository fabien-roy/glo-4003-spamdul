package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import java.util.List;

public interface ReportQueryFactory<Q extends ReportQuery> {

  Q createGateEnteredReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes);

  Q createGateEnteredSummaryReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes);

  Q createBillPaidReportQuery(
      ReportEventType reportEventType, int year, boolean isByConsumptionType);
}
