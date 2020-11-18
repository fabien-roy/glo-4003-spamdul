package ca.ulaval.glo4003.reports.services;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static ca.ulaval.glo4003.reports.helpers.ReportTypeMother.createReportType;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.times.helpers.CustomDateTimeMother;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreaServiceTest {
  @Mock private ReportRepository reportRepository;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
  @Mock private ReportQueryFactory reportQueryFactory;
  @Mock private ReportSummaryBuilder reportSummaryBuilder;
  @Mock private ReportQuery reportQuery;

  private ReportParkingAreaService reportParkingAreaService;

  private final ReportPeriod reportPeriod = aReportPeriod().build();
  private final ReportPeriodDto reportPeriodDto = aReportPeriodDto().build();
  private final String reportType = createReportType().toString();
  private final String month = CustomDateTimeMother.createMonth().toString();

  @Before
  public void setUp() {
    reportParkingAreaService =
        new ReportParkingAreaService(
            reportRepository, reportPeriodAssembler, reportQueryFactory, reportSummaryBuilder);

    when(reportQueryFactory.create(ReportType.get(reportType), month)).thenReturn(reportQuery);
    when(reportRepository.getPeriods(reportQuery))
        .thenReturn(Collections.singletonList(reportPeriod));
    when(reportPeriodAssembler.assembleMany(Collections.singletonList(reportPeriod)))
        .thenReturn(Collections.singletonList(reportPeriodDto));
  }

  @Test
  public void whenGettingParkingAreaReports_thenGetParkingAreaReports() {
    List<ReportPeriodDto> reportPeriodDtos =
        reportParkingAreaService.getAllParkingAreaReports(reportType, month);

    assertThat(reportPeriodDtos).hasSize(1);
    assertThat(reportPeriodDtos.get(0)).isSameInstanceAs(reportPeriodDto);
  }
}
