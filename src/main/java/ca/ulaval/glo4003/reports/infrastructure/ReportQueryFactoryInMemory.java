package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo4003.reports.domain.ReportType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeFactory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ParkingAreaDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportEventTypeFilterInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesMetricInMemory;
import java.util.List;

// TODO #326 : Review method names with report event type names
public class ReportQueryFactoryInMemory implements ReportQueryFactory<ReportQueryInMemory> {
  private final ReportScopeFactory reportScopeFactory;

  public ReportQueryFactoryInMemory(ReportScopeFactory reportScopeFactory) {
    this.reportScopeFactory = reportScopeFactory;
  }

  // TODO #326 : Test this
  @Override
  public ReportQueryInMemory createGateEntriesReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes) {
    ReportQueryInMemory reportQuery = new ReportQueryInMemory();

    // TODO #326 : Move this to a filter
    reportQuery.addFilter(new ReportEventTypeFilterInMemory(ReportEventType.GATE_ENTERED));

    // TODO #326 : Move this to a filter
    switch (reportType) {
      case MONTHLY:
        reportQuery.setScope(reportScopeFactory.createMonthlyScope());
        break;
      case SUMMARY:
      case DAY_OF_MONTH:
        reportQuery.setScope(reportScopeFactory.createDailyScope(month));
        break;
    }

    // TODO #326 : Move this to a filter
    reportQuery.addMetric(new GateEntriesMetricInMemory());

    // TODO #326 : Move this to a filter
    reportQuery.addDimension(new ParkingAreaDimensionInMemory(parkingAreaCodes));

    return reportQuery;
  }

  @Override
  public ReportQueryInMemory createProfitsReportQuery(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    // TODO #326
    return null;
  }
}
