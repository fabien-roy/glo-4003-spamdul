package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReportAggregateFunctionFactoryInMemoryTest {

  private ReportAggregateFunctionFactoryInMemory aggregateFunctionFactory;

  @Before
  public void setUp() {
    aggregateFunctionFactory = new ReportAggregateFunctionFactoryInMemory();
  }

  @Test
  public void givenNoAggregateFunctionType_whenCreatingMany_thenReturnNoAggregateFunction() {
    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionFactory.createMany(Collections.emptyList());

    assertThat(aggregateFunctions).hasSize(0);
  }

  @Test
  public void
      givenMaximumAggregateFunctionType_whenCreatingMany_thenReturnMaximumAggregateFunction() {
    List<ReportAggregateFunctionType> aggregateFunctionTypes =
        Collections.singletonList(ReportAggregateFunctionType.MAXIMUM);

    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionFactory.createMany(aggregateFunctionTypes);

    assertThat(aggregateFunctions).hasSize(1);
    assertThat(aggregateFunctions.get(0)).isInstanceOf(MaximumAggregateFunctionInMemory.class);
  }

  @Test
  public void
      givenMinimumAggregateFunctionType_whenCreatingMany_thenReturnMinimumAggregateFunction() {
    List<ReportAggregateFunctionType> aggregateFunctionTypes =
        Collections.singletonList(ReportAggregateFunctionType.MINIMUM);

    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionFactory.createMany(aggregateFunctionTypes);

    assertThat(aggregateFunctions).hasSize(1);
    assertThat(aggregateFunctions.get(0)).isInstanceOf(MinimumAggregateFunctionInMemory.class);
  }

  @Test
  public void
      givenAverageAggregateFunctionType_whenCreatingMany_thenReturnAverageAggregateFunction() {
    List<ReportAggregateFunctionType> aggregateFunctionTypes =
        Collections.singletonList(ReportAggregateFunctionType.AVERAGE);

    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionFactory.createMany(aggregateFunctionTypes);

    assertThat(aggregateFunctions).hasSize(1);
    assertThat(aggregateFunctions.get(0)).isInstanceOf(AverageAggregateFunctionInMemory.class);
  }
}
