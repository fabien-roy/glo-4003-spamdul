package ca.ulaval.glo4003.reports.assemblers;

import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportQuery;
import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.reports.exceptions.InvalidReportTypeException;
import ca.ulaval.glo4003.times.domain.TimeMonth;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.time.Year;
import java.util.Collections;

public class ReportQueryAssembler {
  // TODO test
  private ReportQueryBuilder reportQueryBuilder;

  public ReportQueryAssembler(ReportQueryBuilder reportQueryBuilder) {
    this.reportQueryBuilder = reportQueryBuilder;
  }

  public ReportQuery assemble(String reportType, String month) {
    if (reportType.equals("summary")) {
      return getParkingAreasSummaryReport();
    } else if (reportType.equals("monthly")) {
      return getParkingAreasByMonthReports();
    } else if (reportType.equals("dayOfMonth")) {
      return getParkingAreasByDayOfMonthReports(month);
    } else {
      throw new InvalidReportTypeException();
    }
  }

  private ReportQuery getParkingAreasByMonthReports() {
    return reportQueryBuilder
        .aReportQuery()
        .withReportEventType(ReportEventType.GATE_ENTERED)
        .withPeriod(new TimeYear(Year.now().getValue()).toPeriod())
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

  private ReportQuery getParkingAreasSummaryReport() {
    // TODO
    return null;
  }
}
