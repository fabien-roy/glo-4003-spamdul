package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InMemoryReportDimensionBuilderTest {

  private ReportDimensionBuilder reportDimensionBuilder;

  @Before
  public void setUp() {
    reportDimensionBuilder = new InMemoryReportDimensionBuilder();
  }

  @Test
  public void givenNoDimensionType_whenBuildingMany_thenReturnNoDimension() {
    List<ReportDimension> dimensions = reportDimensionBuilder.someDimensions().buildMany();

    assertThat(dimensions).hasSize(0);
  }

  @Test
  public void
      givenConsumptionTypeDimensionType_whenBuildingMany_thenReturnConsumptionTypeDimension() {
    List<ReportDimensionType> dimensionTypes =
        Collections.singletonList(ReportDimensionType.CONSUMPTION_TYPE);

    List<ReportDimension> dimensions =
        reportDimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();

    assertThat(dimensions).hasSize(1);
    assertThat(dimensions.get(0)).isInstanceOf(InMemoryConsumptionTypeDimension.class);
  }

  @Test
  public void givenParkingAreaDimensionType_whenBuildingMany_thenReturnParkingAreaDimension() {
    List<ReportDimensionType> dimensionTypes =
        Collections.singletonList(ReportDimensionType.PARKING_AREA);

    List<ReportDimension> dimensions =
        reportDimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();

    assertThat(dimensions).hasSize(1);
    assertThat(dimensions.get(0)).isInstanceOf(InMemoryParkingAreaDimension.class);
  }

  @Test
  public void
      givenParkingAreaDimensionTypeAndParkingAreaCodes_whenBuildingMany_thenReturnParkingAreaDimensionWithParkingAreaCodes() {
    ParkingAreaCode parkingAreaCode = createParkingAreaCode();
    List<ReportDimensionType> dimensionTypes =
        Collections.singletonList(ReportDimensionType.PARKING_AREA);

    List<ReportDimension> dimensions =
        reportDimensionBuilder
            .someDimensions()
            .withTypes(dimensionTypes)
            .withParkingAreaCodes(Collections.singletonList(parkingAreaCode))
            .buildMany();

    assertThat(dimensions.get(0).getValues()).hasSize(1);
    assertThat(dimensions.get(0).getValues().get(0)).isSameInstanceAs(parkingAreaCode);
  }
}
