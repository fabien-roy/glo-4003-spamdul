package ca.ulaval.glo4003.funds;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FundInjectorTest {

  @Mock private ReportEventService reportEventService;

  private FundInjector fundInjector;

  @Before
  public void setUp() {
    fundInjector = new FundInjector();
  }

  @Test
  public void whenCreatingBillService_thenReturnIt() {
    BillService billService = fundInjector.createBillService(reportEventService);

    assertThat(billService).isNotNull();
  }

  @Test
  public void whenCreatingMoneyConverter_thenReturnIt() {
    MoneyConverter moneyConverter = fundInjector.createMoneyConverter();

    assertThat(moneyConverter).isNotNull();
  }

  @Test
  public void whenGettingSustainableMobilityProgramBankRepository_thenReturnIt() {
    SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository =
        fundInjector.getSustainableMobilityProgramBankRepository();

    assertThat(sustainableMobilityProgramBankRepository).isNotNull();
  }
}
