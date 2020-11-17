package ca.ulaval.glo4003.reports.domain;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.reports.helpers.ReportEventMother.createReportEventType;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import org.junit.Before;
import org.junit.Test;

public class ReportEventFactoryTest {

  private ReportEventFactory reportEventFactory;

  private final ReportEventType type = createReportEventType();
  private final Money profits = createMoney();
  private final ConsumptionType consumptionType = createConsumptionType();

  @Before
  public void setUp() {
    reportEventFactory = new ReportEventFactory();
  }

  @Test
  public void whenCreating_thenSetType() {
    ReportEvent reportEvent = reportEventFactory.create(type, profits);

    assertThat(reportEvent.getType()).isSameInstanceAs(type);
  }

  @Test
  public void whenCreating_thenSetDateTimeToNow() {
    CustomDateTime now = CustomDateTime.now();

    ReportEvent reportEvent = reportEventFactory.create(type, profits);

    assertThat(reportEvent.getDateTime().toDate()).isEqualTo(now.toDate());
  }

  @Test
  public void whenCreating_thenSetProfits() {
    ReportEvent reportEvent = reportEventFactory.create(type, profits);

    assertThat(reportEvent.getProfits()).isSameInstanceAs(profits);
  }

  @Test
  public void whenCreating_thenDoNotSetConsumptionType() {
    ReportEvent reportEvent = reportEventFactory.create(type, profits);

    assertThat(reportEvent.getConsumptionType()).isNull();
  }

  @Test
  public void whenCreatingWithConsumptionType_thenSetType() {
    ReportEvent reportEvent = reportEventFactory.create(type, profits, consumptionType);

    assertThat(reportEvent.getType()).isSameInstanceAs(type);
  }

  @Test
  public void whenCreatingWithConsumptionType_thenSetDateTimeToNow() {
    CustomDateTime now = CustomDateTime.now();

    ReportEvent reportEvent = reportEventFactory.create(type, profits, consumptionType);

    assertThat(reportEvent.getDateTime().toDate()).isEqualTo(now.toDate());
  }

  @Test
  public void whenCreatingWithConsumptionType_thenSetProfits() {
    ReportEvent reportEvent = reportEventFactory.create(type, profits, consumptionType);

    assertThat(reportEvent.getProfits()).isSameInstanceAs(profits);
  }

  @Test
  public void whenCreatingWithConsumptionType_thenSetConsumptionType() {
    ReportEvent reportEvent = reportEventFactory.create(type, profits, consumptionType);

    assertThat(reportEvent.getConsumptionType()).isSameInstanceAs(consumptionType);
  }
}
