package ca.ulaval.glo4003.reports.infrastructure.metrics;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingConfiguration;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.stream.Stream;

public class GateEntriesForCarsMetricInMemory extends GateEntriesMetricInMemory {

  @Override
  public ReportMetricType getType() {
    return ReportMetricType.GATE_ENTRIES_FOR_CARS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    Stream<ReportEvent> gateEntries = filterGateEntries(data.getEvents());
    double gateEntriesForCars = calculateTotalCars(gateEntries);
    data.addMetric(toMetricData(gateEntriesForCars));
  }

  private double calculateTotalCars(Stream<ReportEvent> events) {
    ParkingAreaCode bicycleParkingAreaCode =
        ParkingConfiguration.getConfiguration().getBicycleParkingAreaCode();

    return events
        .filter(event -> !event.getParkingAreaCode().equals(bicycleParkingAreaCode))
        .count();
  }
}
