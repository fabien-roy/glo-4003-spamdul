package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import java.util.List;
import java.util.logging.Logger;

public class ReportParkingAreasService {
  private final Logger logger = Logger.getLogger(ReportParkingAreasService.class.getName());
  private final ReportRepository reportRepository;
  private final ReportPeriodAssembler reportPeriodAssembler;
  private final ReportQueryFactory reportQueryFactory;

  public ReportParkingAreasService(
      ReportRepository reportRepository,
      ReportPeriodAssembler reportPeriodAssembler,
      ReportQueryFactory reportQueryFactory) {
    this.reportRepository = reportRepository;
    this.reportPeriodAssembler = reportPeriodAssembler;
    this.reportQueryFactory = reportQueryFactory;
  }

  public List<ReportPeriodDto> getAllParkingAreaReports(String reportName, String month) {
    logger.info(String.format("Getting report for type %s at month %s", reportName, month));

    ReportType reportType = ReportType.get(reportName);
    ReportQuery reportQuery = reportQueryFactory.create(reportType, month);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);
    return reportPeriodAssembler.assembleMany(periods);
  }
}
