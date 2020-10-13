package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.services.BillService;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AccessInjectorTest {
  @Mock private CarService carService;
  @Mock private AccountService accountService;
  @Mock private BillService billService;
  private AccessInjector accessInjector;

  @Before
  public void setUp() {
    accessInjector = new AccessInjector();
  }

  @Test
  public void whenGettingAccessPassService_thenReturnIt() {
    AccessPassService accessResource =
        accessInjector.createAccessPassService(carService, accountService, billService);

    Truth.assertThat(accessResource).isNotNull();
  }
}
