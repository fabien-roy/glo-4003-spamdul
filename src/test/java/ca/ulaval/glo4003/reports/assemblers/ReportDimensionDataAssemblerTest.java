package ca.ulaval.glo4003.reports.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.api.dto.ReportDimensionDataDto;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportDimensionDataAssemblerTest {

  @Mock private ReportDimensionData dimension;
  @Mock private ReportDimensionData otherDimension;

  private ReportDimensionDataAssembler reportDimensionDataAssembler;

  private final ReportDimensionType dimensionType = ReportDimensionType.CONSUMPTION_TYPE;
  private final ReportDimensionType otherDimensionType = ReportDimensionType.PARKING_AREA;
  private final String dimensionValue = "dimensionValue";
  private final String otherDimensionValue = "otherDimensionValue";
  private List<ReportDimensionData> singleDimension;
  private List<ReportDimensionData> multipleDimensions;

  @Before
  public void setUp() {
    reportDimensionDataAssembler = new ReportDimensionDataAssembler();

    when(dimension.getType()).thenReturn(dimensionType);
    when(dimension.getValue()).thenReturn(dimensionValue);
    when(otherDimension.getType()).thenReturn(otherDimensionType);
    when(otherDimension.getValue()).thenReturn(otherDimensionValue);

    singleDimension = Collections.singletonList(dimension);
    multipleDimensions = Arrays.asList(dimension, otherDimension);
  }

  @Test
  public void givenSingleDimension_whenAssemblingMany_thenAssembleASingleDimension() {
    List<ReportDimensionDataDto> dtos = reportDimensionDataAssembler.assembleMany(singleDimension);

    assertThat(dtos).hasSize(1);
  }

  @Test
  public void givenMultipleDimensions_whenAssemblingMany_thenAssembleMultipleDimensions() {
    List<ReportDimensionDataDto> dtos =
        reportDimensionDataAssembler.assembleMany(multipleDimensions);

    assertThat(dtos).hasSize(2);
  }

  @Test
  public void givenSingleDimension_whenAssemblingMany_thenSetName() {
    List<ReportDimensionDataDto> dtos = reportDimensionDataAssembler.assembleMany(singleDimension);

    assertThat(dtos.get(0).name).isEqualTo(dimensionType.toString());
  }

  @Test
  public void givenMultipleDimensions_whenAssemblingMany_thenSetNames() {
    List<ReportDimensionDataDto> dtos =
        reportDimensionDataAssembler.assembleMany(multipleDimensions);

    assertThat(dtos.get(0).name).isEqualTo(dimensionType.toString());
    assertThat(dtos.get(1).name).isEqualTo(otherDimensionType.toString());
  }

  @Test
  public void givenSingleDimension_whenAssemblingMany_thenSetValue() {
    List<ReportDimensionDataDto> dtos = reportDimensionDataAssembler.assembleMany(singleDimension);

    assertThat(dtos.get(0).value).isEqualTo(dimensionValue);
  }

  @Test
  public void givenMultipleDimensions_whenAssemblingMany_thenSetValues() {
    List<ReportDimensionDataDto> dtos =
        reportDimensionDataAssembler.assembleMany(multipleDimensions);

    assertThat(dtos.get(0).value).isEqualTo(dimensionValue);
    assertThat(dtos.get(1).value).isEqualTo(otherDimensionValue);
  }
}
