package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class ConsumptionTypeDimensionInMemoryTest extends ReportDimensionInMemoryTest {

  private final ConsumptionType firstConsumptionType = ConsumptionType.ZERO_POLLUTION;
  private final ConsumptionType secondConsumptionType = ConsumptionType.ECONOMIC;
  private final ReportEvent firstEvent =
      aReportEvent().withConsumptionType(firstConsumptionType).build();
  private final ReportEvent secondEvent =
      aReportEvent().withConsumptionType(secondConsumptionType).build();

  @Before
  public void setUp() {
    super.setUp();
    dimension = new ConsumptionTypeDimensionInMemory();
  }

  @Override
  protected List<ReportEvent> buildEvents() {
    return Arrays.asList(firstEvent, secondEvent);
  }

  @Override
  protected int numberOfValues() {
    return ConsumptionType.values().length;
  }

  @Test
  public void whenSplittingAll_thenSplitWithConsumptionTypeValues() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertThat(
            splitData.stream()
                .anyMatch(
                    data -> data.getDimensions().get(0).getValue().equals(firstConsumptionType)))
        .isTrue();
    assertThat(
            splitData.stream()
                .anyMatch(
                    data -> data.getDimensions().get(0).getValue().equals(secondConsumptionType)))
        .isTrue();
  }

  @Test
  public void whenSplittingAll_thenSplitWithConsumptionTypeDimensionType() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertThat(
            splitData.stream()
                .allMatch(
                    data ->
                        data.getDimensions()
                            .get(0)
                            .getType()
                            .equals(ReportDimensionType.CONSUMPTION_TYPE)))
        .isTrue();
  }

  @Test
  public void
      givenSingleEventPerConsumptionType_whenSplittingAll_thenSplitEventsByConsumptionType() {
    assertSplitEventsByConsumptionType(firstEvent, firstConsumptionType);
    assertSplitEventsByConsumptionType(secondEvent, secondConsumptionType);
  }

  private void assertSplitEventsByConsumptionType(ReportEvent event, ConsumptionType value) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);
    List<ReportPeriodData> filteredData = filterData(splitData, value);

    assertThat(filteredData).hasSize(1);
    assertThat(filteredData.get(0).getEvents()).hasSize(1);
    assertThat(filteredData.get(0).getEvents().get(0)).isSameInstanceAs(event);
  }

  private List<ReportPeriodData> filterData(List<ReportPeriodData> data, ConsumptionType value) {
    return data.stream()
        .filter(datum -> datum.getDimensions().get(0).getValue().equals(value))
        .collect(Collectors.toList());
  }
}
