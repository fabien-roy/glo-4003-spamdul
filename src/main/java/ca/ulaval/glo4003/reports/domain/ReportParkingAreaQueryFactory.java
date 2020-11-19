package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimeMonth;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.util.Collections;
import java.util.List;

public class ReportParkingAreaQueryFactory {
  private final ReportQueryBuilder reportQueryBuilder;

  public ReportParkingAreaQueryFactory(ReportQueryBuilder reportQueryBuilder) {
    this.reportQueryBuilder = reportQueryBuilder;
  }

  public ReportQuery create(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes) {
    switch (reportType) {
      default:
      case MONTHLY:
        return getParkingAreasByMonthReports(parkingAreaCodes);
      case DAY_OF_MONTH:
      case SUMMARY:
        return getParkingAreasByDayOfMonthReports(month, parkingAreaCodes);
    }
  }

  private ReportQuery getParkingAreasByMonthReports(List<ParkingAreaCode> parkingAreaCodes) {
    return reportQueryBuilder
        .aReportQuery()
        .withReportEventType(ReportEventType.GATE_ENTERED)
        .withPeriod(TimeYear.now().toPeriod())
        .withScope(ReportScopeType.MONTHLY)
        .withMetrics(Collections.singletonList(ReportMetricType.GATE_ENTRIES))
        .withDimensions(Collections.singletonList(ReportDimensionType.PARKING_AREA))
        .withParkingAreaCodes(parkingAreaCodes)
        .build();
  }

  private ReportQuery getParkingAreasByDayOfMonthReports(
      String month, List<ParkingAreaCode> parkingAreaCodes) {
    return reportQueryBuilder
        .aReportQuery()
        .withReportEventType(ReportEventType.GATE_ENTERED)
        .withPeriod(new TimeMonth(month).toPeriod())
        .withScope(ReportScopeType.DAILY)
        .withMetrics(Collections.singletonList(ReportMetricType.GATE_ENTRIES))
        .withDimensions(Collections.singletonList(ReportDimensionType.PARKING_AREA))
        .withParkingAreaCodes(parkingAreaCodes)
        .build();
  }
}
