package ca.ulaval.glo4003.reports.infrastructure.filters;

import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReportEventTypeFilterInMemoryTest {

  private ReportFilterInMemory filter;

  private final ReportEventType type = ReportEventType.GATE_ENTERED;
  private final ReportEventType otherType = ReportEventType.BILL_PAID_FOR_OFFENSE;
  private final ReportEvent reportEventWithType = aReportEvent().withType(type).build();
  private final ReportEvent reportEventWithOtherType = aReportEvent().withType(otherType).build();

  @Before
  public void setUp() {
    filter = new ReportEventTypeFilterInMemory(type);
  }

  @Test
  public void givenReportEvents_whenFiltering_thenFilterWithType() {
    List<ReportEvent> reportEvents = Arrays.asList(reportEventWithType, reportEventWithOtherType);

    List<ReportEvent> filteredReportEvents = filter.filter(reportEvents);

    assertThat(filteredReportEvents).hasSize(1);
    assertThat(filteredReportEvents.get(0)).isSameInstanceAs(reportEventWithType);
  }
}
