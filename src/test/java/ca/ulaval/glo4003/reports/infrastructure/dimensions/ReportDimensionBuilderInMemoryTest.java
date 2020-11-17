package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReportDimensionBuilderInMemoryTest {

  private ReportDimensionBuilderInMemory reportDimensionBuilder;

  @Before
  public void setUp() {
    reportDimensionBuilder = new ReportDimensionBuilderInMemory();
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
    assertThat(dimensions.get(0)).isInstanceOf(ConsumptionTypeDimensionInMemory.class);
  }
}
