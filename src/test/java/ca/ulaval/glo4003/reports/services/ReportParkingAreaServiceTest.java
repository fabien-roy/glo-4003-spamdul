package ca.ulaval.glo4003.reports.services;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.services.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDto;
import ca.ulaval.glo4003.times.helpers.CustomDateTimeMother;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreaServiceTest {
  @Mock private ParkingAreaService parkingAreaService;
  @Mock private ReportRepository reportRepository;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
  @Mock private ReportParkingAreaQueryFactory reportParkingAreaQueryFactory;
  @Mock private ReportSummaryBuilder reportSummaryBuilder;
  @Mock private ReportQuery reportQuery;
  @Mock private ReportQuery summaryReportQuery;

  private ReportParkingAreaService reportParkingAreaService;

  private final ReportPeriod reportPeriod = aReportPeriod().build();
  private final ReportPeriod summaryReportPeriod = aReportPeriod().build();
  private final ReportPeriod aggregatedSummaryReportPeriod = aReportPeriod().build();
  private final ReportPeriodDto reportPeriodDto = aReportPeriodDto().build();
  private final ReportPeriodDto summaryReportPeriodDto = aReportPeriodDto().build();
  private final ReportType reportType = ReportType.MONTHLY;
  private final ReportType summaryReportType = ReportType.SUMMARY;
  private final String month = CustomDateTimeMother.createMonth().toString();
  private final List<ParkingAreaCode> parkingAreaCodes =
      Collections.singletonList(createParkingAreaCode());

  @Before
  public void setUp() {
    reportParkingAreaService =
        new ReportParkingAreaService(
            parkingAreaService,
            reportRepository,
            reportPeriodAssembler,
            reportParkingAreaQueryFactory,
            reportSummaryBuilder);

    when(parkingAreaService.getParkingAreaCodes()).thenReturn(parkingAreaCodes);

    when(reportParkingAreaQueryFactory.create(reportType, month, parkingAreaCodes))
        .thenReturn(reportQuery);
    when(reportRepository.getPeriods(reportQuery))
        .thenReturn(Collections.singletonList(reportPeriod));
    when(reportPeriodAssembler.assembleMany(Collections.singletonList(reportPeriod)))
        .thenReturn(Collections.singletonList(reportPeriodDto));

    when(reportParkingAreaQueryFactory.create(summaryReportType, month, parkingAreaCodes))
        .thenReturn(summaryReportQuery);
    when(reportRepository.getPeriods(summaryReportQuery))
        .thenReturn(Collections.singletonList(summaryReportPeriod));
    when(reportSummaryBuilder.aReportSummary()).thenReturn(reportSummaryBuilder);
    when(reportSummaryBuilder.withPeriods(Collections.singletonList(summaryReportPeriod)))
        .thenReturn(reportSummaryBuilder);
    when(reportSummaryBuilder.withAggregateFunctions(
            Arrays.asList(
                ReportAggregateFunctionType.MAXIMUM,
                ReportAggregateFunctionType.MINIMUM,
                ReportAggregateFunctionType.AVERAGE)))
        .thenReturn(reportSummaryBuilder);
    when(reportSummaryBuilder.withMetric(ReportMetricType.GATE_ENTRIES))
        .thenReturn(reportSummaryBuilder);
    when(reportSummaryBuilder.build())
        .thenReturn(Collections.singletonList(aggregatedSummaryReportPeriod));
    when(reportPeriodAssembler.assembleMany(
            Collections.singletonList(aggregatedSummaryReportPeriod)))
        .thenReturn(Collections.singletonList(summaryReportPeriodDto));
  }

  @Test
  public void whenGettingParkingAreaReports_thenGetParkingAreaReports() {
    List<ReportPeriodDto> reportPeriodDtos =
        reportParkingAreaService.getAllParkingAreaReports(reportType.toString(), month);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodDto);
  }

  @Test
  public void
      givenSummaryReportType_whenGettingParkingAreaReports_thenGetSummaryParkingAreaReports() {
    List<ReportPeriodDto> reportPeriodDtos =
        reportParkingAreaService.getAllParkingAreaReports(summaryReportType.toString(), month);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(summaryReportPeriodDto);
  }
}
