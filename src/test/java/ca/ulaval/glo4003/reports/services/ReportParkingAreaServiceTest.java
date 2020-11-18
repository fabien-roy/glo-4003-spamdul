package ca.ulaval.glo4003.reports.services;

import static ca.ulaval.glo4003.reports.helpers.ReportTypeMother.createReportType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.times.helpers.CustomDateTimeMother;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// TODO : Refactor this test class and remove most of verify()
@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreaServiceTest {
  @Mock private ReportRepository reportRepository;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
  @Mock private ReportQueryFactory reportQueryFactory;
  @Mock private ReportSummaryBuilder reportSummaryBuilder;
  @Mock private ReportQuery reportQuery;
  @Mock private ReportPeriod reportPeriod; // TODO : Why mock ReportPeriod?

  private ReportParkingAreaService reportParkingAreaService;

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
  }

  @Test
  public void whenGettingReports_thenCreateReportQuery() {
    reportParkingAreaService.getAllParkingAreaReports(reportType, month);

    verify(reportQueryFactory).create(ReportType.get(reportType), month);
  }

  @Test
  public void whenGettingReports_thenGetReportsPeriods() {
    reportParkingAreaService.getAllParkingAreaReports(reportType, month);

    verify(reportRepository).getPeriods(reportQuery);
  }

  @Test
  public void whenGettingReports_thenAssembleReports() {
    reportParkingAreaService.getAllParkingAreaReports(reportType, month);

    verify(reportPeriodAssembler).assembleMany(Collections.singletonList(reportPeriod));
  }
}
