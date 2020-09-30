package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
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
}
