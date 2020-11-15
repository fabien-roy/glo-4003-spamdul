package ca.ulaval.glo4003.reports.services;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

  @Mock private ReportRepository reportRepository;
  @Mock private ReportQueryBuilder reportQueryBuilder;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
  @Mock private ReportEventFactory reportEventFactory;

  private ReportService reportService;

  private final Money profits = createMoney();
  private final ConsumptionType consumptionType = createConsumptionType();
  private final ReportEvent billPaidForParkingStickerEvent = aReportEvent().build();
  private final ReportEvent billPaidForAccessPassEvent = aReportEvent().build();
  private final ReportEvent billPaidForOffenseEvent = aReportEvent().build();

  @Before
  public void setUp() {
    reportService =
        new ReportService(
            reportRepository, reportQueryBuilder, reportPeriodAssembler, reportEventFactory);

    when(reportEventFactory.create(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, profits))
        .thenReturn(billPaidForParkingStickerEvent);
    when(reportEventFactory.create(ReportEventType.BILL_PAID_FOR_ACCESS_PASS, profits, consumptionType))
            .thenReturn(billPaidForAccessPassEvent);
    when(reportEventFactory.create(ReportEventType.BILL_PAID_FOR_OFFENSE, profits))
            .thenReturn(billPaidForOffenseEvent);
  }

  @Test
  public void whenAddingBillPaidForParkingStickerEvent_thenAddReportEventToRepository() {
    reportService.addBillPaidForParkingStickerEvent(profits);

    verify(reportRepository).addEvent(billPaidForParkingStickerEvent);
  }

  @Test
  public void whenAddingBillPaidForAccessPassEvent_thenAddReportEventToRepository() {
    reportService.addBillPaidForAccessPassEvent(profits, consumptionType);

    verify(reportRepository).addEvent(billPaidForAccessPassEvent);
  }

  @Test
  public void whenAddingBillPaidForOffenseEvent_thenAddReportEventToRepository() {
    reportService.addBillPaidForOffenseEvent(profits);

    verify(reportRepository).addEvent(billPaidForOffenseEvent);
  }
}
