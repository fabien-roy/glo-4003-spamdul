package ca.ulaval.glo4003.accesspasses;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.services.BillService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AccessPassInjectorTest {
  @Mock private CarService carService;
  @Mock private AccountService accountService;
  @Mock private BillService billService;

  private AccessPassInjector accessPassInjector;

  @Before
  public void setUp() {
    accessPassInjector = new AccessPassInjector();
  }

  @Test
  public void whenGettingAccessPassService_thenReturnIt() {
    AccessPassService accessResource =
        accessPassInjector.createAccessPassService(carService, accountService, billService);

    assertThat(accessResource).isNotNull();
  }
}
