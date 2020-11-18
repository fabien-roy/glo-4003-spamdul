package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReportAggregateFunctionBuilderInMemoryTest {

  private ReportAggregateFunctionBuilderInMemory aggregateFunctionBuilder;

  @Before
  public void setUp() {
    aggregateFunctionBuilder = new ReportAggregateFunctionBuilderInMemory();
  }

  @Test
  public void givenNoAggregateFunctionType_whenBuildingMany_thenReturnNoAggregateFunction() {
    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionBuilder.someAggregateFunctions().buildMany();

    assertThat(aggregateFunctions).hasSize(0);
  }

  @Test
  public void
      givenMaximumAggregateFunctionType_whenBuildingMany_thenReturnMaximumAggregateFunction() {
    List<ReportAggregateFunctionType> aggregateFunctionTypes =
        Collections.singletonList(ReportAggregateFunctionType.MAXIMUM);

    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionBuilder
            .someAggregateFunctions()
            .withTypes(aggregateFunctionTypes)
            .buildMany();

    assertThat(aggregateFunctions).hasSize(1);
    assertThat(aggregateFunctions.get(0)).isInstanceOf(MaximumAggregateFunctionInMemory.class);
  }

  @Test
  public void
      givenMinimumAggregateFunctionType_whenBuildingMany_thenReturnMinimumAggregateFunction() {
    List<ReportAggregateFunctionType> aggregateFunctionTypes =
        Collections.singletonList(ReportAggregateFunctionType.MINIMUM);

    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionBuilder
            .someAggregateFunctions()
            .withTypes(aggregateFunctionTypes)
            .buildMany();

    assertThat(aggregateFunctions).hasSize(1);
    assertThat(aggregateFunctions.get(0)).isInstanceOf(MinimumAggregateFunctionInMemory.class);
  }

  @Test
  public void
      givenAverageAggregateFunctionType_whenBuildingMany_thenReturnAverageAggregateFunction() {
    List<ReportAggregateFunctionType> aggregateFunctionTypes =
        Collections.singletonList(ReportAggregateFunctionType.AVERAGE);

    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionBuilder
            .someAggregateFunctions()
            .withTypes(aggregateFunctionTypes)
            .buildMany();

    assertThat(aggregateFunctions).hasSize(1);
    assertThat(aggregateFunctions.get(0)).isInstanceOf(AverageAggregateFunctionInMemory.class);
  }
}
