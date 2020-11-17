package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class ParkingAreaDimensionInMemoryTest extends ReportDimensionInMemoryTest {

  private final ParkingAreaCode firstParkingAreaCode = createParkingAreaCode();
  private final ParkingAreaCode secondParkingAreaCode = createParkingAreaCode();
  private final List<ParkingAreaCode> parkingAreaCodes =
      Arrays.asList(firstParkingAreaCode, secondParkingAreaCode);
  private final ReportEvent firstEvent =
      aReportEvent().withParkingAreaCode(firstParkingAreaCode).build();
  private final ReportEvent secondEvent =
      aReportEvent().withParkingAreaCode(secondParkingAreaCode).build();

  @Before
  public void setUp() {
    super.setUp();
    dimension = new ParkingAreaDimensionInMemory(parkingAreaCodes);
  }

  @Override
  protected List<ReportEvent> buildEvents() {
    return Arrays.asList(firstEvent, secondEvent);
  }

  @Override
  protected int numberOfValues() {
    return parkingAreaCodes.size();
  }

  @Test
  public void whenSplittingAll_thenSplitWithParkingAreaCodeValues() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertThat(
        splitData.stream()
            .anyMatch(data -> data.getDimensions().get(0).getValue().equals(firstParkingAreaCode)));
    assertThat(
        splitData.stream()
            .anyMatch(
                data -> data.getDimensions().get(0).getValue().equals(secondParkingAreaCode)));
  }

  @Test
  public void whenSplittingAll_thenSplitWithParkingAreaDimensionType() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertThat(
        splitData.stream()
            .allMatch(
                data ->
                    data.getDimensions()
                        .get(0)
                        .getType()
                        .equals(ReportDimensionType.PARKING_AREA)));
  }

  @Test
  public void
      givenSingleEventPerConsumptionType_whenSplittingAll_thenSplitEventsByConsumptionType() {
    assertSplitEventsByParkingArea(firstEvent, firstParkingAreaCode);
    assertSplitEventsByParkingArea(secondEvent, secondParkingAreaCode);
  }

  private void assertSplitEventsByParkingArea(ReportEvent event, ParkingAreaCode value) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);
    List<ReportPeriodData> filteredData = filterData(splitData, value);

    assertThat(filteredData).hasSize(1);
    assertThat(filteredData.get(0).getEvents()).hasSize(1);
    assertThat(filteredData.get(0).getEvents().get(0)).isSameInstanceAs(event);
  }

  private List<ReportPeriodData> filterData(List<ReportPeriodData> data, ParkingAreaCode value) {
    return data.stream()
        .filter(datum -> datum.getDimensions().get(0).getValue().equals(value))
        .collect(Collectors.toList());
  }
}
