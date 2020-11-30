package ca.ulaval.glo4003.locations;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.locations.domain.SspSender;
import ca.ulaval.glo4003.locations.services.converters.PostalCodeConverter;
import org.junit.Before;
import org.junit.Test;

public class LocationInjectorTest {

  private LocationInjector locationInjector;

  @Before
  public void setUp() {
    locationInjector = new LocationInjector();
  }

  @Test
  public void whenCreatingPostalCodeConverter_thenReturnIt() {
    PostalCodeConverter postalCodeConverter = locationInjector.createPostalCodeConverter();

    assertThat(postalCodeConverter).isNotNull();
  }

  @Test
  public void whenCreatingPostalSender_thenReturnIt() {
    PostalSender postalSender = locationInjector.createPostalCodeSender();

    assertThat(postalSender).isNotNull();
  }

  @Test
  public void whenCreatingSspSender_thenReturnIt() {
    SspSender sspSender = locationInjector.createSspSender();

    assertThat(sspSender).isNotNull();
  }
}
