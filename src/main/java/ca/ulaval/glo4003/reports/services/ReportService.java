package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.ReportEventFactory;
import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Collections;
import java.util.List;

public class ReportService {

  private final ReportRepository reportRepository;
  private final ReportQueryBuilder reportQueryBuilder;
  private final ReportPeriodAssembler reportPeriodAssembler;
  private final ReportEventFactory reportEventFactory;

  public ReportService(
      ReportRepository reportRepository,
      ReportQueryBuilder reportQueryBuilder,
      ReportPeriodAssembler reportPeriodAssembler,
      ReportEventFactory reportEventFactory) {
    this.reportRepository = reportRepository;
    this.reportQueryBuilder = reportQueryBuilder;
    this.reportPeriodAssembler = reportPeriodAssembler;
    this.reportEventFactory = reportEventFactory;
  }

  // TODO #246 : Test ReportService.getAllProfits
  public List<ReportPeriodDto> getAllProfits(ReportEventType reportEventType, int year) {
    return getAllProfits(reportEventType, year, false);
  }

  // TODO #246 : Test ReportService.getAllProfits
  public List<ReportPeriodDto> getAllProfits(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    ReportQuery reportQuery =
        reportQueryBuilder
            .aReportQuery()
            .withReportEventType(reportEventType)
            .withPeriod(TimePeriod.fromYear(year))
            .withScope(ReportScopeType.YEARLY)
            .withMetrics(Collections.singletonList(ReportMetricType.PROFITS))
            .withDimensions(
                isByConsumptionType
                    ? Collections.singletonList(ReportDimensionType.CONSUMPTION_TYPE)
                    : Collections.emptyList())
            .build();

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

    return reportPeriodAssembler.assembleMany(periods);
  }

  // TODO #246 : Use addBillPaidForParkingStickerEvent
  // TODO #246 : Test addBillPaidForParkingStickerEvent
  public void addBillPaidForParkingStickerEvent(Money profits) {
    ReportEvent reportEvent =
        reportEventFactory.create(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, profits);
    reportRepository.addEvent(reportEvent);
  }

  // TODO #246 : Use addBillPaidForAccessPassEvent
  // TODO #246 : Test addBillPaidForAccessPassEvent
  public void addBillPaidForAccessPassEvent(Money profits, ConsumptionType consumptionType) {
    ReportEvent reportEvent =
        reportEventFactory.create(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS, profits, consumptionType);
    reportRepository.addEvent(reportEvent);
  }

  // TODO #246 : Use addBillPaidForOffenseEvent
  // TODO #246 : Test addBillPaidForOffenseEvent
  public void addBillPaidForOffenseEvent(Money profits) {
    ReportEvent reportEvent =
        reportEventFactory.create(ReportEventType.BILL_PAID_FOR_OFFENSE, profits);
    reportRepository.addEvent(reportEvent);
  }
}
