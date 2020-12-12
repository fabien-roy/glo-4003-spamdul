package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.services.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDto;
import java.util.List;
import java.util.logging.Logger;

public class ReportProfitService {

  private final Logger logger = Logger.getLogger(ReportProfitService.class.getName());
  private final ReportRepository reportRepository;
  private final ReportQueryFactory reportQueryFactory;
  private final ReportPeriodAssembler reportPeriodAssembler;

  public ReportProfitService(
      ReportRepository reportRepository,
      ReportQueryFactory reportQueryFactory,
      ReportPeriodAssembler reportPeriodAssembler) {
    this.reportRepository = reportRepository;
    this.reportQueryFactory = reportQueryFactory;
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
        reportQueryFactory.createBillPaidReportQuery(reportEventType, year, isByConsumptionType);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

    return reportPeriodAssembler.assembleMany(periods);
  }
}
