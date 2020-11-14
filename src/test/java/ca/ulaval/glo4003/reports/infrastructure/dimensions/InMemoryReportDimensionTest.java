package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class InMemoryReportDimensionTest {

  protected ReportDimension dimension;

  @Mock private ReportPeriodData data;
  @Mock private ReportPeriodData otherData;

  protected List<ReportPeriodData> singleData;
  private List<ReportPeriodData> multipleData;

  protected abstract List<ReportEvent> buildEvents();

  protected abstract int numberOfValues();

  public void setUp() {
    singleData = Collections.singletonList(data);
    multipleData = Arrays.asList(data, otherData);

    reset(data, otherData);
    when(data.getEvents()).thenReturn(buildEvents());
    when(otherData.getEvents()).thenReturn(Collections.emptyList());
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
