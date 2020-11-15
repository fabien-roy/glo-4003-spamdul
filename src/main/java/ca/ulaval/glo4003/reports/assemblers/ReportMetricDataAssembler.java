package ca.ulaval.glo4003.reports.assemblers;

import ca.ulaval.glo4003.reports.api.dto.DoubleReportMetricDataDto;
import ca.ulaval.glo4003.reports.api.dto.IntegerReportMetricDataDto;
import ca.ulaval.glo4003.reports.api.dto.ReportMetricDataDto;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import java.util.List;
import java.util.stream.Collectors;

// TODO #246 : Test ReportMetricDataAssembler
public class ReportMetricDataAssembler {

  public List<ReportMetricDataDto> assembleMany(List<ReportMetricData> metrics) {
    return metrics.stream().map(this::assembleOne).collect(Collectors.toList());
  }

  private ReportMetricDataDto assembleOne(ReportMetricData data) {
    String name = data.getType().toString();
    Object value = data.getValue();

    switch (data.getValueType()) {
      case DOUBLE:
        return assembleDoubleReportMetricDataDto(name, (double) value);
      default:
      case INTEGER:
        return assembleIntegerReportMetricDataDto(name, (int) value);
    }
  }

  private ReportMetricDataDto assembleDoubleReportMetricDataDto(String name, double value) {
    DoubleReportMetricDataDto reportMetricDataDto = new DoubleReportMetricDataDto();
    reportMetricDataDto.name = name;
    reportMetricDataDto.value = value;
    return reportMetricDataDto;
  }

  private ReportMetricDataDto assembleIntegerReportMetricDataDto(String name, int value) {
    IntegerReportMetricDataDto reportMetricDataDto = new IntegerReportMetricDataDto();
    reportMetricDataDto.name = name;
    reportMetricDataDto.value = value;
    return reportMetricDataDto;
  }
}
