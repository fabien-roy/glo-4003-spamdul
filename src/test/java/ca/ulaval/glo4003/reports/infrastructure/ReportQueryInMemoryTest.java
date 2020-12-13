package ca.ulaval.glo4003.reports.infrastructure;

import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportFilterInMemory;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportQueryInMemoryTest {

  @Mock private ReportScope scope;
  @Mock private ReportPeriod firstPeriod;
  @Mock private ReportPeriod secondPeriod;
  @Mock private ReportMetric firstMetric;
  @Mock private ReportMetric secondMetric;
  @Mock private ReportDimension firstDimension;
  @Mock private ReportDimension secondDimension;
  @Mock private ReportFilterInMemory filter;

  private ReportQueryInMemory query;

  private List<ReportMetric> metrics = new ArrayList<>();
  private List<ReportDimension> dimensions = new ArrayList<>();
  private List<ReportFilterInMemory> filters = new ArrayList<>();
  private List<ReportEvent> events = new ArrayList<>();
  private final CustomDateTime dateTime = createDateTime();
  private final CustomDateTime otherDateTime = createDateTime();
  private final ReportEvent firstEvent = aReportEvent().withDateTime(dateTime).build();
  private final ReportEvent secondEvent = aReportEvent().withDateTime(otherDateTime).build();
  private final ReportEvent eventOutsideFilter = aReportEvent().build();
  private final ReportPeriodData firstData = aReportPeriodData().build();
  private final ReportPeriodData secondData = aReportPeriodData().build();
  private final ReportPeriodData dimensionedData = aReportPeriodData().build();
  private final int numberOfFirstDimensions = 2;
  private final int numberOfSecondDimensions = 6;

  private void setUpQuery() {
    query = new ReportQueryInMemory(scope, metrics, dimensions, filters);
    query.setEvents(events);
  }

  @Before
  public void setUp() {
    reset(scope, firstPeriod, secondPeriod, firstMetric, secondMetric);

    setUpPeriods();
    setUpDimensions();
    setUpFilters();

    givenScopeWithSinglePeriod();
    givenScopeWithSingleMetric();
    givenScopeWithNoDimension();
    givenScopeWithSingleEvent();
  }

  private void setUpPeriods() {
    when(firstPeriod.contains(dateTime)).thenReturn(true);
    when(firstPeriod.contains(otherDateTime)).thenReturn(false);
    when(firstPeriod.getData()).thenReturn(Collections.singletonList(firstData));
    when(secondPeriod.contains(dateTime)).thenReturn(false);
    when(secondPeriod.contains(otherDateTime)).thenReturn(true);
    when(secondPeriod.getData()).thenReturn(Collections.singletonList(secondData));
  }

  private void setUpDimensions() {
    when(firstDimension.splitAll(anyList()))
        .thenReturn(Collections.nCopies(numberOfFirstDimensions, dimensionedData));
    when(secondDimension.splitAll(anyList()))
        .thenReturn(Collections.nCopies(numberOfSecondDimensions, dimensionedData));
  }

  private void setUpFilters() {
    when(filter.filter(Arrays.asList(firstEvent, secondEvent, eventOutsideFilter)))
        .thenReturn(Arrays.asList(firstEvent, secondEvent));
  }

  private void givenScopeWithSinglePeriod() {
    when(scope.getReportPeriods()).thenReturn(Collections.singletonList(firstPeriod));
    setUpQuery();
  }

  private void givenScopeWithMultiplePeriods() {
    when(scope.getReportPeriods()).thenReturn(Arrays.asList(firstPeriod, secondPeriod));
    setUpQuery();
  }

  private void givenScopeWithSingleMetric() {
    metrics = Collections.singletonList(firstMetric);
    setUpQuery();
  }

  private void givenScopeWithMultipleMetrics() {
    metrics = Arrays.asList(firstMetric, secondMetric);
    setUpQuery();
  }

  private void givenScopeWithNoDimension() {
    dimensions = Collections.emptyList();
    setUpQuery();
  }

  private void givenScopeWithSingleDimension() {
    dimensions = Collections.singletonList(firstDimension);
    setUpQuery();
  }

  private void givenScopeWithMultipleDimensions() {
    dimensions = Arrays.asList(firstDimension, secondDimension);
    setUpQuery();
  }

  private void givenScopeWithSingleEvent() {
    events = Collections.singletonList(firstEvent);
    setUpQuery();
  }

  private void givenScopeWithMultipleEvents() {
    events = Arrays.asList(firstEvent, secondEvent);
    setUpQuery();
  }

  private void givenEventsOutsideOfFilter() {
    filters = Collections.singletonList(filter);
    events = Arrays.asList(firstEvent, secondEvent, eventOutsideFilter);
    setUpQuery();
  }

  @Test
  public void givenEventsOutsideFilter_whenExecuting_thenFilterEvents() {
    givenEventsOutsideOfFilter();
    givenScopeWithSinglePeriod();

    query.execute();

    verify(firstPeriod).contains(firstEvent.getDateTime());
    verify(firstPeriod).contains(secondEvent.getDateTime());
    verify(firstPeriod, never()).contains(eventOutsideFilter.getDateTime());
  }

  @Test
  public void givenSinglePeriod_whenExecuting_thenReturnSinglePeriod() {
    givenScopeWithSinglePeriod();

    List<ReportPeriod> periods = query.execute();

    assertThat(periods).hasSize(1);
  }

  @Test
  public void givenMultiplePeriods_whenExecuting_thenReturnMultiplePeriods() {
    givenScopeWithMultiplePeriods();

    List<ReportPeriod> periods = query.execute();

    assertThat(periods).hasSize(2);
  }

  @Test
  public void whenExecuting_thenUseTransactionsWithinPeriods() {
    givenScopeWithMultiplePeriods();
    givenScopeWithMultipleEvents();

    query.execute();

    verify(firstPeriod).setSingleData(eq(Collections.singletonList(firstEvent)));
    verify(secondPeriod).setSingleData(eq(Collections.singletonList(secondEvent)));
  }

  @Test
  public void givenSingleDimension_whenExecuting_thenSplitDataAccordingToDimension() {
    givenScopeWithSingleDimension();

    query.execute();

    ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
    verify(firstPeriod).setData(captor.capture());
    assertThat(captor.getValue()).hasSize(numberOfFirstDimensions);
  }

  @Test
  public void givenMultipleDimensions_whenExecuting_thenSplitDataAccordingToDimension() {
    givenScopeWithMultipleDimensions();

    query.execute();

    ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
    verify(firstPeriod).setData(captor.capture());
    assertThat(captor.getValue()).hasSize(numberOfSecondDimensions);
  }

  @Test
  public void givenSingleMetric_whenExecuting_thenCalculateMetricForEachDimensionedData() {
    givenScopeWithMultipleDimensions();

    query.execute();

    verify(firstMetric, times(numberOfSecondDimensions)).calculate(any());
    verify(secondMetric, never()).calculate(any());
  }

  @Test
  public void givenMultipleMetrics_whenExecuting_thenCalculateMetricsForEachDimensionedData() {
    givenScopeWithMultipleDimensions();
    givenScopeWithMultipleMetrics();

    query.execute();

    verify(firstMetric, times(numberOfSecondDimensions)).calculate(any());
    verify(secondMetric, times(numberOfSecondDimensions)).calculate(any());
  }
}
