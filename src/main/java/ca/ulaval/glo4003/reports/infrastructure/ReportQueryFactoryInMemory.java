package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo4003.reports.domain.ReportType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeFactory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ConsumptionTypeDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ParkingAreaDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportEventTypeFilterInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.ProfitsMetricInMemory;
import java.util.List;

public class ReportQueryFactoryInMemory implements ReportQueryFactory<ReportQueryInMemory> {
  private final ReportScopeFactory reportScopeFactory;

  public ReportQueryFactoryInMemory(ReportScopeFactory reportScopeFactory) {
    this.reportScopeFactory = reportScopeFactory;
  }

  @Override
  public ReportQueryInMemory createGateEnteredReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes) {
    ReportQueryInMemory reportQuery = new ReportQueryInMemory();

    reportQuery.addFilter(new ReportEventTypeFilterInMemory(ReportEventType.GATE_ENTERED));

    switch (reportType) {
      case MONTHLY:
        reportQuery.setScope(reportScopeFactory.createMonthlyScope());
        break;
      case SUMMARY:
      case DAY_OF_MONTH:
        reportQuery.setScope(reportScopeFactory.createDailyScope(month));
    }

    reportQuery.addMetric(new GateEntriesMetricInMemory());

    reportQuery.addDimension(new ParkingAreaDimensionInMemory(parkingAreaCodes));

    return reportQuery;
  }

  // TODO #326 : Test this
  @Override
  public ReportQueryInMemory createBillPaidReportQuery(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    ReportQueryInMemory reportQuery = new ReportQueryInMemory();

    reportQuery.addFilter(new ReportEventTypeFilterInMemory(reportEventType));

    reportQuery.setScope(reportScopeFactory.createYearlyScope(year));

    reportQuery.addMetric(new ProfitsMetricInMemory());

    if (isByConsumptionType) {
      reportQuery.addDimension(new ConsumptionTypeDimensionInMemory());
    }

    return reportQuery;
  }
}
