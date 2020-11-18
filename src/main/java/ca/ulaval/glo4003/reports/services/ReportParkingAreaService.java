package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReportParkingAreaService {
  private final Logger logger = Logger.getLogger(ReportParkingAreaService.class.getName());
  private final ReportRepository reportRepository;
  private final ReportPeriodAssembler reportPeriodAssembler;
  private final ReportQueryFactory reportQueryFactory;
  private final ReportSummaryBuilder reportSummaryBuilder;

  public ReportParkingAreaService(
      ReportRepository reportRepository,
      ReportPeriodAssembler reportPeriodAssembler,
      ReportQueryFactory reportQueryFactory,
      ReportSummaryBuilder reportSummaryBuilder) {
    this.reportRepository = reportRepository;
    this.reportPeriodAssembler = reportPeriodAssembler;
    this.reportQueryFactory = reportQueryFactory;
    this.reportSummaryBuilder = reportSummaryBuilder;
  }

  public List<ReportPeriodDto> getAllParkingAreaReports(String reportName, String month) {
    logger.info(String.format("Getting report for type %s at month %s", reportName, month));

    ReportType reportType = ReportType.get(reportName);
    ReportQuery reportQuery = reportQueryFactory.create(reportType, month);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

    // TODO #238 : Test ReportParkingAreaService for summary type
    if (reportType.equals(ReportType.SUMMARY)) {
      periods = getSummaryPeriods(periods);
    }

    return reportPeriodAssembler.assembleMany(periods);
  }

  private List<ReportPeriod> getSummaryPeriods(List<ReportPeriod> periods) {
    return reportSummaryBuilder
        .aReportSummary()
        .withPeriods(periods)
        .withAggregateFunctions(
            Arrays.asList(
                ReportAggregateFunctionType.MAXIMUM,
                ReportAggregateFunctionType.MINIMUM,
                ReportAggregateFunctionType.AVERAGE))
        .withMetric(ReportMetricType.GATE_ENTRIES)
        .build();
  }
}
