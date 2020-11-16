package ca.ulaval.glo4003.reports.infrastructure.metrics;

import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class InMemoryGateEntriesMetricTest extends InMemoryReportMetricTest {

  private final ReportEvent firstGateEnteredEvent =
      aReportEvent().withType(ReportEventType.GATE_ENTERED).build();
  private final ReportEvent secondGateEnteredEvent =
      aReportEvent().withType(ReportEventType.GATE_ENTERED).build();
  private final ReportEvent otherEvent =
      aReportEvent().withType(ReportEventType.BILL_PAID_FOR_OFFENSE).build();

  @Override
  protected ReportMetricType metricType() {
    return ReportMetricType.GATE_ENTRIES;
  }

  @Before
  public void setUp() {
    metric = new InMemoryGateEntriesMetric();
  }

  @Test
  public void givenNoEvents_whenCalculating_thenReturnZero() {
    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(0);
  }

  @Test
  public void givenSingleGateEnteredEvent_whenCalculating_thenReturnNumberOfGateEnteredEvent() {
    double numberOfGateEnteredEvent = 1;
    data = aReportPeriodData().withEvents(Collections.singletonList(firstGateEnteredEvent)).build();

    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(numberOfGateEnteredEvent);
  }

  @Test
  public void givenMultipleEvents_whenCalculating_thenReturnNumberOfGateEnteredEvents() {
    double numberOfGateEnteredEvents = 2;
    data =
        aReportPeriodData()
            .withEvents(Arrays.asList(firstGateEnteredEvent, secondGateEnteredEvent, otherEvent))
            .build();

    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(numberOfGateEnteredEvents);
  }
}
