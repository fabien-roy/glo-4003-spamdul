package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportQuery;
import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo4003.reports.domain.ReportRepository;
import java.util.List;

// TODO #246 : Test report service
public class ReportService {

  private final ReportRepository reportRepository;
  private final ReportQueryBuilder reportQueryBuilder;
  private final ReportPeriodAssembler reportPeriodAssembler;

  public ReportService(
      ReportRepository reportRepository,
      ReportQueryBuilder reportQueryBuilder,
      ReportPeriodAssembler reportPeriodAssembler) {
    this.reportRepository = reportRepository;
    this.reportQueryBuilder = reportQueryBuilder;
    this.reportPeriodAssembler = reportPeriodAssembler;
  }

  public List<ReportPeriodDto> getAllProfits(int year) {
    return getAllProfits(year, false);
  }

  public List<ReportPeriodDto> getAllProfits(int year, boolean isByConsumptionType) {
    ReportQuery reportQuery =
        reportQueryBuilder.aReportQuery().build(); // TODO #246 : Use correctly query builder
    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);
    return reportPeriodAssembler.assembleMany(periods);
  }

  public void addPaymentEvent(Money payment) {
    // TODO #246
  }

  public void addPaymentEvent(Money payment, ConsumptionType consumptionType) {
    // TODO #246
  }
}
