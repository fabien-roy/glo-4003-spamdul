package ca.ulaval.glo4003.funds;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFundCollector;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FundInjectorTest {

  @Mock private ReportEventService reportEventService;
  @Mock private AccountService accountService;
  @Mock private InitiativeFundCollector initiativeFundCollector;

  private FundInjector fundInjector;

  @Before
  public void setUp() {
    fundInjector = new FundInjector();
  }

  @Test
  public void whenCreatingBillService_thenReturnIt() {
    BillService billService = fundInjector.createBillService(reportEventService, accountService, initiativeFundCollector);

    assertThat(billService).isNotNull();
  }

  @Test
  public void whenCreatingMoneyConverter_thenReturnIt() {
    MoneyConverter moneyConverter = fundInjector.createMoneyConverter();

    assertThat(moneyConverter).isNotNull();
  }
}
