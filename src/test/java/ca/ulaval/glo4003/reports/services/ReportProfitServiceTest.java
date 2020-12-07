package ca.ulaval.glo4003.reports.services;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.services.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDto;
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
  @Mock private ReportQueryFactory reportQueryFactory;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
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

  @Before
  public void setUp() {
    reportProfitService =
        new ReportProfitService(reportRepository, reportQueryFactory, reportPeriodAssembler);

    when(reportQueryFactory.createBillPaidReportQuery(
            ReportEventType.BILL_PAID_FOR_PARKING_STICKER, year, false))
        .thenReturn(reportQueryForParkingStickerEvents);
    when(reportQueryFactory.createBillPaidReportQuery(
            ReportEventType.BILL_PAID_FOR_OFFENSE, year, false))
        .thenReturn(reportQueryForOffenseEvents);

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
  }

  private void givenQueryForAccessPassEvents(boolean isByConsumptionType) {
    reset(reportQueryFactory);

    when(reportQueryFactory.createBillPaidReportQuery(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS, year, isByConsumptionType))
        .thenReturn(reportQueryForAccessPassEvents);
  }

  @Test
  public void givenQueryForParkingStickerEvents_whenGettingAllProfits_thenReturnAllProfitDtos() {
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
    List<ReportPeriodDto> reportPeriodDtos =
        reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_OFFENSE, year);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodForOffenseEventsDto);
  }
}
