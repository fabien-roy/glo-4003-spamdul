package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InMemoryReportDimensionBuilderTest {

  private InMemoryReportDimensionBuilder reportDimensionBuilder;

  @Before
  public void setUp() {
    reportDimensionBuilder = new InMemoryReportDimensionBuilder();
  }

  @Test
  public void givenNoDimensionType_whenBuildingMany_thenReturnNoDimension() {
    List<ReportDimension> dimensions = reportDimensionBuilder.someDimensions().buildMany();

    assertThat(dimensions).hasSize(0);
  }
}
