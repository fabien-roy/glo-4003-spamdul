package ca.ulaval.glo4003.reports.assemblers;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDataDto;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import java.util.List;
import java.util.stream.Collectors;

// TODO #246 : Test ReportPeriodDataAssembler
public class ReportPeriodDataAssembler {

  private final ReportDimensionDataAssembler reportDimensionDataAssembler;
  private final ReportMetricDataAssembler reportMetricDataAssembler;

  public ReportPeriodDataAssembler(
      ReportDimensionDataAssembler reportDimensionDataAssembler,
      ReportMetricDataAssembler reportMetricDataAssembler) {
    this.reportDimensionDataAssembler = reportDimensionDataAssembler;
    this.reportMetricDataAssembler = reportMetricDataAssembler;
  }

  public List<ReportPeriodDataDto> assembleMany(List<ReportPeriodData> data) {
    return data.stream().map(this::assembleOne).collect(Collectors.toList());
  }

  private ReportPeriodDataDto assembleOne(ReportPeriodData data) {
    ReportPeriodDataDto reportPeriodDataDto = new ReportPeriodDataDto();
    reportPeriodDataDto.dimensions =
        reportDimensionDataAssembler.assembleMany(data.getDimensions());
    reportPeriodDataDto.metrics = reportMetricDataAssembler.assembleMany(data.getMetrics());
    return reportPeriodDataDto;
  }
}
