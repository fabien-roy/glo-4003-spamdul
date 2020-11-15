package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Collections;
import java.util.List;

public class ReportService {

  private final ReportRepository reportRepository;
  private final ReportQueryBuilder reportQueryBuilder;
  private final ReportPeriodAssembler reportPeriodAssembler;

  public ReportService(
      ReportRepository reportRepository,
      ReportQueryBuilder reportQueryBuilder,
      ReportPeriodAssembler reportPeriodAssembler) {
    this.reportRepository = reportRepository;
    this.reportQueryBuilder = reportQueryBuilder;
    this.reportPeriodAssembler = reportPeriodAssembler;
  }

  // TODO #246 : Test ReportService.getAll
  public List<ReportPeriodDto> getAllProfits(ReportEventType reportEventType, int year) {
    return getAllProfits(reportEventType, year, false);
  }

  // TODO #246 : Test ReportService.getAll
  public List<ReportPeriodDto> getAllProfits(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    ReportQuery reportQuery =
        reportQueryBuilder
            .aReportQuery()
            .withReportEventType(reportEventType)
            .withPeriod(TimePeriod.fromYear(year))
            .withScope(ReportScopeType.YEARLY)
            .withMetrics(Collections.singletonList(ReportMetricType.PROFITS))
            .withDimensions(
                isByConsumptionType
                    ? Collections.singletonList(ReportDimensionType.CONSUMPTION_TYPE)
                    : Collections.emptyList())
            .build();

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

    return reportPeriodAssembler.assembleMany(periods);
  }

  public void addBillPaymentEvent(Money payment) {
    // TODO #246
  }

  public void addBillPaymentEvent(Money payment, ConsumptionType consumptionType) {
    // TODO #246
  }
}
