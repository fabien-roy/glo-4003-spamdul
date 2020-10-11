package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.api.AccessResource;
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
  public void whenGettingAccessResource_thenReturnIt() {
    AccessResource accessResource =
        accessInjector.createAccessResource(carService, accountService, billService);

    Truth.assertThat(accessResource).isNotNull();
  }
}
