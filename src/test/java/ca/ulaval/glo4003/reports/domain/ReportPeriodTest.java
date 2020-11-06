package ca.ulaval.glo4003.reports.domain;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodMother.createReportPeriodName;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportPeriodTest {
  @Mock private static TimePeriod timePeriod;
  @Mock private static ReportEvent event;

  private ReportPeriod reportPeriod;

  private final String name = createReportPeriodName();
  private final CustomDateTime dateTime = createDateTime();
  private List<ReportEvent> events;

  @Before
  public void setUp() {
    reportPeriod = new ReportPeriod(name, timePeriod);

    events = Collections.singletonList(event);
  }

  private void givenPeriodThatContainsDateTime() {
    reset(timePeriod);
    when(timePeriod.contains(dateTime)).thenReturn(true);
    setUp();
  }

  private void givenPeriodThatDoesNotContainDateTime() {
    reset(timePeriod);
    when(timePeriod.contains(dateTime)).thenReturn(false);
    setUp();
  }

  @Test
  public void whenSettingSingleData_thenSetTransactionsAsSingleData() {
    reportPeriod.setSingleData(events);
    List<ReportPeriodData> data = reportPeriod.getData();

    assertThat(data).hasSize(1);
    assertThat(data.get(0).getEvents().get(0)).isSameInstanceAs(event);
  }

  @Test
  public void
      givenPeriodThatContainsDateTime_whenCheckingIfReportPeriodContainsDateTime_thenReturnTrue() {
    givenPeriodThatContainsDateTime();

    boolean result = reportPeriod.contains(dateTime);

    assertThat(result).isTrue();
  }

  @Test
  public void
      givenPeriodThatDoesNotContainDateTime_whenCheckingIfReportPeriodContainsDateTime_thenReturnFalse() {
    givenPeriodThatDoesNotContainDateTime();

    boolean result = reportPeriod.contains(dateTime);

    assertThat(result).isFalse();
  }
}
