package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.assemblers.ReportQueryAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import java.util.List;

public class ReportParkingAreasService {
  // TODO test
  private ReportRepository reportRepository;
  private ReportPeriodAssembler reportPeriodAssembler;
  private ReportQueryAssembler reportQueryAssembler;

  public ReportParkingAreasService(
      ReportRepository reportRepository,
      ReportPeriodAssembler reportPeriodAssembler,
      ReportQueryAssembler reportQueryAssembler) {
    this.reportRepository = reportRepository;
    this.reportPeriodAssembler = reportPeriodAssembler;
    this.reportQueryAssembler = reportQueryAssembler;
  }

  public List<ReportPeriodDto> getReports(String reportType, String month) {
    ReportQuery reportQuery = reportQueryAssembler.assemble(reportType, month);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);
    return reportPeriodAssembler.assembleMany(periods);
  }
}
