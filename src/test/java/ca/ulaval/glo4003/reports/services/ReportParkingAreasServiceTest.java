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

@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreasServiceTest {
  @Mock private ReportRepository reportRepository;
  @Mock private ReportPeriodAssembler reportPeriodAssembler;
  @Mock private ReportQueryFactory reportQueryFactory;
  @Mock private ReportQuery reportQuery;
  @Mock private ReportPeriod reportPeriod;

  private ReportParkingAreasService reportParkingAreasService;
  private String reportType = createReportType().toString();
  private String month = CustomDateTimeMother.createMonth().toString();

  @Before
  public void setUp() {
    reportParkingAreasService =
        new ReportParkingAreasService(reportRepository, reportPeriodAssembler, reportQueryFactory);

    when(reportQueryFactory.create(ReportType.get(reportType), month)).thenReturn(reportQuery);
    when(reportRepository.getPeriods(reportQuery))
        .thenReturn(Collections.singletonList(reportPeriod));
  }

  @Test
  public void whenGettingReports_thenCreateReportQuery() {
    reportParkingAreasService.getReports(reportType, month);

    verify(reportQueryFactory).create(ReportType.get(reportType), month);
  }

  @Test
  public void whenGettingReports_thenGetReportsPeriods() {
    reportParkingAreasService.getReports(reportType, month);

    verify(reportRepository).getPeriods(reportQuery);
  }

  @Test
  public void whenGettingReports_thenAssembleReports() {
    reportParkingAreasService.getReports(reportType, month);

    verify(reportPeriodAssembler).assembleMany(Collections.singletonList(reportPeriod));
  }
}
