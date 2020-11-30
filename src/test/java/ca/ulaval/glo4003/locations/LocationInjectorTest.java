package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.locations.services.assemblers.PostalCodeAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class LocationInjectorTest {

  private LocationInjector locationInjector;

  @Before
  public void setUp() {
    locationInjector = new LocationInjector();
  }

  @Test
  public void whenCreatingPostalCodeAssembler_thenReturnIt() {
    PostalCodeAssembler postalCodeAssembler = locationInjector.createPostalCodeAssembler();

    Truth.assertThat(postalCodeAssembler).isNotNull();
  }

  @Test
  public void whenCreatingPostalSender_thenReturnIt() {
    PostalSender postalSender = locationInjector.createPostalCodeSender();

    Truth.assertThat(postalSender).isNotNull();
  }
}
