package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.reports.services.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDto;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ReportProfitService {

  private final Logger logger = Logger.getLogger(ReportProfitService.class.getName());
  private final ReportRepository reportRepository;
  private final ReportQueryBuilder reportQueryBuilder;
  private final ReportPeriodAssembler reportPeriodAssembler;

  public ReportProfitService(
      ReportRepository reportRepository,
      ReportQueryBuilder reportQueryBuilder,
      ReportPeriodAssembler reportPeriodAssembler) {
    this.reportRepository = reportRepository;
    this.reportQueryBuilder = reportQueryBuilder;
    this.reportPeriodAssembler = reportPeriodAssembler;
  }

  public List<ReportPeriodDto> getAllProfits(ReportEventType reportEventType, int year) {
    return getAllProfits(reportEventType, year, false);
  }

  public List<ReportPeriodDto> getAllProfits(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    logger.info(
        String.format("Getting report for %s at year %s", reportEventType.toString(), year));

    ReportQuery reportQuery =
        reportQueryBuilder
            .aReportQuery()
            .withReportEventType(reportEventType)
            .withPeriod(new TimeYear(year).toPeriod())
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
}
