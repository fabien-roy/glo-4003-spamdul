package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public abstract class InMemoryReportDimensionTest {

  protected ReportDimension dimension;

  protected List<ReportPeriodData> singleData;
  private List<ReportPeriodData> multipleData;

  protected abstract List<ReportEvent> buildEvents();

  protected abstract int numberOfValues();

  protected void setUp() {
    ReportPeriodData data = aReportPeriodData().withEvents(buildEvents()).build();
    ReportPeriodData otherData = aReportPeriodData().build();

    singleData = Collections.singletonList(data);
    multipleData = Arrays.asList(data, otherData);
  }

  @Test
  public void givenNoData_whenSplittingAll_thenDoNotSplitData() {
    List<ReportPeriodData> splitData = dimension.splitAll(Collections.emptyList());

    assertThat(splitData).isEmpty();
  }

  @Test
  public void givenSingleData_whenSplittingAll_thenSplitDataByValues() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertThat(splitData).hasSize(numberOfValues());
  }

  @Test
  public void givenMultipleData_whenSplittingAll_thenSplitDataByValues() {
    List<ReportPeriodData> splitData = dimension.splitAll(multipleData);

    assertThat(splitData).hasSize(numberOfValues() * 2);
  }
}
