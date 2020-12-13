package ca.ulaval.glo4003.reports.infrastructure.metrics;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingConfiguration;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class GateEntriesForBicyclesMetricInMemoryTest extends ReportMetricInMemoryTest {

  private final ParkingAreaCode bicycleParkingAreaCode =
      ParkingConfiguration.getConfiguration().getBicycleParkingAreaCode();
  private final ReportEvent gateEnteredEventForBicycle =
      aReportEvent()
          .withParkingAreaCode(bicycleParkingAreaCode)
          .withType(ReportEventType.GATE_ENTERED)
          .build();
  private final ReportEvent gateEnteredEventForCar =
      aReportEvent()
          .withParkingAreaCode(createParkingAreaCode())
          .withType(ReportEventType.GATE_ENTERED)
          .build();
  private final ReportEvent otherEvent =
      aReportEvent().withType(ReportEventType.BILL_PAID_FOR_OFFENSE).build();

  @Override
  protected ReportMetricType metricType() {
    return ReportMetricType.GATE_ENTRIES_FOR_BICYCLES;
  }

  @Before
  public void setUp() {
    metric = new GateEntriesForBicyclesMetricInMemory();
  }

  @Test
  public void givenNoEvents_whenCalculating_thenReturnZero() {
    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(0);
  }

  @Test
  public void givenSingleGateEnteredEvent_whenCalculating_thenReturnNumberOfGateEnteredEvent() {
    double numberOfGateEnteredEvent = 1;
    data =
        aReportPeriodData()
            .withEvents(Collections.singletonList(gateEnteredEventForBicycle))
            .build();

    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(numberOfGateEnteredEvent);
  }

  @Test
  public void givenMultipleEvents_whenCalculating_thenReturnNumberOfGateEnteredEventsForBicycles() {
    double numberOfGateEnteredEventsForBicycles = 1;
    data =
        aReportPeriodData()
            .withEvents(
                Arrays.asList(gateEnteredEventForBicycle, gateEnteredEventForCar, otherEvent))
            .build();

    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(numberOfGateEnteredEventsForBicycles);
  }
}
