package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimeMonth;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.util.Collections;

public class ReportQueryFactory {
  private final ReportQueryBuilder reportQueryBuilder;

  public ReportQueryFactory(ReportQueryBuilder reportQueryBuilder) {
    this.reportQueryBuilder = reportQueryBuilder;
  }

  public ReportQuery create(ReportType reportType, String month) {
    switch (reportType) {
      default:
      case MONTHLY:
        return getParkingAreasByMonthReports();
      case DAY_OF_MONTH:
      case SUMMARY:
        return getParkingAreasByDayOfMonthReports(month);
    }
  }

  private ReportQuery getParkingAreasByMonthReports() {
    return reportQueryBuilder
        .aReportQuery()
        .withReportEventType(ReportEventType.GATE_ENTERED)
        .withPeriod(TimeYear.now().toPeriod())
        .withScope(ReportScopeType.MONTHLY)
        .withMetrics(Collections.singletonList(ReportMetricType.GATE_ENTRIES))
        .withDimensions(Collections.singletonList(ReportDimensionType.PARKING_AREA))
        .build();
  }

  private ReportQuery getParkingAreasByDayOfMonthReports(String month) {
    return reportQueryBuilder
        .aReportQuery()
        .withReportEventType(ReportEventType.GATE_ENTERED)
        .withPeriod(new TimeMonth(month).toPeriod())
        .withScope(ReportScopeType.DAILY)
        .withMetrics(Collections.singletonList(ReportMetricType.GATE_ENTRIES))
        .withDimensions(Collections.singletonList(ReportDimensionType.PARKING_AREA))
        .build();
  }
}
