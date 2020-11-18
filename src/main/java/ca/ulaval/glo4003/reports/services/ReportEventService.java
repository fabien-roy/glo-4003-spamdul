package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.domain.*;
import java.util.logging.Logger;

public class ReportEventService {
  private final Logger logger = Logger.getLogger(ReportProfitService.class.getName());
  private final ReportRepository reportRepository;
  private final ReportEventFactory reportEventFactory;

  public ReportEventService(
      ReportRepository reportRepository, ReportEventFactory reportEventFactory) {
    this.reportRepository = reportRepository;
    this.reportEventFactory = reportEventFactory;
  }

  public void addBillPaidForParkingStickerEvent(Money profits) {
    logger.info(
        String.format(
            "Adding report event for paid parking sticker bill with %s profits",
            profits.toString()));

    ReportEvent reportEvent =
        reportEventFactory.create(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, profits);
    reportRepository.addEvent(reportEvent);
  }

  public void addBillPaidForAccessPassEvent(Money profits, ConsumptionType consumptionType) {
    logger.info(
        String.format(
            "Adding report event for paid access pass bill with %s profits", profits.toString()));

    ReportEvent reportEvent =
        reportEventFactory.create(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS, profits, consumptionType);
    reportRepository.addEvent(reportEvent);
  }

  public void addBillPaidForOffenseEvent(Money profits) {
    logger.info(
        String.format(
            "Adding report event for paid offense bill with %s profits", profits.toString()));

    ReportEvent reportEvent =
        reportEventFactory.create(ReportEventType.BILL_PAID_FOR_OFFENSE, profits);
    reportRepository.addEvent(reportEvent);
  }
}
