package ca.ulaval.glo4003.reports.domain;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createMonth;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimeMonth;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreaQueryFactoryTest {

  @Mock private ReportQueryBuilder reportQueryBuilder;
  @Mock private ReportQuery reportQuery;

  private ReportParkingAreaQueryFactory reportParkingAreaQueryFactory;

  private final String month = createMonth().toString();
  private final List<ParkingAreaCode> parkingAreaCodes =
      Collections.singletonList(createParkingAreaCode());

  @Before
  public void setUp() {
    when(reportQueryBuilder.aReportQuery()).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withReportEventType(ReportEventType.GATE_ENTERED))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withPeriod(new TimeYear(Year.now().getValue()).toPeriod()))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withPeriod(new TimeMonth(month).toPeriod()))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withScope(ReportScopeType.MONTHLY)).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withScope(ReportScopeType.DAILY)).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withMetrics(Collections.singletonList(ReportMetricType.GATE_ENTRIES)))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withDimensions(
            Collections.singletonList(ReportDimensionType.PARKING_AREA)))
        .thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.withParkingAreaCodes(parkingAreaCodes)).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.build()).thenReturn(reportQuery);

    reportParkingAreaQueryFactory = new ReportParkingAreaQueryFactory(reportQueryBuilder);
  }

  @Test
  public void givenMonthlyReportType_whenCreating_thenReturnMonthlyReportQuery() {
    ReportType reportType = ReportType.MONTHLY;

    ReportQuery reportQueryCreated =
        reportParkingAreaQueryFactory.create(reportType, month, parkingAreaCodes);

    assertThat(reportQuery).isEqualTo(reportQueryCreated);
  }

  @Test
  public void givenDayOfMonthReportType_whenCreating_thenReturnDayOfMonthReportQuery() {
    ReportType reportType = ReportType.DAY_OF_MONTH;

    ReportQuery reportQueryCreated =
        reportParkingAreaQueryFactory.create(reportType, month, parkingAreaCodes);

    assertThat(reportQuery).isEqualTo(reportQueryCreated);
  }

  @Test
  public void givenSummaryReportType_whenCreating_thenReturnDayOfMonthReportQuery() {
    ReportType reportType = ReportType.SUMMARY;

    ReportQuery reportQueryCreated =
        reportParkingAreaQueryFactory.create(reportType, month, parkingAreaCodes);

    assertThat(reportQuery).isEqualTo(reportQueryCreated);
  }
}
