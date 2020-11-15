package ca.ulaval.glo4003.reports.assemblers;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import java.util.List;
import java.util.stream.Collectors;

// TODO #246 : Test ReportPeriodAssembler
public class ReportPeriodAssembler {

  private final ReportPeriodDataAssembler reportPeriodDataAssembler;

  public ReportPeriodAssembler(ReportPeriodDataAssembler reportPeriodDataAssembler) {
    this.reportPeriodDataAssembler = reportPeriodDataAssembler;
  }

  public List<ReportPeriodDto> assembleMany(List<ReportPeriod> periods) {
    return periods.stream().map(this::toResponse).collect(Collectors.toList());
  }

  private ReportPeriodDto toResponse(ReportPeriod period) {
    ReportPeriodDto reportPeriodDto = new ReportPeriodDto();
    reportPeriodDto.period = period.getName();
    reportPeriodDto.data = reportPeriodDataAssembler.assembleMany(period.getData());
    return reportPeriodDto;
  }
}
