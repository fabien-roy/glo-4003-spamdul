package ca.ulaval.glo4003.reports.infrastructure.metrics;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.reports.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class ProfitsMetricInMemoryTest extends ReportMetricInMemoryTest {

  private final Money firstProfit = createMoney();
  private final Money secondProfit = createMoney();
  private final ReportEvent firstEvent = aReportEvent().withProfits(firstProfit).build();
  private final ReportEvent secondEvent = aReportEvent().withProfits(secondProfit).build();

  @Override
  protected ReportMetricType metricType() {
    return ReportMetricType.PROFITS;
  }

  @Before
  public void setUp() {
    metric = new ProfitsMetricInMemory();
  }

  @Test
  public void givenNoEvents_whenCalculating_thenReturnZero() {
    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(0);
  }

  @Test
  public void givenSingleEvent_whenCalculating_thenReturnEventProfits() {
    data = aReportPeriodData().withEvents(Collections.singletonList(firstEvent)).build();

    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(firstProfit.toDouble());
  }

  @Test
  public void givenMultipleEvents_whenCalculating_thenReturnSumOfEventsProfits() {
    Money sumOfEventsProfits = firstProfit.plus(secondProfit);
    data = aReportPeriodData().withEvents(Arrays.asList(firstEvent, secondEvent)).build();

    metric.calculate(data);

    assertThat(data.getMetrics().get(0).getValue()).isEqualTo(sumOfEventsProfits.toDouble());
  }
}
