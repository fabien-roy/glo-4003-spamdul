package ca.ulaval.glo4003.injection.car;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.api.car.CarResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceConfigTest {

  @Mock private AccountService accountService;

  private CarResourceConfig carResourceConfig;

  @Before
  public void setUp() {
    carResourceConfig = new CarResourceConfig();
  }

  @Test
  public void whenCreatingCarResource_thenReturnIt() {
    CarResource carResource = carResourceConfig.createCarResource(accountService);

    Truth.assertThat(carResource).isNotNull();
  }
}
