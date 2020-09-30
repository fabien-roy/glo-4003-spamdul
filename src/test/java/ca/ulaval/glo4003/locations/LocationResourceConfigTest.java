package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class LocationResourceConfigTest {

  private LocationResourceConfig locationResourceConfig;

  @Before
  public void setUp() {
    locationResourceConfig = new LocationResourceConfig();
  }

  @Test
  public void whenCreatingPostalCodeAssembler_thenReturnIt() {
    PostalCodeAssembler postalCodeAssembler = locationResourceConfig.createPostalCodeAssembler();

    Truth.assertThat(postalCodeAssembler).isNotNull();
  }
}
