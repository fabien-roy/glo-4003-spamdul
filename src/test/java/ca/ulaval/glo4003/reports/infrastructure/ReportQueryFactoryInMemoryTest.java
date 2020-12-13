package ca.ulaval.glo4003.reports.infrastructure;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.reports.helpers.ReportEventMother.createReportEventType;
import static ca.ulaval.glo4003.reports.helpers.ReportScopeMother.createYear;
import static ca.ulaval.glo4003.reports.helpers.ReportTypeMother.createReportType;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createMonth;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeFactory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ConsumptionTypeDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ParkingAreaDimensionInMemory;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportEventTypeFilterInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesForBicyclesMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesForCarsMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.GateEntriesMetricInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.ProfitsMetricInMemory;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportQueryFactoryInMemoryTest {

  @Mock private ReportScopeFactory reportScopeFactory;
  @Mock private ReportScope yearlyReportScope;
  @Mock private ReportScope monthlyReportScope;
  @Mock private ReportScope dailyReportScope;

  private ReportQueryFactoryInMemory reportQueryFactory;

  private final ReportEventType reportEventType = createReportEventType();
  private final ReportType reportType = createReportType();
  private final int year = createYear();
  private final String month = createMonth().toString();
  private final List<ParkingAreaCode> parkingAreaCodes =
      Collections.singletonList(createParkingAreaCode());

  @Before
  public void setUp() {
    reportQueryFactory = new ReportQueryFactoryInMemory(reportScopeFactory);

    when(reportScopeFactory.createYearlyScope(year)).thenReturn(yearlyReportScope);
    when(reportScopeFactory.createMonthlyScope()).thenReturn(monthlyReportScope);
    when(reportScopeFactory.createDailyScope(month)).thenReturn(dailyReportScope);
  }

  @Test
  public void whenCreatingGateEnteredReportQuery_thenAddGateEnteredReportFilter() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(reportType, month, parkingAreaCodes);

    assertThat(reportQuery.getFilters()).hasSize(1);
    assertThat(reportQuery.getFilters().get(0)).isInstanceOf(ReportEventTypeFilterInMemory.class);
    assertThat(
            ((ReportEventTypeFilterInMemory) reportQuery.getFilters().get(0)).getReportEventType())
        .isEqualTo(ReportEventType.GATE_ENTERED);
  }

  @Test
  public void givenMonthlyReportType_whenCreatingGateEnteredReportQuery_thenSetMonthlyScope() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(
            ReportType.MONTHLY, month, parkingAreaCodes);

    assertThat(reportQuery.getScope()).isSameInstanceAs(monthlyReportScope);
  }

  @Test
  public void givenDayOfMonthReportType_whenCreatingGateEnteredReportQuery_thenSetDailyScope() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(
            ReportType.DAY_OF_MONTH, month, parkingAreaCodes);

    assertThat(reportQuery.getScope()).isSameInstanceAs(dailyReportScope);
  }

  @Test
  public void givenSummaryReportType_whenCreatingGateEnteredReportQuery_thenSetDailyScope() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(
            ReportType.SUMMARY, month, parkingAreaCodes);

    assertThat(reportQuery.getScope()).isSameInstanceAs(dailyReportScope);
  }

  @Test
  public void givenMonthlyReportType_whenCreatingGateEnteredReportQuery_thenAddGateEntriesMetric() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(
            ReportType.MONTHLY, month, parkingAreaCodes);

    assertThat(reportQuery.getMetrics()).hasSize(1);
    assertThat(reportQuery.getMetrics().get(0)).isInstanceOf(GateEntriesMetricInMemory.class);
  }

  @Test
  public void
      givenDayOfMonthReportType_whenCreatingGateEnteredReportQuery_thenAddGateEntriesMetric() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(
            ReportType.DAY_OF_MONTH, month, parkingAreaCodes);

    assertThat(reportQuery.getMetrics()).hasSize(1);
    assertThat(reportQuery.getMetrics().get(0)).isInstanceOf(GateEntriesMetricInMemory.class);
  }

  @Test
  public void
      givenSummaryReportType_whenCreatingGateEnteredReportQuery_thenAddGateEntriesMetricForVehicules() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(
            ReportType.SUMMARY, month, parkingAreaCodes);

    assertThat(reportQuery.getMetrics()).hasSize(2);
    assertThat(reportQuery.getMetrics().get(0))
        .isInstanceOf(GateEntriesForCarsMetricInMemory.class);
    assertThat(reportQuery.getMetrics().get(1))
        .isInstanceOf(GateEntriesForBicyclesMetricInMemory.class);
  }

  @Test
  public void whenCreatingGateEnteredReportQuery_thenAddParkingAreaDimension() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(reportType, month, parkingAreaCodes);

    assertThat(reportQuery.getDimensions()).hasSize(1);
    assertThat(reportQuery.getDimensions().get(0)).isInstanceOf(ParkingAreaDimensionInMemory.class);
  }

  @Test
  public void
      whenCreatingGateEnteredReportQuery_thenAddParkingAreaDimensionWithGivenParkingCodes() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createGateEnteredReportQuery(reportType, month, parkingAreaCodes);

    assertThat(((ParkingAreaDimensionInMemory) reportQuery.getDimensions().get(0)).getValues())
        .isEqualTo(parkingAreaCodes);
  }

  @Test
  public void whenCreatingBillPaidReportQuery_thenAddGivenReportEventReportFilter() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createBillPaidReportQuery(reportEventType, year, false);

    assertThat(reportQuery.getFilters()).hasSize(1);
    assertThat(reportQuery.getFilters().get(0)).isInstanceOf(ReportEventTypeFilterInMemory.class);
    assertThat(
            ((ReportEventTypeFilterInMemory) reportQuery.getFilters().get(0)).getReportEventType())
        .isEqualTo(reportEventType);
  }

  @Test
  public void whenCreatingBillPaidReportQuery_thenSetYearlyScope() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createBillPaidReportQuery(reportEventType, year, false);

    assertThat(reportQuery.getScope()).isSameInstanceAs(yearlyReportScope);
  }

  @Test
  public void whenCreatingBillPaidReportQuery_thenAddProfitsMetric() {
    ReportQueryInMemory reportQuery =
        reportQueryFactory.createBillPaidReportQuery(reportEventType, year, false);

    assertThat(reportQuery.getMetrics()).hasSize(1);
    assertThat(reportQuery.getMetrics().get(0)).isInstanceOf(ProfitsMetricInMemory.class);
  }

  @Test
  public void givenNotByConsumptionType_whenCreatingBillPaidReportQuery_thenAddNoDimension() {
    boolean isByConsumptionType = false;

    ReportQueryInMemory reportQuery =
        reportQueryFactory.createBillPaidReportQuery(reportEventType, year, isByConsumptionType);

    assertThat(reportQuery.getDimensions()).hasSize(0);
  }

  @Test
  public void
      givenByConsumptionType_whenCreatingBillPaidReportQuery_thenAddConsumptionTypeDimension() {
    boolean isByConsumptionType = true;

    ReportQueryInMemory reportQuery =
        reportQueryFactory.createBillPaidReportQuery(reportEventType, year, isByConsumptionType);

    assertThat(reportQuery.getDimensions()).hasSize(1);
    assertThat(reportQuery.getDimensions().get(0))
        .isInstanceOf(ConsumptionTypeDimensionInMemory.class);
  }
}
