package ca.ulaval.glo4003.reports.assemblers;

import ca.ulaval.glo4003.reports.api.dto.ReportDimensionDataDto;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionData;
import java.util.List;
import java.util.stream.Collectors;

public class ReportDimensionDataAssembler {

  public List<ReportDimensionDataDto> assembleMany(List<ReportDimensionData> dimensions) {
    return dimensions.stream().map(this::assembleOne).collect(Collectors.toList());
  }

  private ReportDimensionDataDto assembleOne(ReportDimensionData data) {
    ReportDimensionDataDto reportDimensionDataDto = new ReportDimensionDataDto();
    reportDimensionDataDto.name = data.getType().toString();
    reportDimensionDataDto.value = data.getValue().toString();
    return reportDimensionDataDto;
  }
}
