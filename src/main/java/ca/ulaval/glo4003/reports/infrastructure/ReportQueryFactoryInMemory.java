package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo4003.reports.domain.ReportType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeFactory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ConsumptionTypeDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ParkingAreaDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportEventTypeFilterInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesForBicyclesMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesForCarsMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.ProfitsMetricInMemory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReportQueryFactoryInMemory implements ReportQueryFactory<ReportQueryInMemory> {
  private final ReportScopeFactory reportScopeFactory;

  public ReportQueryFactoryInMemory(ReportScopeFactory reportScopeFactory) {
    this.reportScopeFactory = reportScopeFactory;
  }

  @Override
  public ReportQueryInMemory createGateEnteredReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes) {
    return new ReportQueryInMemory(
        createReportScopeForGateEnteredReportQuery(reportType, month),
        Collections.singletonList(new GateEntriesMetricInMemory()),
        Collections.singletonList(new ParkingAreaDimensionInMemory(parkingAreaCodes)),
        Collections.singletonList(new ReportEventTypeFilterInMemory(ReportEventType.GATE_ENTERED)));
  }

  // TODO #317 : Test this
  @Override
  public ReportQueryInMemory createGateEnteredSummaryReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes) {
    return new ReportQueryInMemory(
        createReportScopeForGateEnteredReportQuery(reportType, month),
        Arrays.asList(
            new GateEntriesForCarsMetricInMemory(), new GateEntriesForBicyclesMetricInMemory()),
        Collections.singletonList(new ParkingAreaDimensionInMemory(parkingAreaCodes)),
        Collections.singletonList(new ReportEventTypeFilterInMemory(ReportEventType.GATE_ENTERED)));
  }

  @Override
  public ReportQueryInMemory createBillPaidReportQuery(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    return new ReportQueryInMemory(
        reportScopeFactory.createYearlyScope(year),
        Collections.singletonList(new ProfitsMetricInMemory()),
        isByConsumptionType
            ? Collections.singletonList(new ConsumptionTypeDimensionInMemory())
            : Collections.emptyList(),
        Collections.singletonList(new ReportEventTypeFilterInMemory(reportEventType)));
  }

  private ReportScope createReportScopeForGateEnteredReportQuery(
      ReportType reportType, String month) {
    switch (reportType) {
      case MONTHLY:
        return reportScopeFactory.createMonthlyScope();
      default:
      case SUMMARY:
      case DAY_OF_MONTH:
        return reportScopeFactory.createDailyScope(month);
    }
  }
}
