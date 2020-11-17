package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.util.Collections;
import java.util.List;

public class ReportParkingAreasService {
  // TODO test
  private ReportRepository reportRepository;
  private ReportQueryBuilder reportQueryBuilder;
  private ReportPeriodAssembler reportPeriodAssembler;

  public ReportParkingAreasService(
      ReportRepository reportRepository,
      ReportQueryBuilder reportQueryBuilder,
      ReportPeriodAssembler reportPeriodAssembler) {
    this.reportRepository = reportRepository;
    this.reportQueryBuilder = reportQueryBuilder;
    this.reportPeriodAssembler = reportPeriodAssembler;
  }

  public List<ReportPeriodDto> getReports(
      ReportEventType reportEventType, int year, String reportType, String scope) {
    ReportQuery reportQuery =
        reportQueryBuilder
            .aReportQuery()
            .withReportEventType(reportEventType)
            .withPeriod(new TimeYear(year).toPeriod())
            .withScope(ReportScopeType.get(scope))
            .withMetrics(
                Collections.singletonList(
                    ReportMetricType.valueOf(reportType))) // TODO change for get reportType
            .withDimensions(Collections.singletonList(ReportDimensionType.PARKING_AREA))
            .build();

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

    return reportPeriodAssembler.assembleMany(periods);
  }
}
