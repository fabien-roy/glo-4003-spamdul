package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import java.util.List;

public class ReportParkingAreasService {
  // TODO test
  private ReportRepository reportRepository;
  private ReportPeriodAssembler reportPeriodAssembler;
  private ReportQueryFactory reportQueryFactory;

  public ReportParkingAreasService(
      ReportRepository reportRepository,
      ReportPeriodAssembler reportPeriodAssembler,
      ReportQueryFactory reportQueryFactory) {
    this.reportRepository = reportRepository;
    this.reportPeriodAssembler = reportPeriodAssembler;
    this.reportQueryFactory = reportQueryFactory;
  }

  public List<ReportPeriodDto> getReports(String reportName, String month) {
    ReportType reportType = ReportType.get(reportName);
    ReportQuery reportQuery = reportQueryFactory.create(reportType, month);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);
    return reportPeriodAssembler.assembleMany(periods);
  }
}
