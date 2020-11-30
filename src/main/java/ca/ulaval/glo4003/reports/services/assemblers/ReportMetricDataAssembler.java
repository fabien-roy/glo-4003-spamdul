package ca.ulaval.glo4003.reports.services.assemblers;

import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.services.dto.ReportMetricDataDto;
import java.util.List;
import java.util.stream.Collectors;

public class ReportMetricDataAssembler {

  public List<ReportMetricDataDto> assembleMany(List<ReportMetricData> metrics) {
    return metrics.stream().map(this::assembleOne).collect(Collectors.toList());
  }

  private ReportMetricDataDto assembleOne(ReportMetricData data) {
    ReportMetricDataDto reportMetricDataDto = new ReportMetricDataDto();
    reportMetricDataDto.name = data.getType().toString();
    reportMetricDataDto.value = data.getValue();
    return reportMetricDataDto;
  }
}
