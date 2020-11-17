package ca.ulaval.glo4003.reports.services;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportProfitServiceTest {

  @Mock private ReportRepository reportRepository;
  @Mock private ReportQueryBuilder reportQueryBuilder;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
  @Mock private ReportEventFactory reportEventFactory;
  @Mock private ReportQuery reportQueryForParkingStickerEvents;
  @Mock private ReportQuery reportQueryForAccessPassEvents;
  @Mock private ReportQuery reportQueryForOffenseEvents;

  private ReportProfitService reportProfitService;

  private final int year = 2020;
  private final ReportPeriod reportPeriodForParkingStickerEvents = aReportPeriod().build();
  private final ReportPeriod reportPeriodForAccessPassEvents = aReportPeriod().build();
  private final ReportPeriod reportPeriodForOffenseEvents = aReportPeriod().build();
  private final ReportPeriodDto reportPeriodForParkingStickerEventsDto = aReportPeriodDto().build();
  private final ReportPeriodDto reportPeriodForAccessPassEventsDto = aReportPeriodDto().build();
  private final ReportPeriodDto reportPeriodForOffenseEventsDto = aReportPeriodDto().build();
  private final Money profits = createMoney();
  private final ConsumptionType consumptionType = createConsumptionType();
  private final ReportEvent billPaidForParkingStickerEvent = aReportEvent().build();
  private final ReportEvent billPaidForAccessPassEvent = aReportEvent().build();
  private final ReportEvent billPaidForOffenseEvent = aReportEvent().build();

  @Before
  public void setUp() {
    reportProfitService =
        new ReportProfitService(
            reportRepository, reportQueryBuilder, reportPeriodAssembler, reportEventFactory);

    when(reportRepository.getPeriods(reportQueryForParkingStickerEvents))
        .thenReturn(Collections.singletonList(reportPeriodForParkingStickerEvents));
    when(reportRepository.getPeriods(reportQueryForAccessPassEvents))
        .thenReturn(Collections.singletonList(reportPeriodForAccessPassEvents));
    when(reportRepository.getPeriods(reportQueryForOffenseEvents))
        .thenReturn(Collections.singletonList(reportPeriodForOffenseEvents));

    when(reportPeriodAssembler.assembleMany(
            Collections.singletonList(reportPeriodForParkingStickerEvents)))
        .thenReturn(Collections.singletonList(reportPeriodForParkingStickerEventsDto));
    when(reportPeriodAssembler.assembleMany(
            Collections.singletonList(reportPeriodForAccessPassEvents)))
        .thenReturn(Collections.singletonList(reportPeriodForAccessPassEventsDto));
    when(reportPeriodAssembler.assembleMany(
            Collections.singletonList(reportPeriodForOffenseEvents)))
        .thenReturn(Collections.singletonList(reportPeriodForOffenseEventsDto));

    when(reportEventFactory.create(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, profits))
        .thenReturn(billPaidForParkingStickerEvent);
    when(reportEventFactory.create(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS, profits, consumptionType))
        .thenReturn(billPaidForAccessPassEvent);
    when(reportEventFactory.create(ReportEventType.BILL_PAID_FOR_OFFENSE, profits))
        .thenReturn(billPaidForOffenseEvent);
  }

  private void givenQueryForParkingStickerEvents() {
    reset(reportQueryBuilder);

    when(reportQueryBuilder.aReportQuery()).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withReportEventType(ReportEventType.BILL_PAID_FOR_PARKING_STICKER))
        .thenReturn(reportQueryBuilder);
    setUpReportQueryBuilderForProfits(Collections.emptyList());
    when(reportQueryBuilder.build()).thenReturn(reportQueryForParkingStickerEvents);
  }

  private void givenQueryForAccessPassEvents(boolean isByConsumptionType) {
    reset(reportQueryBuilder);

    when(reportQueryBuilder.aReportQuery()).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withReportEventType(ReportEventType.BILL_PAID_FOR_ACCESS_PASS))
        .thenReturn(reportQueryBuilder);
    setUpReportQueryBuilderForProfits(
        isByConsumptionType
            ? Collections.singletonList(ReportDimensionType.CONSUMPTION_TYPE)
            : Collections.emptyList());
    when(reportQueryBuilder.build()).thenReturn(reportQueryForAccessPassEvents);
  }

  private void givenQueryForOffenseEvents() {
    reset(reportQueryBuilder);

    when(reportQueryBuilder.aReportQuery()).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withReportEventType(ReportEventType.BILL_PAID_FOR_OFFENSE))
        .thenReturn(reportQueryBuilder);
    setUpReportQueryBuilderForProfits(Collections.emptyList());
    when(reportQueryBuilder.build()).thenReturn(reportQueryForOffenseEvents);
  }

  private void setUpReportQueryBuilderForProfits(List<ReportDimensionType> dimensions) {
    when(reportQueryBuilder.withPeriod(new TimeYear(year).toPeriod()))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withScope(ReportScopeType.YEARLY)).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withMetrics(Collections.singletonList(ReportMetricType.PROFITS)))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withDimensions(dimensions)).thenReturn(reportQueryBuilder);
  }

  @Test
  public void givenQueryForParkingStickerEvents_whenGettingAllProfits_thenReturnAllProfitDtos() {
    givenQueryForParkingStickerEvents();

    List<ReportPeriodDto> reportPeriodDtos =
        reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, year);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodForParkingStickerEventsDto);
  }

  @Test
  public void
      givenQueryForAccessPassEventsAndNotByConsumptionType_whenGettingAllProfits_thenReturnAllProfitDtos() {
    boolean isByConsumptionType = false;
    givenQueryForAccessPassEvents(isByConsumptionType);

    List<ReportPeriodDto> reportPeriodDtos =
        reportProfitService.getAllProfits(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS, year, isByConsumptionType);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodForAccessPassEventsDto);
  }

  @Test
  public void
      givenQueryForAccessPassEventsAndByConsumptionType_whenGettingAllProfits_thenReturnAllProfitDtos() {
    boolean isByConsumptionType = true;
    givenQueryForAccessPassEvents(isByConsumptionType);

    List<ReportPeriodDto> reportPeriodDtos =
        reportProfitService.getAllProfits(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS, year, isByConsumptionType);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodForAccessPassEventsDto);
  }

  @Test
  public void givenQueryForOffenseEvents_whenGettingAllProfits_thenReturnAllProfitDtos() {
    givenQueryForOffenseEvents();

    List<ReportPeriodDto> reportPeriodDtos =
        reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_OFFENSE, year);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodForOffenseEventsDto);
  }

  @Test
  public void whenAddingBillPaidForParkingStickerEvent_thenAddReportEventToRepository() {
    reportProfitService.addBillPaidForParkingStickerEvent(profits);

    verify(reportRepository).addEvent(billPaidForParkingStickerEvent);
  }

  @Test
  public void whenAddingBillPaidForAccessPassEvent_thenAddReportEventToRepository() {
    reportProfitService.addBillPaidForAccessPassEvent(profits, consumptionType);

    verify(reportRepository).addEvent(billPaidForAccessPassEvent);
  }

  @Test
  public void whenAddingBillPaidForOffenseEvent_thenAddReportEventToRepository() {
    reportProfitService.addBillPaidForOffenseEvent(profits);

    verify(reportRepository).addEvent(billPaidForOffenseEvent);
  }
}
