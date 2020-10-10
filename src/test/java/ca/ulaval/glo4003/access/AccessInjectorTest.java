package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.api.AccessResource;
import ca.ulaval.glo4003.cars.services.CarService;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AccessInjectorTest {
  @Mock private CarService carService;
  private AccessInjector accessInjector;

  @Before
  public void setUp() {
    accessInjector = new AccessInjector(carService);
  }

  @Test
  public void whenGettingAccessResource_thenReturnIt() {
    AccessResource accessResource = accessInjector.getAccessResource();

    Truth.assertThat(accessResource).isNotNull();
  }
}
