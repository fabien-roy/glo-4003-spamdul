package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.api.AccessResource;
import com.google.common.truth.Truth;
import org.junit.Test;

public class AccessInjectorTest {
  AccessInjector accessInjector = new AccessInjector();

  @Test
  public void whenGettingAccessResource_thenReturnIt() {
    AccessResource accessResource = accessInjector.getAccessResource();

    Truth.assertThat(accessResource).isNotNull();
  }
}
