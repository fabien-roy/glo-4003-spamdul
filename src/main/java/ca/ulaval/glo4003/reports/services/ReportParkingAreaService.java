package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReportParkingAreaService {
  private final Logger logger = Logger.getLogger(ReportParkingAreaService.class.getName());
  private final ParkingAreaService parkingAreaService;
  private final ReportRepository reportRepository;
  private final ReportPeriodAssembler reportPeriodAssembler;
  private final ReportParkingAreaQueryFactory reportParkingAreaQueryFactory;
  private final ReportSummaryBuilder reportSummaryBuilder;

  public ReportParkingAreaService(
      ParkingAreaService parkingAreaService,
      ReportRepository reportRepository,
      ReportPeriodAssembler reportPeriodAssembler,
      ReportParkingAreaQueryFactory reportParkingAreaQueryFactory,
      ReportSummaryBuilder reportSummaryBuilder) {
    this.parkingAreaService = parkingAreaService;
    this.reportRepository = reportRepository;
    this.reportPeriodAssembler = reportPeriodAssembler;
    this.reportParkingAreaQueryFactory = reportParkingAreaQueryFactory;
    this.reportSummaryBuilder = reportSummaryBuilder;
  }

  public List<ReportPeriodDto> getAllParkingAreaReports(String reportName, String month) {
    logger.info(String.format("Getting report for type %s at month %s", reportName, month));

    ReportType reportType = ReportType.get(reportName);
    List<ParkingAreaCode> parkingAreaCodes = parkingAreaService.getParkingAreaCodes();
    ReportQuery reportQuery =
        reportParkingAreaQueryFactory.create(reportType, month, parkingAreaCodes);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

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
